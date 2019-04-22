import {Component, OnInit} from '@angular/core';
import {PhonesService} from './phones.service';
import {take} from 'rxjs/operators';
import {MatDialog, MatSnackBar} from '@angular/material';
import {BuyPhoneComponent} from './buy-phone/buy-phone.component';
import {AddReviewComponent} from './add-review/add-review.component';
import {UserService} from '../user.service';

@Component({
  selector: 'app-phones',
  templateUrl: './phones.component.html',
  styleUrls: ['./phones.component.css']
})
export class PhonesComponent implements OnInit {
  private static DIALOG_WIDTH = '40%';

  tileProperties: {};
  phonesDS: any;

  constructor(private phonesService: PhonesService,
              private userService: UserService,
              public dialog: MatDialog,
              private snackBar: MatSnackBar) {
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
      if (result === undefined) {
        return;
      }

      this.userService.buyPhone(result).pipe(take(1)).subscribe((purchase) => {
        // @ts-ignore
        this.snackBar.open('Purchase successful! Id: ' + purchase.id, '', {
          duration: 3500,
        });
      });
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
