import { Component, OnInit } from '@angular/core';
import {ProductService} from '../product.service';
import {Product} from '../product';

@Component({
  selector: 'app-product-detaisl',
  templateUrl: './product-detaisl.component.html',
  styleUrls: ['./product-detaisl.component.css']
})
export class ProductDetaislComponent implements OnInit {
  product = new Product(-1, 'No product');
  constructor(public productService: ProductService) { }

  ngOnInit() {
    this.productService.getProduct(1).subscribe((a: any) => {
      this.product = a;
    });
  }

}
