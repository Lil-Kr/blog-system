import React from 'react'
import { useRoutes, Outlet } from 'react-router-dom'
import { routers } from '@/routers'
import { Layout } from 'antd'

const { Content } = Layout
const ContentLayout = () => {
  return (
    <Content
      className="site-layout-background content-layout"
      style={{
        margin: '15px 15px 0',
        minHeight: '280'
      }}
    >
      <Outlet />
    </Content>
  )
}

export default ContentLayout
