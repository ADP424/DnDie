import { Component, Inject, Injectable, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { Dice } from '../../dice';
import { ProductService } from '../../product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  
  products: Dice[] = [];
  searchTerms: string = "";

  constructor (
    private productService: ProductService,
    public router: Router) { }
  
    /**
     * Call getProducts on init
     */
  ngOnInit(): void {
    this.getProducts();
  }

  /**
   * Call getDices on select
   */
  onSelect(): void {
    this.getDices();
  }
  
  /**
   * Sort the list of products alphabetically
   * @param items the list of products to be sorted
   * @returns the sorted list of products
   */
  sortBy(items:Dice[]) {
    return items.sort((a, b) => a.name > b.name ? 1 : a.name === b.name ? 0 : -1);
  }

  /**
   * Retrieve the dices using productService
   */
  getDices(): void {
    this.productService.getDices()
    .subscribe(dice => this.products = dice);
  }

  /**
   * Retrieve products using productService
   */
  getProducts(): void {
    this.productService.getProducts()
    .subscribe(dice => this.products = dice);
  }

  /**
   * Call the search function in productService and then getDice
   */
  searchDice(): void{
    let term = (<HTMLInputElement>document.getElementById("search-box")).value;
    console.log((<HTMLInputElement>document.getElementById("search-box")).value);
    this.productService.searchDice(term);
    this.getDices();
  }
}
