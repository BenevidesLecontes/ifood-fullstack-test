import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OrderItems, OrderResponse} from '../models/order';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http: HttpClient) { }

  retrieveOrders(filters: { [p: string]: any }): Observable<OrderResponse> {
    let params: HttpParams;
    let url = '/api/orders';
    if (filters && Object.keys(filters).filter(k => !!filters[k]).length) {
      params = this.buildHttpParams(filters);
      url += '/search';
    }
    return this.http.get<OrderResponse>(url, { params });
  }

  retrieveOrderItems(id: string): Observable<OrderItems[]> {
    return this.http.get<OrderItems[]>(`/api/orders/${id}`);
  }


  private buildHttpParams(filters: any) {
    let params: HttpParams = new HttpParams();
    Object.keys(filters).map(key => {

      if ((key === 'end' || key === 'start') && filters[key]) {
        const date = moment(filters[key]).format('YYYY-MM-DD');
        params = params.set(key, date);
      } else if (filters[key] && key === 'phone') {
        params = params.set(key, filters[key].replace(/[^\d]+/g, ''));
      } else if (filters[key]) {
        params = params.set(key, filters[key]);
      }
    });
    return params;
  }
}
