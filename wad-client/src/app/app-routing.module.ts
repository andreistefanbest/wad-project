import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {ProductsComponent} from './products/products.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {HistoryComponent} from './products/history/history.component';
import {UserGuard} from './user.guard';
import {PurchaseHistoryComponent} from './purchase-history/purchase-history.component';
import {ManagementComponent} from './management/management.component';

const appRoutes: Routes = [
  {path: '', redirectTo: 'phones', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, data: {animation: 'a'}},
  {path: 'products', component: ProductsComponent, data: {animation: 'b'}},
  {path: 'history', canActivate: [UserGuard], component: HistoryComponent, data: {animation: 'x'}},
  {path: 'purchase-history', canActivate: [UserGuard], component: PurchaseHistoryComponent, data: {animation: 'y'}},
  {path: 'management', component: ManagementComponent, data: {animation: 'z'}},
  {path: 'statistics', component: PurchaseHistoryComponent, data: {animation: 'i'}},
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
