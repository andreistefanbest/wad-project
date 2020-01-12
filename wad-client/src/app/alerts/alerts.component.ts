import {Component, Inject, OnInit, ViewEncapsulation} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.less'],
  encapsulation: ViewEncapsulation.None
})
export class AlertsComponent implements OnInit {
  incident: any;

  constructor(@Inject(MAT_DIALOG_DATA) private data) { }

  ngOnInit() {
  }

}
