import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useTranslation } from 'react-i18next'
import md5 from 'js-md5'
import { Login } from '@/types/login'
// redux
import { useAppDispatch } from '@/redux'
import { useLoginMutation } from '@/redux/apis/login/loginApi'
// stlyes
import { LockOutlined, UserOutlined } from '@ant-design/icons'
import { Button, Form, Input } from 'antd'
import styles from '../index.module.scss'

const LoginForm = () => {
	const dispatch = useAppDispatch()
	const { t } = useTranslation()
	const navigate = useNavigate()
	const [form] = Form.useForm()
	const [loading, setLoading] = useState<boolean>(false)

	const onFinishFailed = (errorInfo: any) => {
		console.log('Failed:', errorInfo)
		console.log('--> form:', form)
	}

	const [
		loginFn,
		{
			data: loginResp,
			isSuccess: isLoginSuccess,
			isError: isLoginError,
			isUninitialized: isloginUninitialized
		}
	] = useLoginMutation()
	/**
	 * login
	 * @param values
	 */
	const onFinish = (loginInfo: Login.LoginFormType) => {
		let { username, password } = loginInfo
		// console.log('success:', username)
		// console.log('success:', md5(password))
		// console.log('--> form:', form)
		loginFn(loginInfo).then((res) => {
			console.log('--> res:', res)
		})
	}

	return (
		<div className={styles.lContainer}>
			<div className={styles.lItem}>
				<div className={styles.loginForm}>
					<div className="login-form-title">{t('login.title')}</div>
					<Form
						className="login-form"
						name="basic"
						layout="horizontal"
						initialValues={{ remember: true }}
						onFinish={onFinish}
						onFinishFailed={onFinishFailed}
						autoComplete="off"
					>
						<Form.Item
							name="username"
							rules={[{ required: true, message: t('login.username_message') }]}
						>
							<Input
								prefix={<UserOutlined className="site-form-item-icon" />}
								placeholder={t('login.username_placeholder')}
							/>
						</Form.Item>

						<Form.Item
							name={'password'}
							rules={[{ required: true, message: t('login.password_message') }]}
						>
							<Input.Password
								prefix={<LockOutlined className="site-form-item-icon" />}
								type="password"
								placeholder={t('login.password_placeholder')}
							/>
						</Form.Item>

						<Form.Item>
							<Button
								type="primary"
								loading={loading}
								htmlType="submit"
								className="login-form-button"
							>
								{t('login.btn')}
							</Button>
						</Form.Item>
					</Form>
				</div>
			</div>
		</div>
	)
}

export default LoginForm
