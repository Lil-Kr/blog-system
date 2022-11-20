import React, { Suspense, lazy } from 'react'
import { RouteItemType } from '@/types/router/routeType'
import { lazyLoadUtil } from '@/utils/router'
import busConfig, { testConfig, homeConfig } from './modules'
import { handleRouterItems } from '@/utils/router/routerCommonUtil'
import { UserOutlined } from '@ant-design/icons'
import { getBreadCrumbItems, getMenuItems } from '@/utils/common'

const config = () => {
	return <></>
}

const routersConfig: RouteItemType[] = [
	{
		meta: {
			key: '/',
			title: '重定向到登录page',
			icon: <UserOutlined />
		},
		path: '/',
		// redirect: <Navigate to="/login" replace={true} />
		redirect: '/login'
	},
	{
		meta: {
			key: '/login',
			title: '登录',
			icon: <UserOutlined />
		},
		path: '/login',
		element: lazyLoadUtil(lazy(() => import('@/pages/login')))
	},
	{
		meta: {
			key: '/loginOut',
			title: '退出登录',
			icon: <UserOutlined />
		},
		path: '/loginOut',
		element: lazyLoadUtil(lazy(() => import('@/pages/login')))
	},
	...busConfig
]
// handle router structure
const routers = handleRouterItems(routersConfig)
console.log('--> 处理后的路由表:', routers)

// generate menu structure
const menuItems = getMenuItems(routersConfig)
// console.log('--> 处理后的菜单结构:', JSON.stringify(menuItems))
console.log('--> 处理后的菜单结构:', menuItems)

// generate breadcrumb nav
const breadcrumbMap = getBreadCrumbItems(routersConfig)
console.log('--> 处理后的面包屑结构:', breadcrumbMap)

export default config
export { routersConfig, routers, menuItems, breadcrumbMap }
