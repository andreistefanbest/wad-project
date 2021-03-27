import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class PhonesService {
  private PATH_TO_CONTROLLER = 'http://localhost:8080/phones/';

  constructor(private http: HttpClient) {

  }

  public getPhones() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'getPhones');
  }

  public getBrands() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'brands');
  }

  public add(phone) {
    return this.http.post(this.PATH_TO_CONTROLLER + 'phone', phone);
  }

  public update(phone) {
    return this.http.put(this.PATH_TO_CONTROLLER + 'phone', phone);
  }

  public delete(id: number) {
    return this.http.delete(this.PATH_TO_CONTROLLER + 'phone?id=' + id);
  }
}
