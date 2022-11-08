import { useState } from 'react'
import { Button } from 'antd'
import { routers } from '@/routers'
import { useRoutes, NavLink, Outlet } from 'react-router-dom'
import MainLayout from '@/layout'

function App() {
  const [count, setCount] = useState(0)

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
      {outlet}
    </>
  )
}

export default App
