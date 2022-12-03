import React, { useState } from 'react'
import { useTranslation } from 'react-i18next'
import { useNavigate } from 'react-router-dom'
import { useAppDispatch } from '@/redux'
import { CloseCircleOutlined, LockOutlined, UserOutlined } from '@ant-design/icons'

import { Button, Form, Input, message, Checkbox } from 'antd'

import styles from '../index.module.scss'

const LoginForm = () => {
	const dispatch = useAppDispatch()
	const { t } = useTranslation()
	const navigate = useNavigate()
	const [form] = Form.useForm()
	const [loading, setLoading] = useState<boolean>(false)

	const onFinishFailed = (errorInfo: any) => {
		console.log('Failed:', errorInfo)
	}
	const onFinish = (values: any) => {
		console.log('Success:', values)
	}

	return (
		<div className={styles.lContainer}>
			<div className={styles.lItem}>
				<div className={styles.loginForm}>
					<div className="login100-form-title">{t('login.title')}</div>
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
							rules={[{ required: true, message: t('login.usernameMessage') }]}
						>
							<Input
								prefix={<UserOutlined className="site-form-item-icon" />}
								placeholder={t('login.usernamePlaceholder')}
							/>
						</Form.Item>

						<Form.Item
							name={'password'}
							rules={[{ required: true, message: t('login.pwdMessage') }]}
						>
							<Input.Password
								prefix={<LockOutlined className="site-form-item-icon" />}
								type="password"
								placeholder={t('login.pwdPlaceholder')}
							/>
						</Form.Item>

						{/* <Form.Item name="remember" valuePropName="checked">
							<Checkbox>Remember me</Checkbox>
						</Form.Item> */}

						<Form.Item>
							<Button type="primary" htmlType="submit" className="login-form-button">
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
