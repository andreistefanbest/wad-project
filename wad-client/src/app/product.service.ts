import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  API_URL = 'api/';

  constructor(public httpClient: HttpClient) {

  }

  getProducts() {
    return this.httpClient.get(this.API_URL + 'products');
  }
  getProduct(id) {
    return this.httpClient.get(this.API_URL + 'product?productId=' + id);
  }

}
