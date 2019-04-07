import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MatButtonModule, MatDividerModule, MatInputModule, MatListModule, MatToolbarModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './login/login.service';
import {ProductsComponent} from './products/products.component';
import {ProductsService} from './products/products.service';
import {RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {MatCardModule} from '@angular/material/card';
import {AppRoutingModule} from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    LoginComponent,
    NoPageFoundComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatDividerModule,
    FormsModule,
    HttpClientModule,
    MatListModule,
    MatButtonModule,
    MatToolbarModule,
    RouterModule,
    MatCardModule,
    AppRoutingModule
  ],
  providers: [
    LoginService,
    ProductsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
