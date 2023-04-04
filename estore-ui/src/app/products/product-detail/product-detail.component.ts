import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Dice } from '../../dice';
import { ProductService } from '../../product.service';
import { CartService } from 'src/app/cart.service';
import { Cart } from 'src/app/cart';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: [ './product-detail.component.css' ]
})
export class ProductDetailComponent implements OnInit {
  
  die: Dice | undefined;
  cart: Cart | undefined;
  productsInCart: Dice[] = [];

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService,
    private location: Location,
    public loginService: AuthService
  ) {}

  /**
   * getDie and getCart on init
   */
  ngOnInit(): void {
    this.getDie();
    this.getCart();
  }

  /**
   * Get the die with the id at the current routing
   */
  getDie(): void {
    const id = Number(this.route.snapshot.paramMap.get('id')!);
    this.productService.getDice(id)
    .subscribe(die => this.die = die);
  }

  /**
   * Get the cart with the current user's cart_number
   */
  getCart(): void{
    const id = sessionStorage.getItem('cart_number');
    if(id != null) {
      this.cartService.getCart(parseInt(id, 10)).subscribe(cart => this.setup(cart));
    }
  }

  /**
   * Setup the current user's cart with a given cart
   * @param cart the cart to set the current cart to
   */
  setup(cart: Cart): void{
    this.cart = cart
    this.productsInCart = cart.array
  }

  /**
   * Go back in the URL
   */
  goBack(): void {
    this.location.back();
  }

  /**
   * Add a die to the cart
   * @param die the die to add
   */
  addToCart(die: Dice): void{
    if(this.loginService.isUserLoggedIn()){
      if (die.quantity >= 1) {
        doCartMessage();
        die.quantity = die.quantity - 1;
        this.productsInCart.push(die)
        this.saveCart()
        this.saveDice()
      } else {
        alert("Sorry, none in stock")
      }
    } else {
      alert("You aren't logged in!")
    }
  }

  /**
   * Save the current cart to the service (and then to the JSON)
   */
  saveCart(): void {
    if (this.cart) {
      this.cartService.updateCart(this.cart)
        .subscribe();
    }
  }

   /**
   * Save the current dice to the service (and then to the JSON)
   */
    saveDice(): void {
      if (this.die) {
        this.productService.updateDice(this.die)
          .subscribe();
      }
    }

}

function doCartMessage(): void {
  let addCartEle = document.querySelector("#add-to-cart") as HTMLButtonElement;
  if (addCartEle) {
    addCartEle.innerHTML = "Added!";
    addCartEle.disabled = true;
    setTimeout(doCartReset, 1000); 
  }
}
function doCartReset(): void {
  let addCartEle = document.querySelector("#add-to-cart") as HTMLButtonElement;
  if (addCartEle) {
    addCartEle.disabled = false;
    addCartEle.innerHTML = "Add to Cart"
  }
}