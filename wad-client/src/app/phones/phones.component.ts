import {Component, OnInit} from '@angular/core';
import {PhonesService} from './phones.service';
import {take} from 'rxjs/operators';
import {MatDialog} from '@angular/material';
import {BuyPhoneComponent} from './buy-phone/buy-phone.component';
import {AddReviewComponent} from './add-review/add-review.component';

@Component({
  selector: 'app-phones',
  templateUrl: './phones.component.html',
  styleUrls: ['./phones.component.css']
})
export class PhonesComponent implements OnInit {
  private static DIALOG_WIDTH = '50%';

  tileProperties: {};
  phonesDS: any;

  constructor(private phonesService: PhonesService,
              public dialog: MatDialog) {
    this.tileProperties = {
      cols: 1,
      rows: 1
    };
  }

  ngOnInit() {
    this.phonesService.getPhones().pipe(take(1)).subscribe((phones) => {
      this.phonesDS = phones;
    });
  }

  buyPhone(phone) {
    const dialogRef = this.dialog.open(BuyPhoneComponent, {
      width: PhonesComponent.DIALOG_WIDTH,
      data: phone
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  addReview(phone) {
    const dialogRef = this.dialog.open(AddReviewComponent, {
      width: PhonesComponent.DIALOG_WIDTH,
      data: {
      }
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  testSort() {
    this.phonesDS = this.phonesDS.sort((p1, p2) => p1.price - p2.price);
  }
}
