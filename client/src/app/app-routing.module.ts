import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {HomeComponent} from "./home/home.component";
import {NewsComponent} from "./news/news.component";

const appRoutes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, data: {animation: 'a'}},
  {path: 'news', component: NewsComponent, data: {animation: 'b'}},
  {path: 'home', component: HomeComponent, data: {animation: 'c'}},
  {path: '**', component: NoPageFoundComponent, data: {animation: 'd'}}
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
