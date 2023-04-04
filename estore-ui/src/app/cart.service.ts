import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Cart } from './cart';

@Injectable({
  providedIn: 'root'
})

export class CartService {

  private cartsUrl = '/api/cart';

  constructor(
    private http: HttpClient,
    //private messageService: MessageService
    ) { }

    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    /**
     * Find and returns the cart with the given cart_number
     * @param id the cart's cart_number
     * @returns 
     */
    getCart(id: number): Observable<Cart> {
      var cart = this.http.get<Cart>(this.cartsUrl  + "/" + id)
      .pipe(
        catchError(this.handleError<Cart>('getCart'))
      );
  
      return cart
    }

    /**
     * Updates the cart with the information in the given cart
     */
    updateCart(cart: Cart) {
      return this.http.put(this.cartsUrl, cart, this.httpOptions).pipe(
        catchError(this.handleError<any>('updateCart'))
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
    
        // Let the app keep running by returning an empty result.
        return of(result as T);
      };
    }
}
