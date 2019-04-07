import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {LoginService} from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnDestroy {

  private existingUser: string;
  private existingUserPass: string;

  private newUserName: string;
  private newUserMail: string;
  private newUserPass: string;

  private loginSuccessful: boolean;

  private subscription: Subscription;

  constructor(private loginService: LoginService) {
    this.subscription = new Subscription();
  }

  public ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private logIn(event: MouseEvent) {
    this.subscription.add(this.loginService.logIn(this.existingUser, this.existingUserPass)
      .subscribe((result) => {
        console.log('Login ' + (result === 1 ? 'successful!' : 'failed!'));
        this.loginSuccessful = result === 1;
      }));
  }

  private signIn(event: MouseEvent) {
    this.subscription.add(this.loginService.signIn(this.newUserName, this.newUserMail, this.newUserPass)
      .subscribe((result: {userId: string}) => {
        console.log('Success! New user Id is: ' + result.userId);
      }));
  }
}
