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
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort'
import {HistoryComponent} from './products/history/history.component';
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';
import {PurchaseHistoryComponent} from './purchase-history/purchase-history.component';
import {ManagementComponent} from './management/management.component';
import {StatisticsComponent} from './statistics/statistics.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HistoryComponent,
    LoginComponent,
    PurchaseHistoryComponent,
    ManagementComponent,
    StatisticsComponent,
    NoPageFoundComponent
  ],
  imports: [
    CommonsModule,
    HttpClientModule,
    PhonesModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatTableModule,
    MatSortModule,
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
