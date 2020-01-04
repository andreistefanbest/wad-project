import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class UserService {
  private PATH_TO_CONTROLLER = 'http://localhost:1337/localhost:8080/user/';

  constructor(private http: HttpClient) {
  }

  public getCurrentUser() {
    // return JSON.parse(localStorage.getItem(GlobalConstants.LOGGED_USER_KEY));
  }

  public getPersons() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'persons');
  }
}
