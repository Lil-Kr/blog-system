import React from 'react'
import { useLocation } from 'react-router-dom'
import { breadcrumbMap } from '@/routers'

import { Breadcrumb } from 'antd'

const BreadcrumbNav = () => {
	const { pathname } = useLocation()
	const breadcrumbs: string[] = breadcrumbMap.get(pathname) || []
	console.log('--> breadcrumbs info:', breadcrumbs)
	return (
		<>
			<Breadcrumb>
				{breadcrumbs.map((item: string, index: number) => {
					return <Breadcrumb.Item key={index}>{item}</Breadcrumb.Item>
				})}
			</Breadcrumb>
		</>
	)
}

export default BreadcrumbNav
