import { MenuProps } from "antd"

type MenuItem = Required<MenuProps>['items'][number]

interface MenuItemType {
  key: string
  icon: any
  label: string
  flage?: string
  children?: MenuItemType[]
}

interface SubMenuType {
  key: string
  keyPath: string[]
}

export type { MenuItemType, SubMenuType }