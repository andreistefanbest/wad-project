import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  styleUrls: ['./compare.component.css']
})
export class CompareComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private phones) {}

  ngOnInit() {
  }

  diff(specName: string): boolean {
    const spec = this.phones.phones[0].specsId[specName];
    return this.phones.phones.filter(phone => phone.specsId[specName] === spec).length < this.phones.phones.length;
  }
}
