import { Col, Divider, Progress, Row, Statistic, Tag } from 'antd';
import ProList from '@ant-design/pro-list';
import { getAgentList } from '@/services/monitor';
import { memConvert, netSpeedConvert } from '@/utils/unit';

const strokeColor = {
  '0%': '#87d068',
  '100%': '#108ee9',
};

export default () => {
  return (
    <ProList<any>
      polling={2}
      grid={{ gutter: 16, column: 4 }}
      metas={{
        title: {},
        subTitle: {},
        type: {},
        avatar: {},
        content: {},
        actions: {},
      }}
      request={async () => {
        const msg = await getAgentList();
        const dataSource = msg.data.map((item: any) => ({
          title: item.nodeName,
          subTitle:
            item.id !== undefined ? (
              <Tag color="#5BD8A6">在线</Tag>
            ) : (
              <Tag color="#f08080">离线</Tag>
            ),
          avatar: 'https://gw.alipayobjects.com/zos/antfincdn/UCSiy1j6jx/xingzhuang.svg',
          content:
            item.id !== undefined ? (
              <div
                style={{
                  flex: 1,
                }}
              >
                <div
                  style={{
                    width: 320,
                  }}
                >
                  <div>
                    <Row gutter={16}>
                      <Col span={8}>
                        <Statistic
                          title="系统"
                          value={item.systemOs}
                          valueStyle={{ color: '#000000', fontSize: 'initial' }}
                        />
                      </Col>
                      <Col span={8}>
                        <Statistic
                          title="已上线"
                          value={item.systemUpTime / 60 / 60 / 24}
                          precision={0}
                          valueStyle={{ color: '#5BD8A6', fontSize: 'initial' }}
                          suffix="天"
                        />
                      </Col>
                      <Col span={8}>
                        <Statistic
                          title="核心"
                          value={item.cpuCounts}
                          valueStyle={{
                            fontSize: 'initial',
                          }}
                        />
                      </Col>
                    </Row>
                  </div>
                  <div>
                    <Row gutter={16}>
                      <Col span={8}>
                        <Statistic
                          title="流量"
                          value={`${memConvert(item.netSendBytes + item.netRecvBytes)}`}
                          valueStyle={{ fontSize: 'initial' }}
                        />
                      </Col>
                      <Col span={8}>
                        <Statistic
                          title="上传"
                          value={netSpeedConvert(item.netSendBytesPerSecond)}
                          precision={2}
                          valueStyle={{ color: '#3f8600', fontSize: 'initial' }}
                        />
                      </Col>
                      <Col span={8}>
                        <Statistic
                          title="下载"
                          value={netSpeedConvert(item.netRecvBytesPerSecond)}
                          precision={2}
                          valueStyle={{ color: '#cf1322', fontSize: 'initial' }}
                        />
                      </Col>
                    </Row>
                  </div>
                  <Divider orientation="left" plain={true}>
                    使用率
                  </Divider>
                  <div>
                    CPU&ensp;
                    <span style={{ fontSize: '10px', color: '#808080' }}>{item.cpuVersion}</span>
                    <Progress strokeColor={strokeColor} percent={item.cpuPercent} status="normal" />
                  </div>
                  <div>
                    内存&ensp;
                    <span style={{ fontSize: '10px', color: '#808080' }}>
                      {memConvert(item.usedMem)}/{memConvert(item.totalMem)}
                    </span>
                    <Progress strokeColor={strokeColor} percent={item.memPercent} status="normal" />
                  </div>
                  <div>
                    交换内存&ensp;
                    <span style={{ fontSize: '10px', color: '#808080' }}>
                      {memConvert(item.usedSwap)}/{memConvert(item.totalSwap)}
                    </span>
                    <Progress
                      strokeColor={strokeColor}
                      percent={item.swapPercent}
                      status="normal"
                    />
                  </div>
                  <div>
                    硬盘&ensp;
                    <span style={{ fontSize: '10px', color: '#808080' }}>
                      {memConvert(item.usedDisk)}/{memConvert(item.totalDisk)}
                    </span>
                    <Progress
                      strokeColor={strokeColor}
                      percent={parseFloat(
                        String(((item.usedDisk / item.totalDisk) * 100).toFixed(2)),
                      )}
                    />
                  </div>
                </div>
              </div>
            ) : (
              '节点已失去连接'
            ),
        }));
        return {
          data: dataSource,
          // success 请返回 true，
          // 不然 table 会停止解析数据，即使有数据
          success: true,
          // 不传会使用 data 的长度，如果是分页一定要传
          // total: 12,
        };
      }}
      headerTitle="机器列表"
    />
  );
};
