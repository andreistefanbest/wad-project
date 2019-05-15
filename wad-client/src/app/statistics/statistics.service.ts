import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class StatisticsService {
  private PATH_TO_CONTROLLER = 'http://localhost:1337/localhost:8080/statistics/';

  constructor(private http: HttpClient) {

  }

  public getMonthlySales() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'monthly-sales');
  }
}
