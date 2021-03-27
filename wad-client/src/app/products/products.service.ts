import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable()
export class ProductsService {
  private PATH_TO_CONTROLLER = 'http://localhost:8080/product/';

  constructor(private http: HttpClient) {

  }

  public getProducts() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'getProducts');
  }

  public buy(purchase: {}) {
    return this.http.put(this.PATH_TO_CONTROLLER + 'buy', purchase);
  }

  public history(userId) {
    const params = new HttpParams().set('userId', '1');
    return this.http.get(this.PATH_TO_CONTROLLER + 'history', {params});
  }
}
