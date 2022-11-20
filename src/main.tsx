import React from 'react'
// import ReactDOM from 'react-dom/client'
import ReactDOM from 'react-dom'

import App from './App'
import { HashRouter } from 'react-router-dom'

// stroe
import store from '@/redux'
import { Provider } from 'react-redux'

// 自动去除默认样式, 初始化样式, 接下来才是引入自己的样式
import 'reset-css'

// 引入全局样式
import '@/assets/styles/global.scss'

/**
 * 使用 ReactDOM.render 渲染, 解决 react18(ReactDOM.createRoot) 并发
 * https://github.com/react-component/menu/pull/551
 * https://github.com/ant-design/ant-design/issues/38534
 */
ReactDOM.render(
	<Provider store={store}>
		<HashRouter>
			<App />
		</HashRouter>
	</Provider>,
	document.getElementById('root')
)

// react 18 使用 ReactDOM.createRoot 渲染, antd Menu 菜单(子菜单组件) 在收缩展开时会闪烁, 先使用ReactDOM.render渲染组件, 等待官方修复
// ReactDOM.createRoot(document.getElementById('root')).render(
// 	// <React.StrictMode>
// 	// </React.StrictMode>
// 	<HashRouter>
// 		<App />
// 	</HashRouter>
// )
