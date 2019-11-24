import {NgModule} from '@angular/core';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDialogModule} from '@angular/material/dialog';
import {MatStepperModule} from '@angular/material/stepper';
import {MatBadgeModule} from '@angular/material/badge';

import {HomeComponent} from './home.component';
import {CommonsModule} from '../commons.module';
import {UserService} from '../user.service';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatChipsModule} from '@angular/material/chips';
import {HomeRoutingModule} from './home-routing.module';
import {AddJobComponent} from './add-job/add-job.component';

@NgModule({
  declarations: [
    HomeComponent,
    AddJobComponent
  ],
  imports: [
    CommonsModule,
    MatGridListModule,
    MatExpansionModule,
    MatStepperModule,
    MatDialogModule,
    MatBadgeModule,
    MatSnackBarModule,
    MatBottomSheetModule,
    MatCheckboxModule,
    MatChipsModule,
    HomeRoutingModule
  ],
  providers: [
    UserService
  ],
  entryComponents: [
    AddJobComponent
  ]
})
export class HomeModule { }
