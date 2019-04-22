import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {ProductsComponent} from './products/products.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {HistoryComponent} from './products/history/history.component';

const appRoutes: Routes = [
  {path: '', component: LoginComponent, data: {animation: 'a'}},
  {path: 'products', component: ProductsComponent, data: {animation: 'b'}},
  {path: 'history', component: HistoryComponent, data: {animation: 'x'}},
  {path: '**', component: NoPageFoundComponent, data: {animation: 'c'}}
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
