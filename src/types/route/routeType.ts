import { IndexRouteObject, RouteObject } from 'react-router-dom'


interface FunctionalImportType {
  (): any
}

interface MetaType {
  [propName: string]: any
}

// interface RouteItemType extends RouteObject {
//   path: string
//   redirect?: string
//   component?: FunctionalImportType
//   meta?: MetaType
//   children?: RouteItemType[]
// }