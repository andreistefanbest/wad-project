import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './user.service';
import {MatNativeDateModule} from '@angular/material/core';
import {CommonsModule} from './commons.module';
import {LoadingBarHttpClientModule} from '@ngx-loading-bar/http-client';
import {DatePipe} from '@angular/common';
import {AlertsComponent} from './alerts/alerts.component';
import {MatButtonModule, MatCardModule, MatDialogModule, MatFormFieldModule, MatInputModule} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    AlertsComponent
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
    MatInputModule
  ],
  exports: [
  ],
  entryComponents: [
    AlertsComponent
  ],
  providers: [
    UserService,
    MatNativeDateModule,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
