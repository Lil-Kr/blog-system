import { MenuUnfoldOutlined, MenuFoldOutlined } from '@ant-design/icons'
import { Breadcrumb, Layout } from 'antd'
import React, { useState } from 'react'
import BreadcrumbNav from './breadcrumbNav/BreadcrumbNav'
import CollapsIcon from './collapsIcon/CollapsIcon'

const { Header } = Layout

const HeaderLayout = (props) => {
  let { collapsed, setCollapsed } = props
  return (
    <Header className="site-layout-background" style={{ padding: 0 }}>
      {/* collaps icon */}
      <CollapsIcon collapsed={collapsed} setCollapsed={setCollapsed} />
      {/* breadcrumb nav */}
      <BreadcrumbNav />
    </Header>
  )
}

export default HeaderLayout
