import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {ProductsService} from './products.service';
import {count, take} from 'rxjs/operators';
import {UserService} from '../user.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html'
})
export class ProductsComponent implements OnInit, OnDestroy {
  private subscription: Subscription;
  private products: any;
  private inputs: [];
  loggedIn: boolean;
  showSum: boolean;
  sum: number;

  constructor(private productsService: ProductsService,
              private userService: UserService) {
    this.subscription = new Subscription();
    this.loggedIn = this.userService.getCurrentUser() != null;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.inputs = [];
    this.subscription.add(this.productsService.getProducts().subscribe((products) => {
      this.products = products;
    }));
  }

  calculate($event: MouseEvent) {
    this.sum = 0;
    this.inputs.forEach((item, index) => {
      // @ts-ignore
      if (item === undefined || item.toString().trim() === '') {
        return;
      }
      this.sum += item as number * this.products[index].unitPrice;
    });
    this.showSum = true;
  }

  buy($event: MouseEvent) {
    if (!this.loggedIn) {
      alert('You must be logged in in order to place an order.');
      return;
    }
    let counter = 0;
    this.inputs.forEach((item, index) => {
      // @ts-ignore
      if (item === undefined || item.toString().trim() === '') {
        return;
      }
      counter += item * 1;
    });
    this.productsService.buy({userId: 1, numOfItems: counter, totalPrice: this.sum}).pipe(take(1)).subscribe();
  }
}
