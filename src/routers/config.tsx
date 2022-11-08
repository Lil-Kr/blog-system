import React, { lazy } from 'react'
// 重定向组件
import { Navigate, RouteObject } from 'react-router-dom'

const config = () => {
  return <div>config</div>
}

// lazy loading
const Home = lazy(() => import('@/pages/home'))
const Band = lazy(() => import('@/pages/band'))
const User = lazy(() => import('@/pages/user'))

const withLoadingComponent = (comp: JSX.Element) => (
  <React.Suspense fallback={<div>loading...</div>}>{comp}</React.Suspense>
)

const routers = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    element: withLoadingComponent(<Home />)
  },
  {
    path: '/band',
    element: withLoadingComponent(<Band />)
  },
  {
    path: '/user',
    element: withLoadingComponent(<User />)
  }
]

export default config
export { routers }
