import React, { lazy } from 'react'
import { MenuProps, Spin } from 'antd'
// 重定向组件
import { Navigate, RouteObject } from 'react-router-dom'

// css
import styles from './spin.module.scss'

const config = () => {
  return <></>
}

// lazy loading
const MainLayout = lazy(() => import('@/layout'))
const Home = lazy(() => import('@/pages/home'))
const Band = lazy(() => import('@/pages/band'))
const User = lazy(() => import('@/pages/user/User'))
const Touristische = lazy(() => import('@/pages/user/Touristische'))
const Login = lazy(() => import('@/pages/login'))
const Blog = lazy(() => import('@/pages/blog'))
const System = lazy(() => import('@/pages/system'))

const withLoadingComponent = (comp: JSX.Element) => (
  <React.Suspense
    fallback={
      <div>
        <Spin size="large" className={styles.spinLargeStyle} />
      </div>
    }
  >
    {comp}
  </React.Suspense>
)

const routers = [
  {
    path: '/',
    element: <Navigate to="/login" replace={true} />
  },
  {
    path: '/login',
    element: withLoadingComponent(<Login />)
  },
  {
    path: '/',
    element: withLoadingComponent(<MainLayout />),
    children: [
      {
        path: '/home',
        element: withLoadingComponent(<Home />)
      },
      {
        path: '/blog',
        element: withLoadingComponent(<Blog />)
      },
      {
        path: '/blogType',
        element: withLoadingComponent(<Blog />)
      },
      {
        path: '/band',
        element: withLoadingComponent(<Band />)
      },
      {
        path: '/user',
        element: withLoadingComponent(<User />)
      },
      {
        path: '/touristische',
        element: withLoadingComponent(<Touristische />)
      },
      {
        path: '/system',
        element: withLoadingComponent(<System />)
      }
    ]
  }
]

export default config
export { routers }
