import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable()
export class StatisticsService {
  private PATH_TO_CONTROLLER = environment.backend + '/statistics/';

  constructor(private http: HttpClient) {

  }

  public getMonthlySales() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'monthly-sales');
  }
}
