import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {ProgrammingComponent} from './programming/programming.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';

const appRoutes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, data: {animation: 'a'}},
  {path: 'programming', component: ProgrammingComponent, data: {animation: 'b'}},
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
