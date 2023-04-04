import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User, UserHandler } from '../../user';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username:string = "";
  password:string = "";

  constructor(
    public router: Router,
    private loginservice: AuthService
    ) { }

  ngOnInit(): void {}

  /**
   * Call the loginUser function in loginService to log the user in
   */
  LoginUser(): void{
    this.loginservice.loginUser(this.username, this.password).subscribe(res => this.handleAuth(res as UserHandler));
  }

  /**
   * Performs all necessary tasks to check the user's authorization upon login
   * @param res http response holding the user data
   */
  handleAuth(res: UserHandler) {    
    if (res.success[0]) {
      console.log("Success")
      var user: User = {
        cart_number:res.cart_number[0],
        first_name:res.first_name[0],
        last_name:res.last_name[0],
        is_admin:res.is_admin[0],
        username:res.username[0],
      }
      this.HandleLogin(true, user)
    } else {
      this.HandleLogin(false, {
        cart_number:-1,
        first_name:"",
        last_name:"",
        is_admin:false,
        username:"",
      }
      )
      console.log("Failure")
    }
  }

  /**
   * If success is true, log the user in and navigate them to the loading page
   * @param success whether there was successfull authorization or not
   * @param user the user to login
   */
  HandleLogin(success: boolean, user: User): void {
    if (success) {
      this.loginservice.logInSuccess(user)
      this.router.navigate(['/loading'])
    } else {
      alert("Incorrect Password or Username");
    }
  }

  /**
   * Navigate to the register page
   */
  NewUser(): void{
    this.router.navigate(['/register'])
  }

}