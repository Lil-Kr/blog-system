import React from 'react'
import Logo from './logo/Logo'
import { Breadcrumb, Layout, Menu } from 'antd'
import { UserOutlined, VideoCameraOutlined, UploadOutlined } from '@ant-design/icons'

const MenuLayout = () => {
  return (
    <>
      <Logo />
      <Menu
        theme="dark"
        mode="inline"
        defaultSelectedKeys={['1']}
        items={[
          {
            key: '1',
            icon: <UserOutlined />,
            label: 'nav 1'
          },
          {
            key: '2',
            icon: <VideoCameraOutlined />,
            label: 'nav 2'
          },
          {
            key: '3',
            icon: <UploadOutlined />,
            label: 'nav 3'
          }
        ]}
      />
    </>
  )
}

export default MenuLayout
