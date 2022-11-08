import React from 'react'
import ReactDOM from 'react-dom/client'
// 自动去除默认样式, 初始化样式, 接下来才是引入自己的样式
import 'reset-css'

// 引入全局样式
import '@/assets/styles/global.scss'

import App from './App'
import { HashRouter } from 'react-router-dom'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <HashRouter>
      <App />
    </HashRouter>
  </React.StrictMode>
)
