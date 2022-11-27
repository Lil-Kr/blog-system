import React, { useState } from 'react'
import { MenuUnfoldOutlined, MenuFoldOutlined } from '@ant-design/icons'
import { Breadcrumb, Layout, Space } from 'antd'
import BreadcrumbNav from './breadcrumbNav/BreadcrumbNav'
import CollapsIcon from './collapsIcon/CollapsIcon'

import './index.scss'

const { Header } = Layout

const HeaderLayout = (props) => {
	let { collapsed, setCollapsed } = props
	return (
		<Header className="site-layout-background header-layout" style={{ padding: 0 }}>
			<div className="header-lf">
				{/* collaps icon */}
				<CollapsIcon collapsed={collapsed} setCollapsed={setCollapsed} />
				{/* breadcrumb nav */}
				<BreadcrumbNav />
			</div>
			<div className="header-ri">
				<Space>
					<div>语言切换</div>
					<div>主题更改</div>
					<div>是否全屏</div>
					<div>用户信息</div>
				</Space>
			</div>
		</Header>
	)
}

export default HeaderLayout
