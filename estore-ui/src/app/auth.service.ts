import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { User, UserHandler } from './user';
import { catchError, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  authenticated = false;
  public username = '';
  public password = '';

  private usersUrl = '/api/users';
  private loginUrl = '/api/login';

  users: User[] = [];

  constructor(private http: HttpClient) { }

  httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  /**
   * Returns the user with the target username and password
   * Will return the first user in the list if none is found
   * Use userExists() method first to make sure the target user exists before calling this method
   * @param username target user's username
   * @param password target user's password
   * @returns the target user
   */
  loginUser(username: string, password: string) {
    let queryParams = new HttpParams();
    const body = {
      "username":username,
      "password":password
    }
    queryParams.append("username",username);
    queryParams.append("password",password);
    const headers = new HttpHeaders()
    .set('cache-control', 'no-cache')
    .set('content-type', 'application/json')
    return this.http.post(this.loginUrl,{ headers: headers, body:body}).pipe(
      catchError(this.handleError<UserHandler>('loginUser'))
    );;

  }

  /**
   * Returns true if the user is logged in
   * @returns whether the user is logged in or not
   */
  isUserLoggedIn(): boolean {
    let user = sessionStorage.getItem('username')
    // console.log(!(user === null))
    return !(user === null)
  }

  /**
   * Returns true if the user is logged out and false otherwise
   * @returns whether the user is not logged in or is
   */
  isUserLoggedOut() {
    let user = sessionStorage.getItem('username')
    // console.log(!(user === null))
    return user === null
  }

  /**
   * Updates sessionStorage with all the current user's information
   * Should only be used once the user's login is validated
   * @param user the logged in user
   */
  logInSuccess(user: User) {
    sessionStorage.setItem('cart_number', user.cart_number.toString())
    sessionStorage.setItem('username', user.username)
    sessionStorage.setItem('first_name', user.first_name)
    sessionStorage.setItem('last_name', user.last_name)
    sessionStorage.setItem('is_admin', user.is_admin.toString())
  }

  /**
   * Removes all the items from sessionStorage
   * Sets the current username and password to blank
   */
  logOut() {
    sessionStorage.removeItem('cart_number')
    sessionStorage.removeItem('username')
    sessionStorage.removeItem('password')
    sessionStorage.removeItem('first_name')
    sessionStorage.removeItem('last_name')
    sessionStorage.removeItem('is_admin')
    this.username = '';
    this.password = '';
  }

  /**
   * Checks if the user is a website admin
   * @returns whether the user is admin
   */
  public userIsAdmin(): boolean {
    return (this.isUserLoggedIn() && sessionStorage.getItem('is_admin') == 'true')
  }

  /**
   * Handles errors for this component
   * @param _operation the operation being performed when the error occurred
   * @param result the error result
   * @returns the result
   */
  private handleError<T>(_operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
