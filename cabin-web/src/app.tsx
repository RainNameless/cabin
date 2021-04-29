// import type { Settings as LayoutSettings } from '@ant-design/pro-layout';
// import { PageLoading } from '@ant-design/pro-layout';
import type { RunTimeLayoutConfig } from 'umi';
// import { history } from 'umi';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
// import { getCurrentUser } from './services/base/login';
// import { isEmpty } from '@/utils/utils';

// /** 获取用户信息比较慢的时候会展示一个 loading */
// export const initialStateConfig = {
//   loading: <PageLoading />,
// };
//
// /**
//  * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
//  * */
// export async function getInitialState(): Promise<{
//   settings?: Partial<LayoutSettings>;
//   currentUser?: API.CurrentUser;
//   fetchUserInfo?: () => Promise<API.CurrentUser | undefined>;
// }> {
//   const fetchUserInfo = async () => {
//     try {
//       const token: string | null = localStorage.getItem('token');
//       if (!isEmpty(token)) {
//         const result = await getCurrentUser();
//         if (result) {
//           return result.data;
//         }
//       }
//     } catch (error) {
//       history.push('/user/login');
//     }
//     return undefined;
//   };
//   // 如果是登录页面，不执行
//   if (history.location.pathname !== '/user/login') {
//     const currentUser = await fetchUserInfo();
//     return {
//       fetchUserInfo,
//       currentUser,
//       settings: {},
//     };
//   }
//   return {
//     fetchUserInfo,
//     settings: {},
//   };
// }

// https://umijs.org/zh-CN/plugins/plugin-layout
// @ts-ignore
export const layout: RunTimeLayoutConfig = ({ initialState }) => {
  return {
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    footerRender: () => <Footer />,
    onPageChange: () => {
      // const { location } = history;
      // 如果没有登录，重定向到 login
      // if (!initialState?.currentUser && location.pathname !== '/user/login') {
      //   history.push('/user/login');
      // }
    },
    menuHeaderRender: undefined,
    // 自定义 403 页面
    // unAccessible: <div>unAccessible</div>,
    ...initialState?.settings,
  };
};
