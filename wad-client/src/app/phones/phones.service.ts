import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class PhonesService {
  private PATH_TO_CONTROLLER = 'http://localhost:1337/localhost:8080/phones/';

  constructor(private http: HttpClient) {

  }

  public getPhones() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'getPhones');
  }
}
