import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { FeaturesModule } from './features/features.module';
import { ProductsModule } from './products/products.module';
import { UsersModule } from './users/users.module';

import { HttpClientModule } from '@angular/common/http';
import { UserComponent } from './users/user/user.component';
import { AdminPageComponent } from './features/admin-page/admin-page.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AdminPageComponent
  ],
  imports: [
    BrowserModule,
    FeaturesModule,
    ProductsModule,
    UsersModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
