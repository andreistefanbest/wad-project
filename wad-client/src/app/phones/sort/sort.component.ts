import { Component, OnInit } from '@angular/core';
import {MatBottomSheetRef} from '@angular/material';

@Component({
  selector: 'app-sort',
  templateUrl: './sort.component.html',
  styleUrls: ['./sort.component.css']
})
export class SortComponent implements OnInit {

  constructor(private bottomSheetRef: MatBottomSheetRef<SortComponent>) { }

  ngOnInit() {
  }

  sort($event: MouseEvent, name: string) {
    this.bottomSheetRef.dismiss(name);
    event.preventDefault();
  }
}
