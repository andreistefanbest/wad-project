import { Component, OnInit } from '@angular/core';
import {PhonesService} from '../phones/phones.service';
import {take} from 'rxjs/operators';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css']
})
export class ManagementComponent implements OnInit {

  phonesDS: any;

  constructor(private phonesService: PhonesService) { }

  ngOnInit() {
    this.phonesService.getPhones().pipe(take(1)).subscribe((phones) => {
      this.phonesDS = phones;
    });
  }

  add() {

  }
}
