import { Component, Inject, Injectable, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { User } from '../../user'
import { UserService } from '../../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  
  users: User[] = [];

  constructor (
    private userService: UserService,
    public router: Router) { }
  
  ngOnInit(): void {
    this.getUsers();
  }

  onSelect(user: User): void {
    this.getUsers();
  }
  sortBy(items:User[]) {
    return items.sort((a, b) => a.cart_number > b.cart_number ? 1 : a.cart_number === b.cart_number ? 0 : -1);
  }

  getUsers(): void {
    this.userService.getUsers()
    .subscribe(user => this.users = user);
  }


  add(user: User): void {
    if (!user) { return; }
    this.userService.addUser(user)
      .subscribe(user => {
        this.users.push(user);
      });
  }   
}
