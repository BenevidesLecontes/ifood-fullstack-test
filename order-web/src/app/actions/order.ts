import {Action} from '@ngrx/store';
import {Order, Page} from '../models/order';
import {HttpErrorResponse} from '@angular/common/http';


export enum OrderActionTypes {
  GetOrders = 'GET ORDERS',
  GetOrdersSuccess = 'GET ORDERS SUCCESS',
  GetOrdersError = 'GET ORDERS ERROR',
}


export class GetOrders implements Action {

  readonly type = OrderActionTypes.GetOrders;

  constructor(public payload?: { [key: string]: any }) {

  }
}

export class GetOrdersSuccess implements Action {

  readonly type = OrderActionTypes.GetOrdersSuccess;

  constructor(public payload: { orders: Order[], page: Page }) {

  }

}

export class GetOrdersError implements Action {

  readonly type = OrderActionTypes.GetOrdersError;

  constructor(public payload: { error: HttpErrorResponse }) {

  }

}


export type OrderActions = GetOrders | GetOrdersSuccess | GetOrdersError;




