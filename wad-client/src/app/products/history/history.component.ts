import { Component, OnInit } from '@angular/core';
import {ProductsService} from '../products.service';
import {take} from 'rxjs/operators';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  purchases: {};

  constructor(private productService: ProductsService) { }

  ngOnInit() {
    this.productService.history(1).pipe(take(1)).subscribe((history) => {
      this.purchases = history;
    });
  }

}
