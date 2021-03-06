import Vue from 'vue'
import store from './store'
import Router from 'vue-router'
import Login from './views/Login'
import Logout from './views/Logout'
import Denied from './views/Denied'
import Index from './views/Index'

Vue.use(Router)

const requireAuth = () => (from, to, next) => {
    const isAuthenticated = store.state.accessToken ? 1 : 0
    if (isAuthenticated) return next()
    next('/login')
}

const requireRootAuth = () => (from, to, next) => {
    const isAuthenticated = store.state.accessToken ? 1 : 0
    if (isAuthenticated) return next('/index')
    next('/login')
}

export default new Router({
    routes: [
        {
            path: '/login',
            component: Login
        },
        {
            path: '/logout',
            component: Logout,
            beforeEnter: requireAuth()
        },
        {
            path: '/index',
            component: Index,
            beforeEnter: requireAuth()
        },
        {
            path: '/',
            beforeEnter: requireRootAuth()
        },
        {
            path: '/*',
            component: Denied
        }
        
    ]
})
