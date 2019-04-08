import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PhonesComponent} from './phones.component';

const routes: Routes = [
  {path: 'phones', component: PhonesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PhonesRoutingModule { }
