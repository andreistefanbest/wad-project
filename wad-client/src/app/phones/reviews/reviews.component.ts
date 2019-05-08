import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {UserService} from '../../user.service';
import {ReviewsService} from './reviews.service';
import {take} from 'rxjs/operators';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {

  user: any;
  reviewsDS: any;
  content: string;

  constructor(@Inject(MAT_DIALOG_DATA) private phone,
              private userService: UserService,
              private reviewsService: ReviewsService) { }

  ngOnInit() {
    this.user = this.userService.getCurrentUser();
    this.reviewsService.getReviews(this.phone.phone.id).pipe(take(1)).subscribe((reviews) => {
      this.reviewsDS = reviews;
    });
  }

  addReview() {
    const review = {
      userName: this.user ? this.user.fullName : null,
      userId: this.user ? this.user.userId : null,
      date: new Date(),
      content: this.content,
      phoneId: this.phone.phone.id
    }
    this.reviewsService.addReview(review).pipe(take(1)).subscribe(() => {
      this.reviewsDS.push(review);
      this.content = null;
    });
  }
}
