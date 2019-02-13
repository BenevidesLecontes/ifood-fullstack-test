import { Injectable } from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {GetOrders, GetOrdersError, GetOrdersSuccess, OrderActionTypes} from './actions/order';
import {catchError, map, mergeMap} from 'rxjs/operators';
import {of} from 'rxjs';
import {OrdersService} from './services/orders.service';

@Injectable()
export class AppEffects {
  @Effect()
  getOrders$ = this.actions$.pipe(
    ofType<GetOrders>(OrderActionTypes.GetOrders),
    mergeMap(action =>
      this.ordersService.retrieveOrders(action.payload)
    ),
    map(({ list, page }) => new GetOrdersSuccess({ orders: list, page })),
    catchError(err => {
      return of(new GetOrdersError(err));
    }));
  constructor(private actions$: Actions, private ordersService: OrdersService) {}
}
