import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PhonesRoutingModule } from './phones-routing.module';
import { PhonesComponent } from './phones.component';
import {PhonesService} from './phones.service';

@NgModule({
  declarations: [PhonesComponent],
  imports: [
    CommonModule,
    PhonesRoutingModule
  ],
  providers: [
    PhonesService
  ]
})
export class PhonesModule { }
