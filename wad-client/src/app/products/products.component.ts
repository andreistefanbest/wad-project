import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {ProductsService} from './products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html'
})
export class ProductsComponent implements OnInit, OnDestroy {
  private subscription: Subscription;
  private products: any;
  private inputs: [];

  constructor(private productsService: ProductsService) {
    this.subscription = new Subscription();
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

}
