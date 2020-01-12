import { Component, OnInit } from '@angular/core';
import {UserService} from '../../user.service';

@Component({
  selector: 'app-add-job',
  templateUrl: './add-job.component.html',
  styleUrls: ['./add-job.component.css']
})
export class AddJobComponent implements OnInit {
  description: any;
  title: any;
  period: any;

  constructor(public service: UserService) { }

  ngOnInit() {
  }

  addJob() {

  }
}
