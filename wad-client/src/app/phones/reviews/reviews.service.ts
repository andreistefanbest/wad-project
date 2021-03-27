import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReviewsService {
  private PATH_TO_CONTROLLER = 'http://localhost:8080/review/';

  constructor(private http: HttpClient) { }

  public getReviews(phoneId: number) {
    return this.http.get(this.PATH_TO_CONTROLLER + 'review?phoneId=' + phoneId);
  }

  public addReview(review) {
    return this.http.post(this.PATH_TO_CONTROLLER + 'review', review);
  }
}
