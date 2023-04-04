import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private authentocationService: AuthService,
    private router: Router
  ) { }

  /**
   * Log the user out and navigate to the login page
   */
  ngOnInit(): void {
    this.authentocationService.logOut();
    this.router.navigate(['login']);
  }

}
