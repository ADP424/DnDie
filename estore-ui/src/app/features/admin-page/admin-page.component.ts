import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {
  name:string = "";
  dice_color:string = "";
  font_type:string = "";
  font_color:string = "";
  price:string = "";
  quantity:string = "";
  coupon: string = "";

  id:string = "";

  id_to_update:string = "";
  name_to_update:string = "";
  dice_color_to_update:string = "";
  font_type_to_update:string = "";
  font_color_to_update:string = "";
  price_to_update:string = "";
  quantity_to_update:string = "";
  coupon_to_update:string = "";

  url: string = '/api/products/';
  page:string = "default"

  constructor(private http: HttpClient, public router: Router, public loginService: AuthService) { }

  /**
   * Check if the user is logged in and authenticated as admin
   * If they are not both of those things, navigate them away
   */
  ngOnInit(): void {
    if (this.loginService.isUserLoggedIn()) {
      if (!this.loginService.userIsAdmin()) {
        console.error("User is not authenticated!")
        this.router.navigate(['/home'])
        return
      }
    } else {
      console.error("User is not logged in!")
      this.router.navigate(['/login'])
      return
    }

    if (this.router.url == "/admin") {
      this.router.navigate(['/admin/add'])
    }
  }

  /**
   * Replaces  the current page
   * @param newPage the page to set the current page to
   */
  public setPage(newPage: string){
    this.page = newPage
  }

  /**
   * Posts a product with the given fields, even if the fields are empty
   * @param name name of the product to be added
   * @param dice_color color of the dice to be added
   * @param font_type type of font of the dice to be added
   * @param font_color color of the font of the dice to be added
   * @param price the price of the product
   */
  public addProduct() {
    const headers = new HttpHeaders()
    .set('cache-control', 'no-cache')
    .set('content-type', 'application/json')

    const body = {
      name: this.name,
      dice_color: this.dice_color,
      font_type: this.font_type,
      font_color: this.font_color,
      price: this.price,
      coupon: this.coupon,
      quantity: this.quantity
    };

    this.name = "";
    this.dice_color = "";
    this.font_type = "";
    this.font_color = "";
    this.price = "";
    this.coupon = "";
    this.quantity = "";

    this.http.post(this.url, body, { headers: headers }).subscribe(res => res);
  }

  /**
   * Delete the product with the current id
   */
  public deleteProduct() {
    this.http.delete(this.url + "/" + this.id).subscribe(res => res)
    this.id = "";
  }

  /**
   * Updates the product with all the current attributes
   * If any are left blank, they will set them to blank
   */
  public updateProduct() {
    const headers = new HttpHeaders()
    .set('cache-control', 'no-cache')
    .set('content-type', 'application/json')

    const body = {
      id: this.id_to_update,
      name: this.name_to_update,
      dice_color: this.dice_color_to_update,
      font_type: this.font_type_to_update,
      font_color: this.font_color_to_update,
      price: this.price_to_update,
      quantity: this.quantity_to_update,
      coupon: this.coupon_to_update
    };

    this.id_to_update = "";
    this.name_to_update = "";
    this.dice_color_to_update = "";
    this.font_type_to_update = "";
    this.font_color_to_update = "";
    this.price_to_update = "";
    this.coupon_to_update = "";
    this.quantity_to_update = "";
    this.coupon_to_update = "";

    this.http.put(this.url + "/" + this.id_to_update, body).subscribe(res => res)
  }
}
