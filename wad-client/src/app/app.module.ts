import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './login/login.service';
import {ProductsComponent} from './products/products.component';
import {ProductsService} from './products/products.service';
import {LoginComponent} from './login/login.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {AppRoutingModule} from './app-routing.module';
import {ErrorStateMatcher, ShowOnDirtyErrorStateMatcher} from '@angular/material/core';
import {PhonesModule} from './phones/phones.module';
import {CommonsModule} from './commons.module';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    LoginComponent,
    NoPageFoundComponent
  ],
  imports: [
    CommonsModule,
    HttpClientModule,
    PhonesModule,
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  providers: [
    LoginService,
    ProductsService,
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
