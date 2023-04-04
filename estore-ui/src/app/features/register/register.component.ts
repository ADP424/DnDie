import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  username:string = "";
  password:string = "";
  first_name:string = "";
  last_name:string = "";

  userUrl: string = '/api/users';
  cartUrl: string = '/api/cart';

  constructor(public router: Router, private http: HttpClient, private loginservice: AuthService) { }

  ngOnInit(): void {
  }

  /**
   * Posts a user to the api service
   */
  RegisterUser() {
    const headers = new HttpHeaders()
        .set('cache-control', 'no-cache')
        .set('content-type', 'application/json')

      const body = {
        username: this.username,
        password: this.password,
        first_name: this.first_name,
        last_name: this.last_name
      }

      this.http.post(this.userUrl, body, { headers: headers }).subscribe(res => res);
      this.createNewCart();

      this.username = ""
      this.password = ""
      this.first_name = ""
      this.last_name = ""

      this.router.navigate(['/login']);
  }

  /**
   * Creates a cart for the current user
   * Called from RegisterUser
   */
  createNewCart() {
    const headers = new HttpHeaders()
        .set('cache-control', 'no-cache')
        .set('content-type', 'application/json')
  
    const body = {
      name: this.username + "'s Cart",
      array: []
    }
  
    this.http.post(this.cartUrl, body, { headers: headers }).subscribe(res => res);
  }
}
