import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {
  private PATH_TO_CONTROLLER = environment.backend + '/purchase/';

  constructor(private http: HttpClient) {

  }

  getLastPurchase(userId) {
    return this.http.get(this.PATH_TO_CONTROLLER + 'last-purchase',
      {params: new HttpParams().set('userId', userId)});
  }

  getPurchaseHistory(userId) {
    return this.http.get(this.PATH_TO_CONTROLLER + 'history',
      {params: new HttpParams().set('userId', userId)});
  }
}
