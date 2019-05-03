import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {GlobalConstants} from './utils/GlobalConstants';

@Injectable()
export class UserService {
  private PATH_TO_CONTROLLER = 'http://localhost:1337/localhost:8080/user/';

  constructor(private http: HttpClient) {

  }

  public signIn(name, mail, password) {
    let httpParams: HttpParams = new HttpParams();
    httpParams = httpParams.set('name', name);
    httpParams = httpParams.set('mail', mail);
    httpParams = httpParams.set('password', password);

    return this.http.put(this.PATH_TO_CONTROLLER + 'signIn',
      undefined, {params: httpParams});
  }
  public logIn(mail, password) {
    let httpParams: HttpParams = new HttpParams();
    httpParams = httpParams.set('mail', mail);
    httpParams = httpParams.set('password', password);

    return this.http.post(this.PATH_TO_CONTROLLER + 'logIn',
      undefined, {params: httpParams});
  }

  public buyPhone(purchase: {}) {
    return this.http.put(this.PATH_TO_CONTROLLER + 'buyPhone', purchase);
  }

  public getUser(id: number) {
    return this.http.get(this.PATH_TO_CONTROLLER + 'getUser?id=' + id);
  }

  public getPurchases(userId: number) {
    return this.http.get(this.PATH_TO_CONTROLLER + 'purchases?userId=' + userId);
  }

  public getCurrentUser() {
    return JSON.parse(localStorage.getItem(GlobalConstants.LOGGED_USER_KEY));
  }
}
