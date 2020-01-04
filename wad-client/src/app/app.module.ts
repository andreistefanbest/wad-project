import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './user.service';
import {MatNativeDateModule} from '@angular/material/core';
import {CommonsModule} from './commons.module';
import {LoadingBarHttpClientModule} from '@ngx-loading-bar/http-client';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    CommonsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LoadingBarHttpClientModule,
  ],
  exports: [
  ],
  entryComponents: [
  ],
  providers: [
    UserService,
    MatNativeDateModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
