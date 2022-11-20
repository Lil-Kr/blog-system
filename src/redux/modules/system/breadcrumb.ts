/**
 * definition breadcrumb slice
 */
import { createSlice, PayloadAction } from "@reduxjs/toolkit"

import { BreadcrumbStateType } from "@/types/common/breadcrumbType"

const breadcrumbState: BreadcrumbStateType = {
  breadcrumbMap: null
}

const breadcrumbSlice = createSlice({
  name: "breadcrumb", // 自动生成action中的type
  initialState: breadcrumbState,
  reducers: {
    setBreadcrumbMap(state: BreadcrumbStateType, action: PayloadAction<{ [propName: string]: any }>) {
      const { payload } = action
      state.breadcrumbMap = payload.breadcrumbMap
    },

    setBreadcrumbMapByKey(state: BreadcrumbStateType, action: PayloadAction<{ [propName: string]: any }>) {
      const { payload } = action

    }
  }
})


export const { setBreadcrumbMap, setBreadcrumbMapByKey } = breadcrumbSlice.actions
export const { reducer: breadcrumbReducer } = breadcrumbSlice