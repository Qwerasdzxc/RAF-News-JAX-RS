import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [


  {
    path: '/news',
    name: 'News',
    meta: {
      authRequired: false,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/News.vue')
  },
//   {
//     path: '/news/popular',
//     name: 'Popular',
//     meta: {
//       authRequired: false,
//     },
//     // route level code-splitting
//     // this generates a separate chunk (about.[hash].js) for this route
//     // which is lazy-loaded when the route is visited.
//     component: () => import(/* webpackChunkName: "about" */ '../views/Popular.vue')
//   },
  {
    path: '/news/:id',
    name: 'NewsDetailsView',
    meta: {
      authRequired: false,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/NewsDetailsView.vue')
  },
  {
    path: '/news/tag/:id',
    name: 'NewsByTag',
    meta: {
      authRequired: false,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/NewsByTag.vue')
  },
  {
    path: '/news/category/:id',
    name: 'NewsByCategory',
    meta: {
      authRequired: false,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/NewsByCategory.vue')
  },
  {
    path: '/users/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  }
//   {
//     path: '/news',
//     name: 'CreateNews',
//     component: () => import(/* webpackChunkName: "about" */ '../views/CreateNews.vue')
//   },
//   {
//     path: '/category',
//     name: 'CreateCategory',
//     component: () => import(/* webpackChunkName: "about" */ '../views/CreateCategory.vue')
//   },
//   {
//     path: '/users',
//     name: 'CreateUser',
//     component: () => import(/* webpackChunkName: "about" */ '../views/CreateUser.vue')
//   },
//   {
//     path: '/users/:email',
//     name: 'SingleUser',
//     component: () => import(/* webpackChunkName: "about" */ '../views/SingleUser.vue')
//   },
//   {
//     path: '/category/:id',
//     name: 'UpdateCategory',
//     component: () => import(/* webpackChunkName: "about" */ '../views/UpdateCategory.vue')
//   },
//   {
//     path: '/news/update/:id',
//     name: 'UpdateNews',
//     component: () => import(/* webpackChunkName: "about" */ '../views/UpdateNews.vue')
//   },
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');

    if (!jwt) {
      next({name: 'Login'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'Login'});
      return;
    }
  }

  next();
});

export default router
