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
import {ErrorStateMatcher, MatNativeDateModule, ShowOnDirtyErrorStateMatcher} from '@angular/material/core';
import {PhonesModule} from './phones/phones.module';
import {CommonsModule} from './commons.module';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort'
import {HistoryComponent} from './products/history/history.component';
import {LoadingBarHttpClientModule} from '@ngx-loading-bar/http-client';
import {PurchaseHistoryComponent} from './purchase-history/purchase-history.component';
import {ManagementComponent} from './management/management.component';
import {AddEditPhoneComponent} from './management/add-edit-phone/add-edit-phone.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MDBRootModule} from 'angular-bootstrap-md';
import {StatisticsModule} from './statistics/statistics.module';
import {FormControlErrorDisplayComponent} from './utils/form-control-error-display/form-control-error-display.component';
import {CoolFormComponent} from './utils/cool-form/cool-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HistoryComponent,
    LoginComponent,
    FormControlErrorDisplayComponent,
    CoolFormComponent,
    PurchaseHistoryComponent,
    ManagementComponent,
    AddEditPhoneComponent,
    NoPageFoundComponent
  ],
  imports: [
    CommonsModule,
    HttpClientModule,
    PhonesModule,
    StatisticsModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatTableModule,
    MatSortModule,
    AppRoutingModule,
    MatDialogModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    MatNativeDateModule,
    LoadingBarHttpClientModule,
    MDBRootModule
  ],
  exports: [
    FormControlErrorDisplayComponent
  ],
  entryComponents: [
    AddEditPhoneComponent,
    CoolFormComponent
  ],
  providers: [
    UserService,
    ProductsService,
    MatNativeDateModule,
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
