// import { RootState, useAppSelector } from "@/redux";
// import axios, { AxiosError, AxiosResponse, AxiosInstance, AxiosRequestConfig } from "axios";

// // type
// import { ResponseType } from '@/types/http/responseType'
// import { message } from 'antd'

// const config = {
//   // 设置header
//   headers: {
//     Accept: 'application/json',
//     'Content-Type': 'application/json'
//   },
//   // 设置超时时间（10s）
//   timeout: 10000,
//   // 跨域时候允许携带凭证
//   withCredentials: true
// }


// // const axiosCanceler = new AxiosCanceler();

// class HttpIntercept {
//   private axiosInstance: AxiosInstance;
//   public constructor(config: AxiosRequestConfig) {
//     // 实例化axios
//     this.axiosInstance = axios.create(config);

//     /**
//      * 请求拦截
//      */
//     this.axiosInstance.interceptors.request.use(
//       (config: AxiosRequestConfig) => {
//         const { access_token: accessToken } = useAppSelector((state: RootState) => state.global)
//         if (accessToken) {
//           config.headers.Authorization = accessToken
//         }
//         console.log('--> request intercept config', config)
//       },

//       (error: AxiosError) => {
//         console.log('--> request intercept error:', error)
//         return Promise.reject(error)
//       }
//     );

//     /**
//      * 响应拦截
//      * 服务器换返回信息 -> [拦截统一处理] -> 客户端JS获取到信息
//      */
//     this.axiosInstance.interceptors.response.use(
//       (response: AxiosResponse) => {
//         console.log('--> interceptors.response:', response)
//         const { data, config, headers, request, status, statusText } = response
//         console.log('--> response data:', data)
//         const { code, msg, token, userInfo } = data.data
//         console.log('--> response token:', token)
//         // if (token) {
//         // 	setGlobalToken(token)
//         // }

//         if (status === 200) {
//           if (data.code !== 0) {
//             message.error(data.msg)
//             return response
//           }
//           return response
//         } else {
//           message.error('网络连接异常,请稍后再试!')
//           return response
//         }
//       },

//       // 请求失败
//       (error: any) => {
//         const { response } = error
//         // console.log('--> response.error:', error)
//         if (response) {
//           // 请求已发出, 但是不在2xx的范围
//           console.log('--> 请求已发出, 但是不在2xx的范围 -> response.code:', response.data.status)
//           message.error(`${response.data.status} ->  ${response.data.error}`)

//           // const errorResp = Promise.reject(response.data)
//           // console.log('--> errorResp:', errorResp)
//           const respData = { code: response.data.status, msg: response.data.error, data: '' }
//           return respData
//         } else {
//           message.error('网络连接异常,请稍后再试!')
//         }
//       }
//     )
//   }

// }