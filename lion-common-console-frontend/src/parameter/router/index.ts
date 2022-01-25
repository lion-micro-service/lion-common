import { createRouter,createWebHashHistory}  from 'vue-router'
const routes : Array<any> =[{
    path:'/',
    name:'参数',
    redirect:'/parameter/list'
},{
    path:'/parameter/list',
    name:'参数列表',
    component: () => import('@/parameter/views/List.vue'),
    meta: {keepAlive: true }
}
];
const route = createRouter({
    history: createWebHashHistory(),
    routes
})
export default route