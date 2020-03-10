import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  contactName = '';
  contactAddress = '';
  isDeleted = true;
  date = new FormControl();
  serializedDate: any = new Date();
  contactGender: any = 'male';
  contactSource: any = 'email';

  constructor() { }

  ngOnInit() {
  }

  saveContact() {

  }
}
