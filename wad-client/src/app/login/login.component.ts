import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {LoginService} from './login.service';
import {FormControl, FormGroupDirective, NgForm, ValidationErrors, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {take} from 'rxjs/operators';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

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

  matcher: MyErrorStateMatcher;

  constructor(private loginService: LoginService) {
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
    this.matcher = new MyErrorStateMatcher();
  }

  logIn(event: MouseEvent) {
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
        console.log('Login ' + (result === 1 ? 'successful!' : 'failed!'));
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
      .subscribe((result: {userId: string}) => {
        console.log('Success! New user Id is: ' + result.userId);
      });
  }

}
