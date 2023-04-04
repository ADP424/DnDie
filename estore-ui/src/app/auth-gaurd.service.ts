import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdService {

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  /**
   * Check if the user is logged in
   * If they are, return true
   * If they aren't, navigate to login and return false
   * @returns whether the user is logged in or not
   */
  canActivate(): boolean {
    if (this.authService.isUserLoggedIn())
      return true;

    this.router.navigate(['login']);
    return false;

  }
}
