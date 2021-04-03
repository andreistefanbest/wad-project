import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class PhonesService {
  private PATH_TO_CONTROLLER = 'http://localhost:8080/phones/';

  constructor(private http: HttpClient) {

  }

  getPhones() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'phones');
  }

  getBrands() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'brands');
  }

  add(phone) {
    return this.http.post(this.PATH_TO_CONTROLLER + 'phone', phone);
  }

  update(phone) {
    return this.http.put(this.PATH_TO_CONTROLLER + 'phone', phone);
  }

  delete(id: number) {
    return this.http.delete(this.PATH_TO_CONTROLLER + 'phone?id=' + id);
  }

  addBrand(newBrandName: string) {
    return this.http.post(this.PATH_TO_CONTROLLER + 'brand', newBrandName);
  }
}
