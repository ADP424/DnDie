import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/product.service';
import { Dice } from '../../dice';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  products: Dice[] = [];
  
  constructor(public router: Router,
    private productService: ProductService
    ) { }
  
  /**
   * Call get dices on init
   */
  ngOnInit(): void {
    this.getDices();
  }
  
  /**
   * Sorts the array of products alphabetically
   * @param items the products to be sorted
   * @returns the array of sorted products
   */
  sortBy(items:Dice[]) {
    return items.sort((a, b) => a.name > b.name ? 1 : a.name === b.name ? 0 : -1);
  }

  /**
   * Fetches the products
   */
  getDices(): void {
    this.productService.getProducts()
      .subscribe((products: Dice[]) => 
      this.products = products.slice(1, 4)
      );
  }
}
