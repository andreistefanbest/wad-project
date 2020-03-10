import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductDetaislComponent} from './product-detaisl/product-detaisl.component';

const routes: Routes = [
  {
    path: 'products',
    component: ProductListComponent
  },
  {
    path: 'product/:id', component: ProductDetaislComponent
  },
  {
    path: '', redirectTo: '/products', pathMatch: 'full'
  }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [RouterModule]
})
export class RoutingModule { }
