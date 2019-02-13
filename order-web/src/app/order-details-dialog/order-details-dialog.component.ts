import {Component, Inject, OnInit} from '@angular/core';
import {OrdersService} from '../services/orders.service';
import {Observable, of} from 'rxjs';
import {Order, OrderItems} from '../models/order';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {map, switchMap, take} from 'rxjs/operators';

@Component({
  selector: 'app-order-details-dialog',
  templateUrl: './order-details-dialog.component.html',
  styleUrls: ['./order-details-dialog.component.scss']
})
export class OrderDetailsDialogComponent implements OnInit {
  displayedColumns: string[] = [
    'description',
    'quantity',
    'price',
    'total'
  ];

  items$: Observable<OrderItems[]>;

  constructor(private ordersService: OrdersService, private dialogRef: MatDialogRef<OrderDetailsDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Order) { }

  ngOnInit() {
    this.dialogRef.afterOpened().pipe(take(1))
      .subscribe(e => this.getOrderItems());
  }

  getOrderItems() {
    this.items$ = this.ordersService
      .retrieveOrderItems(this.data.id)
      .pipe(map(items => {
        return items.map(item => {
          return {
            ...item,
            total: item.quantity * item.price
          };
        });
      }));
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
