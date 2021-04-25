import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {GlobalConstants} from './utils/GlobalConstants';
import {NewPurchaseDTO} from './phones/buy-phone/dto/NewPurchaseDTO';
import {environment} from '../environments/environment';

@Injectable()
export class UserService {
  public userChanged: EventEmitter<void> = new EventEmitter();
  private PATH_TO_CONTROLLER = environment.backend + '/user/';

  constructor(private http: HttpClient) {

  }

  public signIn(name, mail, password) {
    let httpParams: HttpParams = new HttpParams();
    httpParams = httpParams.set('name', name);
    httpParams = httpParams.set('mail', mail);
    httpParams = httpParams.set('password', password);

    return this.http.post(this.PATH_TO_CONTROLLER + 'signIn',
      undefined, {params: httpParams});
  }

  public logIn(mail, password) {
    let httpParams: HttpParams = new HttpParams();
    httpParams = httpParams.set('mail', mail);
    httpParams = httpParams.set('password', password);

    return this.http.post(this.PATH_TO_CONTROLLER + 'logIn',
      undefined, {params: httpParams});
  }

  public buyPhone(purchase: NewPurchaseDTO) {
    return this.http.post(this.PATH_TO_CONTROLLER + 'buyPhone', purchase, {responseType: 'text'});
  }

  public getUser(id: number) {
    return this.http.get(this.PATH_TO_CONTROLLER + 'getUser?id=' + id);
  }

  public getCurrentUser() {
    return JSON.parse(localStorage.getItem(GlobalConstants.LOGGED_USER_KEY));
  }
}
