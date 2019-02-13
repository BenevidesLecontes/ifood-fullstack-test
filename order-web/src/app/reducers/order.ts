import {createEntityAdapter, EntityAdapter, EntityState} from '@ngrx/entity';
import {Order, Page} from '../models/order';
import {OrderActions, OrderActionTypes} from '../actions/order';
import {HttpErrorResponse} from '@angular/common/http';

export interface OrdersState extends EntityState<Order> {
  loading: boolean;
  error?: HttpErrorResponse;
  page?: Page;
}

export const adapter: EntityAdapter<Order> = createEntityAdapter<Order>();

export const initialOrdersState: OrdersState = adapter.getInitialState({
  loading: false
});

export function ordersReducer(
  state = initialOrdersState,
  action: OrderActions
): OrdersState {
  switch (action.type) {
    case OrderActionTypes.GetOrders:
      return {...state, loading: true};

    case OrderActionTypes.GetOrdersSuccess:
      return adapter.addAll(action.payload.orders, {
        ...state,
        loading: false,
        page: action.payload.page
      });

    case OrderActionTypes.GetOrdersError:
      const {error} = action.payload;
      return {...state, error, loading: false};

    default: {
      return state;
    }
  }
}


export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal

} = adapter.getSelectors();
