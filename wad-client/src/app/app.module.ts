import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './user.service';
import {ProgrammingComponent} from './programming/programming.component';
import {LoginComponent} from './login/login.component';
import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {AppRoutingModule} from './app-routing.module';
import {ErrorStateMatcher, MatNativeDateModule, ShowOnDirtyErrorStateMatcher} from '@angular/material/core';
import {HomeModule} from './phones/home.module';
import {CommonsModule} from './commons.module';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';
import {
  MatBadgeModule,
  MatBottomSheetModule, MatCheckboxModule, MatChipsModule,
  MatDialogModule,
  MatExpansionModule,
  MatGridListModule,
  MatStepperModule
} from '@angular/material';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MDBRootModule} from 'angular-bootstrap-md';
import {RdfComponent} from './rdf/rdf.component';

@NgModule({
  declarations: [
    AppComponent,
    ProgrammingComponent,
    LoginComponent,
    NoPageFoundComponent,
    RdfComponent
  ],
  imports: [
    CommonsModule,
    MatGridListModule,
    MatExpansionModule,
    MatStepperModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatCheckboxModule,
    MatChipsModule,
    HttpClientModule,
    HomeModule,
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
  ],
  entryComponents: [
  ],
  providers: [
    UserService,
    MatNativeDateModule,
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
