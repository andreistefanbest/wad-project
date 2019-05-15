import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartsModule, WavesModule } from 'angular-bootstrap-md';
import {CommonsModule} from '../commons.module';
import {StatisticsComponent} from './statistics.component';
import {StatisticsService} from './statistics.service';

@NgModule({
  declarations: [
    StatisticsComponent
  ],
  imports: [
    CommonModule,
    CommonsModule,
    ChartsModule,
    WavesModule
  ],
  exports: [
    StatisticsComponent
  ],
  providers: [
    StatisticsService
  ]
})
export class StatisticsModule { }
