export default [
  {
    path: '/monitor',
    name: '监控页面',
    icon: 'smile',
    component: './Monitor',
  },
  {
    path: '/',
    redirect: '/monitor',
  },
  {
    component: './404',
  },
];
