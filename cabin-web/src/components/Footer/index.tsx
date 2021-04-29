import {GithubOutlined} from '@ant-design/icons';
import {DefaultFooter} from '@ant-design/pro-layout';

export default () => (
  <DefaultFooter
    copyright="一支简洁的小探针"
    links={[
      {
        key: 'github',
        title: <GithubOutlined/>,
        href: 'https://github.com/goimer/cabin',
        blankTarget: true,
      },
    ]}
  />
);
