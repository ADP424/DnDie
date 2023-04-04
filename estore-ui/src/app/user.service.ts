import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { MessageService } from './message.service';

import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = '/api/users';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
    ) { }

    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

  getUsers(): Observable<User[]> {
    var users = this.http.get<User[]>(this.usersUrl)
    .pipe(
      catchError(this.handleError<User[]>('getUsers', []))
    );

    return users
  }

  getUser(username: string): Observable<User> {
    var user = this.http.get<User>(this.usersUrl + "/" + username)
    .pipe(
      catchError(this.handleError<User>('getUser'))
    );
    return user
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** POST: add a new user to the server */
  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.usersUrl, user, this.httpOptions).pipe(
      tap((newUser: User) => this.log(`added hero w/ username=${newUser.username}`)),
      catchError(this.handleError<User>('addUser'))
    );
  }

  /** Log a UserService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`UserService: ${message}`);
  }
}
