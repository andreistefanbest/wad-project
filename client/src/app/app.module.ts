import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './user.service';
import {LoginComponent} from './login/login.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {AppRoutingModule} from './app-routing.module';
import {ErrorStateMatcher, MatNativeDateModule, ShowOnDirtyErrorStateMatcher} from '@angular/material/core';
import {CommonsModule} from './commons.module';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort'
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';
import {MatDialogModule} from '@angular/material';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MDBRootModule} from 'angular-bootstrap-md';
import { HomeComponent } from './home/home.component';
import {MatExpansionModule} from "@angular/material/expansion";
import { PatientDetailsComponent } from './home/patient-details/patient-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NoPageFoundComponent,
    HomeComponent,
    PatientDetailsComponent
  ],
  imports: [
    CommonsModule,
    HttpClientModule,
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
    MDBRootModule,
    MatExpansionModule
  ],
  exports: [
  ],
  entryComponents: [
    PatientDetailsComponent
  ],
  providers: [
    UserService,
    MatNativeDateModule,
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }