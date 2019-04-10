import {NgModule} from '@angular/core';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDialogModule} from '@angular/material/dialog';
import {MatStepperModule} from '@angular/material/stepper';

import {PhonesRoutingModule} from './phones-routing.module';
import {PhonesComponent} from './phones.component';
import {PhonesService} from './phones.service';
import {CommonsModule} from '../commons.module';
import { BuyPhoneComponent } from './buy-phone/buy-phone.component';
import { AddReviewComponent } from './add-review/add-review.component';

@NgModule({
  declarations: [
    PhonesComponent,
    BuyPhoneComponent,
    AddReviewComponent
  ],
  imports: [
    CommonsModule,
    MatGridListModule,
    MatExpansionModule,
    MatStepperModule,
    MatDialogModule,
    PhonesRoutingModule
  ],
  providers: [
    PhonesService
  ],
  entryComponents: [
    BuyPhoneComponent,
    AddReviewComponent
  ]
})
export class PhonesModule { }
