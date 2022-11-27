import { useState } from 'react'
import { Button, Spin } from 'antd'
import { routers } from '@/routers'
import { useRoutes, NavLink, Outlet } from 'react-router-dom'
import MainLayout from './layout'
import { CssSyntaxError } from 'postcss'

const App = () => {
	const outlet = useRoutes(routers)
	return (
		<>
			{/* <div className="App">顶层组件</div>
      <Button type="primary">Button</Button> */}
			{/* <ul>
        <li>
          <NavLink to="/home">home信息</NavLink>
        </li>
        <li>
          <NavLink to="/band">乐队信息</NavLink>
        </li>
        <li>
          <NavLink to="/user">用户信息</NavLink>
        </li>
      </ul>
      {outlet}
      <Outlet /> */}
			{/* <MainLayout /> */}
			{/* <Spin className="spin-large-style" /> */}
			{outlet}
		</>
	)
}

export default App
