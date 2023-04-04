import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/cart';
import { CartService } from 'src/app/cart.service';
import { Dice } from 'src/app/dice';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  cart: Cart | undefined;
  productsInCart: Dice[] = [];
  price: number = 0;
  newPrice: String = '';

  constructor(
    private cartService: CartService,
    public router: Router,
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    this.getCart()
  }

  /**
   * Calls the getCart method in cartService with cart_number from the sessionStorage and subscribes
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
   * Sets the cart and productsInCart to the values in the cart parameter
   * Counts the total price by calling countPrice
   * @param cart the cart to setup
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
   * Sorts the products alphabetically by name
   * @param items the products to be sorted
   * @returns the array of sorted products
   */
  sortBy(items:Dice[]) {
    return items.sort((a, b) => a.name > b.name ? 1 : a.name === b.name ? 0 : -1);
  }

  /**
   * Delete a specified product from the cart
   * @param product the product to delete
   */
  delete(product: Dice): void{
    this.productsInCart.splice(this.productsInCart.indexOf(product), 1);
    product.quantity = product.quantity + 1
    this.save()
    this.saveDice(product)
    this.countPrice(this.productsInCart)
  }

  /**
   * Navigate to the checkout page with the router
   */
  checkout(): void {
    this.router.navigate(['/process'])
  }

  /**
   * Save the user's cart and its contents
   */
  save(): void {
    if (this.cart) {
      this.cart.array = this.productsInCart
      this.cartService.updateCart(this.cart)
        .subscribe();
    }
  }

 /**
   * Save the current dice to the service (and then to the JSON)
   */
  saveDice(dice: Dice): void {
    this.productService.updateDice(dice)
      .subscribe()
  }

}
