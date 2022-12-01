import { SvgIcon } from '@/assets/images'
import ArticleCreate from '@/assets/images/svg/article-create.svg'
import { useTranslation } from 'react-i18next'

import { Button } from 'antd'

const Login = () => {
	const { t } = useTranslation()
	// console.log('--> t:', t)

	// const { language } = useAppSelector((state: RootState) => state.global)
	// const dispatch = useAppDispatch()

	// const clickMe = () => {
	// 	console.log('--> aaaa:')
	// 	const a = language === 'zh' ? 'en' : 'zh'
	// 	dispatch(setLanguage(a))
	// }

	return (
		<>
			<br />
			<ArticleCreate />
			<br />
			Login
			<br />
			{/* <Button type="primary" onClick={clickMe}>
				{t('test.btn')}
			</Button> */}
		</>
	)
}

export default Login
