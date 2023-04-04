import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { LoadingComponent } from './loading/loading.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProductDetailComponent } from '../products/product-detail/product-detail.component';
import { LogoutComponent } from './logout/logout.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { AuthGaurdService } from '../auth-gaurd.service';
import { ProcessComponent } from './process/process.component';
import { AdminPageComponent } from './admin-page/admin-page.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login',   component: LoginComponent  },
  { path: 'logout',   component: LogoutComponent  },
  { path: 'home',   component: HomeComponent  },
  { path: 'loading',   component: LoadingComponent  },
  { path: 'cart', component:CheckoutComponent,  canActivate:[AuthGaurdService]},
  { path: 'products', loadChildren: () => import('../products/products.module').then(x => x.ProductsModule)},
  { path: 'detail/:id', component: ProductDetailComponent },
  { path: 'register', component: RegisterComponent  },
  { path: 'process',   component: ProcessComponent,  canActivate:[AuthGaurdService]  },
  { 
    path: 'admin',
    children: [
      {
        path:"add",
        component: AdminPageComponent
      },
      {
        path:"delete",
        component: AdminPageComponent
      },
      {
        path:"update",
        component: AdminPageComponent
      }

  ],
    component: AdminPageComponent  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
