import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';
import {FormControl, Validators} from '@angular/forms';
import {take} from 'rxjs/operators';
import {GlobalConstants} from '../utils/GlobalConstants';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessageMail = 'Please enter a valid email address';
  errorMessagePassword = 'Please enter a password';
  errorMessageName = 'Please enter your name';

  emailFormControlLogIn: FormControl;
  emailFormControlSignIn: FormControl;
  passLogInControl: FormControl;
  passSignInControl: FormControl;
  nameControl: FormControl;

  loggedIn: boolean;

  logInPressed: boolean;

  constructor(private loginService: UserService,
              private snackBar: MatSnackBar,
              private router: Router) {
  }

  public ngOnInit(): void {
    this.emailFormControlLogIn = new FormControl('', [
      Validators.required,
      Validators.email,
    ]);

    this.emailFormControlSignIn = new FormControl('', [
      Validators.required,
      Validators.email,
    ]);
    this.passLogInControl = new FormControl('', [
      Validators.required
    ]);
    this.passSignInControl = new FormControl('', [
      Validators.required
    ]);
    this.nameControl = new FormControl('', [
      Validators.required
    ]);

    this.loggedIn = localStorage.getItem(GlobalConstants.LOGGED_USER_KEY) != null;
  }

  logIn(event: MouseEvent) {
    this.logInPressed = true;
    let err = false;
    if (this.emailFormControlLogIn.hasError('email')) {
      this.emailFormControlLogIn.markAsDirty();
      err = true;
    }
    if (this.emailFormControlLogIn.hasError('required')) {
      this.emailFormControlLogIn.markAsDirty();
      err = true;
    }
    if (this.passLogInControl.hasError('required')) {
      this.passLogInControl.markAsDirty();
      err = true;
    }

    if (err) {
      return;
    }

    this.loginService.logIn(this.emailFormControlLogIn.value, this.passLogInControl.value).pipe(take(1))
      .subscribe((result) => {
        if (result != null) {
          localStorage.setItem(GlobalConstants.LOGGED_USER_KEY, JSON.stringify(result));
          this.loginService.userChanged.emit();
          // @ts-ignore
          this.snackBar.open('Welcome, ' + result.fullName + '!', '', {
            duration: 2500,
          });
          this.router.navigate(['/home']);
        } else {
          // @ts-ignore
          this.snackBar.open('Wrong username of password!', '', {
            duration: 2500,
          });
        }
      });
  }

  signIn(event: MouseEvent) {
    let err = false;
    if (this.emailFormControlSignIn.hasError('email')) {
      this.emailFormControlSignIn.markAsDirty();
      err = true;
    }
    if (this.emailFormControlSignIn.hasError('required')) {
      this.emailFormControlSignIn.markAsDirty();
      err = true;
    }
    if (this.passSignInControl.hasError('required')) {
      this.passSignInControl.markAsDirty();
      err = true;
    }
    if (this.nameControl.hasError('required')) {
      this.nameControl.markAsDirty();
      err = true;
    }

    if (err) {
      return;
    }

    this.loginService.signIn(this.nameControl.value, this.emailFormControlSignIn.value, this.passSignInControl.value).pipe(take(1))
      .subscribe((result) => {
        localStorage.setItem(GlobalConstants.LOGGED_USER_KEY, JSON.stringify(result));
        this.loginService.userChanged.emit();
        // @ts-ignore
        this.snackBar.open('Welcome, ' + result.fullName + '!', '', {
          duration: 2500,
        });
        this.router.navigate(['/home']);
      });
  }

  logOut() {
    this.loggedIn = false;
    // @ts-ignore
    this.snackBar.open('See ya!', '', {
      duration: 2500,
    });
    localStorage.removeItem(GlobalConstants.LOGGED_USER_KEY);
    this.loginService.userChanged.emit();
  }
}
