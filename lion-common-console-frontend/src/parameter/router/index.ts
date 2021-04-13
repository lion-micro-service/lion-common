import Vue from 'vue';
import VueRouter, {RouteConfig} from 'vue-router';

Vue.use(VueRouter);

const routes : Array<RouteConfig> = [{
    path:'/parameter',
    name:'参数',
    redirect:'/parameter/list'
    },{
        path:'/parameter/list',
        name:'参数列表',
        component: () => import('@/parameter/views/List.vue'),
        meta: {keepAlive: true }
    }
];

const route = new VueRouter({
    mode:'history',
    base:process.env.BASE_URL,
    routes
})

export default route