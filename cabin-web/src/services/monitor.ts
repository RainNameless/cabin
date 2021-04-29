import {request} from '@@/plugin-request/request';

export type CommonResult = {
  success?: boolean;
  data?: any;
};

/** 获取监控数据列表 */
export async function getAgentList() {
  return request<CommonResult>('/cabin-api/agent/list', {
    method: 'GET',
  });
}
