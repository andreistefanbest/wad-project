import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {slideInAnimation} from './animations';
import {UserService} from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [slideInAnimation]
})
export class AppComponent implements OnInit {

  userType: string;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.fetchUserType();
    this.userService.userChanged.subscribe(() => {
      this.fetchUserType();
    });
  }

  private fetchUserType() {
    this.userType = this.userService.getCurrentUser() ? this.userService.getCurrentUser().userType : null;
  }

  getAnimationData(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData.animation;
  }

}
