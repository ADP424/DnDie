import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductsRoutingModule } from './products-routing.module';
import { ProductSearchComponent } from './product-search/product-search.component';
import { ProductComponent } from './product/product.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../product.service';


@NgModule({
  declarations: [
    ProductComponent,
    ProductSearchComponent,
    ProductDetailComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
    RouterModule,
    FormsModule
  ],
  exports: [
    ProductSearchComponent
  ]
})
export class ProductsModule { }
