import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {ProductsComponent} from './products/products.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';

const appRoutes: Routes = [
  {path: '', component: LoginComponent, data: {animation: 'test1'}},
  {path: 'products', component: ProductsComponent, data: {animation: 'test2'}},
  {path: '**', component: NoPageFoundComponent}
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
