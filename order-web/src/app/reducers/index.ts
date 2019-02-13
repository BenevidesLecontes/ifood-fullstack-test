import {
  ActionReducerMap, createFeatureSelector, createSelector,
  MetaReducer
} from '@ngrx/store';
import { environment } from '../../environments/environment';
import {ordersReducer, OrdersState, selectAll} from './order';

export interface State {
  orders: OrdersState;
}

export const reducers: ActionReducerMap<State> = {
  orders: ordersReducer
};


export const metaReducers: MetaReducer<State>[] = !environment.production ? [] : [];


const selectOrders = createFeatureSelector<OrdersState>('orders');

export const selectAllOrders = createSelector(
  selectOrders,
  selectAll
);
export const selectLoading = createSelector(
  selectOrders,
  (orderState: OrdersState) => orderState.loading
);

export const selectPage = createSelector(
  selectOrders,
  (orderState: OrdersState) => orderState.page
);
