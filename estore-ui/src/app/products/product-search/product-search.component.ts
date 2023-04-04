import { Component, OnInit, Input, Output, OnChanges, SimpleChanges, EventEmitter } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Dice } from 'src/app/dice';
import { ProductService } from 'src/app/product.service';

// TODO add dynamic search bar updates
// TODO clear search stuff when navigating out of the products page

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: [ './product-search.component.css' ]
})
export class ProductSearchComponent implements OnInit {
  products$: Observable<Dice[]> | undefined;
  
  @Input() searchTerms : string;
  @Output("searchDice") searchDice: EventEmitter<any> = new EventEmitter();

  constructor(private productService: ProductService) {
    this.searchTerms = "";
  }

  ngOnInit(): void {
    
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
  }

  // Push a search term into the observable stream.
  search(term: string): void {
    //this.searchTerms.next(term);
  }
}
