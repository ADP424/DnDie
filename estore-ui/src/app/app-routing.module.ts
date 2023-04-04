import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';

const routes: Routes = [
  { path: '', loadChildren: () => import('./features/features.module').then(x => x.FeaturesModule)},
  { path: '', loadChildren: () => import('./products/products.module').then(x => x.ProductsModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }