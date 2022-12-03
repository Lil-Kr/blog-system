import { useTranslation } from 'react-i18next'
import { RootState, useAppDispatch, useAppSelector } from '@/redux'
import { setLanguage } from '@/redux/modules/global'
import LoginForm from './components/LoginForm'

import { Button, Form, Input, Checkbox } from 'antd'
import { LockOutlined, UserOutlined } from '@ant-design/icons'
import styles from './index.module.scss'

const Login = () => {
	return (
		<div className={styles.containerLogin100}>
			<LoginForm />
		</div>
	)
}

export default Login
