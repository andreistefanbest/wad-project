import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class ProductsService {
  private PATH_TO_CONTROLLER = 'http://localhost:1337/localhost:8080/product/';

  constructor(private http: HttpClient) {

  }

  public getProducts() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'getProducts');
  }
}
