import React, { useState } from 'react'
import Logo from './logo/Logo'
import { Breadcrumb, Layout, Menu, MenuProps } from 'antd'
import { UserOutlined, VideoCameraOutlined, UploadOutlined } from '@ant-design/icons'
import { useNavigate, useLocation } from 'react-router-dom'
import { MenuItemType, SubMenuType } from '../interfaces'
import { getMenuOpenKeysUtil } from '@/utils/common'

const MenuLayout = () => {
  // Menu data
  const items: MenuItemType[] = [
    {
      key: 'blog',
      icon: <UserOutlined />,
      label: '博客管理',
      children: [
        {
          key: '/blog',
          icon: <UserOutlined />,
          label: '博客管理'
        },
        {
          key: '/blogType',
          icon: <UserOutlined />,
          label: '分类管理'
        }
      ]
    },
    {
      key: 'user',
      icon: <VideoCameraOutlined />,
      label: '用户管理',
      children: [
        {
          key: '/user',
          icon: <UserOutlined />,
          label: '用户管理'
        },
        {
          key: '/touristische',
          icon: <UserOutlined />,
          label: '游客管理'
        }
      ]
    },
    {
      key: 'band',
      icon: <VideoCameraOutlined />,
      label: '乐队管理',
      children: [
        {
          key: '/band',
          icon: <UserOutlined />,
          label: '乐队管理'
        }
      ]
    },
    {
      key: 'permission',
      icon: <VideoCameraOutlined />,
      label: '权限管理',
      children: [
        {
          key: '/speisekarte',
          icon: <UserOutlined />,
          label: '菜单管理'
        },
        {
          key: '/role',
          icon: <UserOutlined />,
          label: '角色管理'
        },
        {
          key: '/premiss',
          icon: <UserOutlined />,
          label: '权限管理'
        }
      ]
    },
    {
      key: 'system',
      icon: <UploadOutlined />,
      label: '系统管理',
      children: [
        {
          key: '/configuration',
          icon: <UserOutlined />,
          label: '系统配置'
        }
      ]
    },
    {
      key: '/test',
      icon: <UploadOutlined />,
      label: '测试'
    }
  ]

  const navigateTo = useNavigate()
  const { pathname } = useLocation()
  const [openKeys, setOpenKeys] = useState<string[]>()
  const [defaultSelectKeys, setDefaultSelectKeys] = useState<string[]>([pathname])

  /**
   * jump content page
   * @param e
   */
  const handleMenu = (e: SubMenuType) => {
    const { key, keyPath } = e
    console.log('--> key:', key)
    console.log('--> keyPath:', keyPath)

    // set default selected sub-menu
    setDefaultSelectKeys([pathname])
    navigateTo(key)
  }

  // set default open menu
  // 找到菜单路由配置中与pathname 相匹配的key的所有父节点
  const keys: string[] = getMenuOpenKeysUtil(items, pathname)
  // 初始化菜单是否展开状态
  const [defaultOpenKeys, setDefaultOpenKey] = useState<string[]>(keys)

  /**
   * handle open/close menu
   * @param menuKeysArry
   */
  const handleOpenMenu = (menuKeys: string[]) => {
    console.log('--> 选中菜单', menuKeys)
    console.log('--> defaultOpenKeys:', defaultOpenKeys)
    setOpenKeys(menuKeys)
    setDefaultOpenKey(menuKeys)
  }

  return (
    <>
      <Logo />
      <Menu
        // style={{ minWidth: 0, flex: 'auto' }}
        theme="dark"
        mode="inline"
        // default open menu
        defaultOpenKeys={defaultOpenKeys}
        openKeys={openKeys}
        items={items}
        // 初始选中的菜单项 key 数组
        defaultSelectedKeys={defaultSelectKeys}
        // 当前选中的菜单项 key 数组
        // selectedKeys={selectedKeys}
        // inlineCollapsed={true}
        onOpenChange={handleOpenMenu}
        onClick={handleMenu}
      />
    </>
  )
}

export default MenuLayout
