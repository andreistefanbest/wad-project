import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './user.service';
import {MatNativeDateModule} from '@angular/material/core';
import {LoadingBarHttpClientModule} from '@ngx-loading-bar/http-client';
import {DatePipe} from '@angular/common';
import {
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule, MatDatepickerModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule, MatRadioModule,
  MatSelectModule
} from '@angular/material';
import { CalculatorComponent } from './calculator/calculator.component';
import {CommonsModule} from './commons/commons.module';
import { ContactComponent } from './contact/contact.component';
import { RoutingModule } from './routing.module';
import {Lab3Component} from './lab3/lab3.component';
import {RouterModule} from '@angular/router';
import {InMemoryWebApiModule} from 'angular-in-memory-web-api';
import {DataService} from './data.service';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductDetaislComponent} from './product-detaisl/product-detaisl.component';

@NgModule({
  declarations: [
    AppComponent,
    CalculatorComponent,
    ContactComponent,
    Lab3Component,
    ProductListComponent,
    ProductDetaislComponent
  ],
  imports: [
    CommonsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LoadingBarHttpClientModule,
    MatCardModule,
    MatDialogModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatRadioModule,
    MatNativeDateModule,
    RoutingModule,
    RouterModule,
    InMemoryWebApiModule.forRoot(DataService)
  ],
  exports: [
  ],
  entryComponents: [
  ],
  providers: [
    UserService,
    MatNativeDateModule,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
