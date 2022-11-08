import React, { useState } from 'react'

import MenuLayout from './menu'
import HeaderLayout from './header'
import ContentLayout from './content'
import FooterLayout from './footer/FooterLayout'
import { Layout } from 'antd'

// css
import styles from './mainLayout.module.scss'

const { Sider } = Layout
const MainLayout = () => {
  const [collapsed, setCollapsed] = useState(false)

  return (
    <div className={styles.container}>
      <Sider trigger={true} collapsed={collapsed}>
        <MenuLayout />
      </Sider>
      <Layout className="site-layout">
        <HeaderLayout collapsed={collapsed} setCollapsed={setCollapsed} />
        {/* <LayoutTabs></LayoutTabs> */}
        <ContentLayout />
        <FooterLayout />
      </Layout>
    </div>
  )
}

export default MainLayout
