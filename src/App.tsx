import { useState } from 'react'
import { Button, Spin } from 'antd'
import { routers } from '@/routers'
import { useRoutes, NavLink, Outlet } from 'react-router-dom'
import MainLayout from './layout'

function App() {
	const outlet = useRoutes(routers)

	// let array = [1]
	
	// console.log('--> array1:', array)
	// array.splice(0,1)
	// console.log('--> array:', array)

	// if (array1) {
	// 	console.log('--> true')
	// } else {
	// 	console.log('--> false')
	// }

	// let testMap = new Map([
	// 	['a', 1],
	// 	['b', 2],
	// 	['c', 34444]
	// ])
	// testMap.set('c',4)
	// testMap.set('c',55)

	// console.log('--> map:', testMap.get('c'))

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
