/**
 * 配置 persist
 */
import { combineReducers } from "@reduxjs/toolkit"
import { persistReducer } from "redux-persist"
import storage from 'redux-persist/lib/storage'
import { breadcrumbReducer } from "@/redux/modules/system/breadcrumb"
import { tabReducer } from "@/redux/modules/system/tabs"
import { globalReducer } from "../global"

const rootPersistConfig = {
  /**
   * 储存的标识名
   */
  key: 'root',
  /**
   * 储存方式
   */
  storage: storage,
  whitelist: ['breadcrumb', 'tab', 'global']
}

const breadcrumbPersistConfig = {
  key: 'breadcrumb',
  storage: storage,
  whitelist: ['breadcrumb']
}

const tabPersistConfig = {
  key: 'tab',
  storage: storage,
  whitelist: ['tab']
}

/**
 * root reducer
 */
// const rootReducer = combineReducers({
//   breadcrumb: persistReducer(breadcrumbPersistConfig, breadcrumbReducer),
//   tab: persistReducer(tabPersistConfig, tabReducer),
// })
const rootReducer = combineReducers({
  breadcrumb: breadcrumbReducer,
  tab: tabReducer,
  global: globalReducer
})


const rootPersistReducer = persistReducer(rootPersistConfig, rootReducer)

export default rootPersistReducer