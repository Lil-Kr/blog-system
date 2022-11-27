import React from 'react'
import { useRoutes, Outlet } from 'react-router-dom'

import { Layout } from 'antd'

const { Content } = Layout
const ContentLayout = () => {
	return (
		<>
			<Content
				className="site-layout-background content-layout"
				style={{
					margin: '0 15px 0',
					minHeight: '280'
				}}
			>
				<Outlet />
			</Content>
		</>
	)
}

export default ContentLayout
