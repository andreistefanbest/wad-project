import {NgModule} from '@angular/core';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDialogModule} from '@angular/material/dialog';
import {MatStepperModule} from '@angular/material/stepper';
import {MatBadgeModule} from '@angular/material/badge';

import {PhonesRoutingModule} from './phones-routing.module';
import {PhonesComponent} from './phones.component';
import {PhonesService} from './phones.service';
import {CommonsModule} from '../commons.module';
import { BuyPhoneComponent } from './buy-phone/buy-phone.component';
import { AddReviewComponent } from './add-review/add-review.component';
import {UserService} from '../user.service';
import {MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    PhonesComponent,
    BuyPhoneComponent,
    AddReviewComponent,
  ],
  imports: [
    CommonsModule,
    MatGridListModule,
    MatExpansionModule,
    MatStepperModule,
    MatDialogModule,
    MatBadgeModule,
    MatSnackBarModule,
    PhonesRoutingModule
  ],
  providers: [
    PhonesService,
    UserService
  ],
  entryComponents: [
    BuyPhoneComponent,
    AddReviewComponent
  ]
})
export class PhonesModule { }
