import React, { useEffect, useState } from 'react'
import Logo from './logo/Logo'
import { Breadcrumb, Button, Layout, Menu, MenuProps, Spin } from 'antd'
import { UserOutlined, VideoCameraOutlined, UploadOutlined } from '@ant-design/icons'
import { useNavigate, useLocation, useParams, useSearchParams } from 'react-router-dom'
import { MenuItemType, SubMenuType } from '@/types/common'
import { getMenuOpenKeysUtil } from '@/utils/common'
import { menuItems } from '@/routers'

const MenuLayout = (props) => {
	const { collapsed } = props

	const navigateTo = useNavigate()
	const { pathname } = useLocation()
	// console.log('--> pathname:', pathname)
	const [openKeys, setOpenKeys] = useState<string[]>([])
	const [selectedKeys, setSelectedKeys] = useState<string[]>([pathname])
	const [loading, setLoading] = useState(false)

	const keys: string[] = getMenuOpenKeysUtil(pathname)
	useEffect(() => {
		setSelectedKeys([pathname])
		collapsed ? null : setOpenKeys(keys)
	}, [pathname, collapsed])

	/**
	 * jump content page
	 * @param e
	 */
	const handleMenu = (e: SubMenuType) => {
		const { key, keyPath } = e
		// console.log('--> handleMenu -> e:', e)
		console.log('--> handleMenu -> key:', key)
		console.log('--> handleMenu -> keyPath:', keyPath)
		// const breadcrumb = breadcrumbMap.get(key)
		// console.log('--> breadcrumb:', breadcrumb)
		navigateTo(key)
	}

	/**
	 * handle open/close menu
	 * @param openKeys
	 */
	const handleOpenMenu = (openKeys: string[]) => {
		// console.log('--> handleOpenMenu -> openKeys:', openKeys)
		setOpenKeys(openKeys)
	}

	return (
		<>
			<Spin spinning={loading} tip="Loading...">
				<Logo />
				<Menu
					theme="dark"
					mode="inline"
					triggerSubMenuAction="click"
					openKeys={openKeys}
					selectedKeys={selectedKeys}
					// default open menu
					// defaultOpenKeys={defaultOpenKeys}
					items={menuItems}
					// 初始选中的菜单项 key 数组
					// defaultSelectedKeys={defaultSelectKeys}
					onClick={handleMenu}
					onOpenChange={handleOpenMenu}
				/>
			</Spin>
		</>
	)
}

export default MenuLayout
