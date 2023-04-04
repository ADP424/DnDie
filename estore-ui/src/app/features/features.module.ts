import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { FeaturesRoutingModule } from './features-routing.module';
import { HomeComponent } from './home/home.component';
import { LoadingComponent } from './loading/loading.component';
import { LoginComponent } from './login/login.component';
import { ProductSearchComponent } from '../products/product-search/product-search.component';
import { RouterModule } from '@angular/router';
import { ProductsModule } from '../products/products.module';
import { ProductService } from '../product.service';
import { LogoutComponent } from './logout/logout.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ProcessComponent } from './process/process.component';


@NgModule({
  declarations: [
    RegisterComponent,
    HomeComponent,
    LoadingComponent,
    LoginComponent,
    LogoutComponent,
    CheckoutComponent,
    ProcessComponent
  ],
  imports: [
    CommonModule,
    ProductsModule,
    FeaturesRoutingModule,
    FormsModule,
    RouterModule
  ]
})
export class FeaturesModule { }
