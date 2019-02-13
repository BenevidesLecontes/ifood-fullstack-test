import {Component, OnInit} from '@angular/core';
import {select, Store} from '@ngrx/store';
import {selectAllOrders, selectLoading, selectPage, State} from './reducers';
import {Observable} from 'rxjs';
import {Order, Page} from './models/order';
import {GetOrders} from './actions/order';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialog} from '@angular/material';
import {OrderDetailsDialogComponent} from './order-details-dialog/order-details-dialog.component';
import {markFormGroup} from './utils/markFormGroup';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  formFilter = new FormGroup({
    start: new FormControl(''),
    end: new FormControl(''),
    name: new FormControl(''),
    phone: new FormControl(''),
    email: new FormControl('', Validators.email)
  });
  displayedColumns: string[] = [
    'confirmedAt',
    'name',
    'phone',
    'email',
    'total'
  ];
  orders$: Observable<Order[]>;
  loading$: Observable<boolean>;
  page$: Observable<Page>;

  constructor(private store: Store<State>, private dialog: MatDialog) {
    this.store.dispatch(new GetOrders());
  }

  get minDate(): any {
    return this.formFilter ? this.formFilter.get('start').value : null;
  }

  ngOnInit(): void {
    this.orders$ = this.store.pipe(select(selectAllOrders));
    this.loading$ = this.store.pipe(select(selectLoading));
    this.page$ = this.store.pipe(select(selectPage));
  }

  openDialog(selectedOrder: Order): void {
    const dialogRef = this.dialog.open(OrderDetailsDialogComponent, {
      data: selectedOrder,
      width: '700px'
    });
  }

  setDateValidators() {
    const start = this.formFilter.get('start');
    const end = this.formFilter.get('end');
    if (start.value || end.value) {
      start.setValidators(Validators.required);
      end.setValidators(Validators.required);
    } else {
      start.clearValidators();
      end.clearValidators();
    }

    start.updateValueAndValidity();
    end.updateValueAndValidity();
  }

  applyFilters() {
    markFormGroup(this.formFilter);
    if (this.formFilter.valid) {
      this.store.dispatch(new GetOrders(this.formFilter.value));
    }
  }
}
