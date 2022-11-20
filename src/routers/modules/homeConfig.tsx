import React, { Suspense, lazy } from 'react'
import { RouteItemType } from '@/types/router/routeType'

import { lazyLoadUtil } from '@/utils/router'
import { UserOutlined } from '@ant-design/icons'

const homeConfig: RouteItemType[] = [
	{
		meta: {
			key: '/home',
			icon: <UserOutlined />,
			title: '首页'
		},
		path: '/home',
		element: lazyLoadUtil(lazy(() => import('@/pages/home')))
	}
]

export default homeConfig
