import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './user.service';
import {ProductsComponent} from './products/products.component';
import {ProductsService} from './products/products.service';
import {LoginComponent} from './login/login.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {AppRoutingModule} from './app-routing.module';
import {ErrorStateMatcher, ShowOnDirtyErrorStateMatcher} from '@angular/material/core';
import {PhonesModule} from './phones/phones.module';
import {CommonsModule} from './commons.module';
import {HistoryComponent} from './products/history/history.component';
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HistoryComponent,
    LoginComponent,
    NoPageFoundComponent
  ],
  imports: [
    CommonsModule,
    HttpClientModule,
    PhonesModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    LoadingBarHttpClientModule
  ],
  providers: [
    UserService,
    ProductsService,
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
