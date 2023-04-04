import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(
    router:Router,
    public loginService: AuthService
    ) {

    let curUrl = window.location.href.split("/")
    if(curUrl.length < 5) {
      router.navigate(['/home']);
    }
  }

  title = 'DNDie';
  public showOverlay = true;
}
