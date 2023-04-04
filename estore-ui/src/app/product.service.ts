import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

import { Dice } from './dice';
import { DICES } from './dice-samples';
import { MessageService } from './message.service';


@Injectable({
  providedIn: 'root'
})

export class ProductService {

  private productsUrl = '/api/products';
  private searchTerms = ""; 

  constructor(
    private http: HttpClient,
    public router: Router
    ) { }

    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    /**
     * Fetches and returns a list of dice with the current search terms
     * @returns all the dice/products with the current search terms
     */
    getDices(): Observable<Dice[]> {
      var dices = this.http.get<Dice[]>(this.productsUrl + "/search?text=" + this.searchTerms)
      .pipe(
        catchError(this.handleError<Dice[]>('getDices', []))
      );

      return dices
    }

    /**
     * Returns every product in the shop
     * @returns every product in the shop
     */
    getProducts(): Observable<Dice[]> {
      var dices = this.http.get<Dice[]>(this.productsUrl + "/search?text=" + "")
      .pipe(
        catchError(this.handleError<Dice[]>('getDices', []))
      );

      return dices
    }

    /**
     * Set the search terms to term
     * @param term the term to filter by
     */
    searchDice(term: string): void {   
      this.searchTerms = term;
      console.log(term);
    }

    /**
     * Returns a dice with the given id
     * @param id the given id
     * @returns 
     */
    getDice(id: number): Observable<Dice> {
      var dice = this.http.get<Dice>(this.productsUrl + "/" + id)
      .pipe(
        catchError(this.handleError<Dice>('getDice'))
      );

      return dice
    }

    /**
     * Updates the product with the information in the given cart
     */
     updateDice(dice: Dice) {
      return this.http.put(this.productsUrl, dice, this.httpOptions).pipe(
        catchError(this.handleError<any>('updateDice'))
      );
    }

    /**
     * Handles errors for this component
     * @param _operation the operation being performed when the error occurred
     * @param result the error result
     * @returns the result
     */
    private handleError<T>(operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
    
        // TODO: send the error to remote logging infrastructure
        console.error(error); // log to console instead
        if (this.router.url.indexOf('/detail') != -1) {
          this.router.navigate(['/home'])
        }
        
        // Let the app keep running by returning an empty result.
        return of(result as T);
      };
    }
}
