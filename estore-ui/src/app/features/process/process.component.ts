import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/cart';
import { CartService } from 'src/app/cart.service';
import { Dice } from 'src/app/dice';

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.css']
})
export class ProcessComponent implements OnInit {
  cart: Cart | undefined;
  productsInCart: Dice[] = [];
  price: number = 0;
  newPrice: String = '';

  constructor(
    private cartService: CartService,
    public router: Router
  ) { }

  /**
   * Call getCart() on init
   */
  ngOnInit(): void {
    this.getCart()
  }

  /**
   * Get the cart with the cart_number of the current user
   */
  getCart(): void {
    const id = sessionStorage.getItem('cart_number');
    if(id != null) {
      this.cartService.getCart(parseInt(id, 10)).subscribe(cart => this.setup(cart));
    }
    //this.exampleProducts = this.cartService.getCart(1)
    //this.exampleProducts = this.exampleProducts;
  }

  /**
   * Setup the cart by setting the current cart equal to cart
   * @param cart the cart to set the current held cart to
   */
  setup(cart: Cart): void{
    this.cart = cart
    this.productsInCart = cart.array
    this.countPrice(cart.array)
  }


  /**
   * Counts the total price of all the cart products and sets this.price to it
   * @param products the products whose total price is to be counted
   */
  countPrice(products: Dice[]): void{
    this.price = 0;
    for (let product of products) {
      if (product.coupon) {
        this.price += (product.price - (product.price * (product.coupon/100)));
      } else {
        this.price += product.price;
      }
    }
    // TODO add this to process as well
    this.newPrice = (Math.round(this.price * 100) / 100).toFixed(2);
  }

  /**
   * Perform the checkout process once the user checkouts from their cart
   * NOTE: We do not actually accept payment information, as this is just a mock E-store
   */
  checkoutProcess(): void {
    alert('Checkout successful!');
    this.router.navigate(["/home"]);
  }
}
