import { Component, OnInit } from '@angular/core';
import {PhonesService} from './phones.service';
import {take} from 'rxjs/operators';

@Component({
  selector: 'app-phones',
  templateUrl: './phones.component.html',
  styleUrls: ['./phones.component.css']
})
export class PhonesComponent implements OnInit {

  phonesJSON: any;

  constructor(private phonesService: PhonesService) { }

  ngOnInit() {
    this.phonesService.getPhones().pipe(take(1)).subscribe((phones) => {
      this.phonesJSON = phones;
    });
  }

}
