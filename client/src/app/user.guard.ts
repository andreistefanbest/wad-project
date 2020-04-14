import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {GlobalConstants} from './utils/GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private router: Router) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (localStorage.getItem(GlobalConstants.LOGGED_USER_KEY) != null) {
      return true;
    }

    this.router.navigate(['/login']);
  }

}
