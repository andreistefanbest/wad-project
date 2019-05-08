import {Component, OnInit} from '@angular/core';
import {PhonesService} from './phones.service';
import {take} from 'rxjs/operators';
import {MatBottomSheet, MatBottomSheetRef, MatCheckboxChange, MatDialog, MatSnackBar} from '@angular/material';
import {BuyPhoneComponent} from './buy-phone/buy-phone.component';
import {ReviewsComponent} from './reviews/reviews.component';
import {UserService} from '../user.service';
import {SortComponent} from './sort/sort.component';
import {GlobalConstants} from '../utils/GlobalConstants';
import {CompareComponent} from './compare/compare.component';

@Component({
  selector: 'app-phones',
  templateUrl: './phones.component.html',
  styleUrls: ['./phones.component.css']
})
export class PhonesComponent implements OnInit {

  tileProperties: {};
  comparisonMode = false;
  phonesDS: any;

  constructor(private phonesService: PhonesService,
              private userService: UserService,
              public dialog: MatDialog,
              private snackBar: MatSnackBar,
              private bottomSheet: MatBottomSheet) {
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
    if (localStorage.getItem(GlobalConstants.LOGGED_USER_KEY) == null) {
      alert('You must be logged in to buy a phone!');
      return;
    }
    const dialogRef = this.dialog.open(BuyPhoneComponent, {
      width: (window.innerWidth < 760 ? '100%' : '40%'),
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

  displayReviews(phone) {
    this.dialog.open(ReviewsComponent, {
      width: (window.innerWidth < 760 ? '100%' : '70%'),
      data: { phone }
    });
  }

  onSort() {
    const sortSheet: MatBottomSheetRef = this.bottomSheet.open(SortComponent);
    sortSheet.afterDismissed().subscribe((res) => {
      switch (res) {
        case 'name':
          this.phonesDS = this.phonesDS.sort((p1, p2) => p1.name.localeCompare(p2.name));
          break;
        case 'price':
          this.phonesDS = this.phonesDS.sort((p1, p2) => p1.price - p2.price);
          break;
        case 'price-desc':
          this.phonesDS = this.phonesDS.sort((p1, p2) => p2.price - p1.price);
          break;
        case 'type':
          this.phonesDS = this.phonesDS.sort((p1, p2) => p1.typeId.name.localeCompare(p2.typeId.name));
          break;
        case 'compare':
          this.comparisonMode = true;
          break;
      }
    });
  }

  onCompareSelect(phone: any, event: MatCheckboxChange) {
    phone.isSelected = event.checked;
  }

  exitComparisonMode() {
    this.comparisonMode = false;
    this.phonesDS.forEach(phone => phone.isSelected = false);
  }

  compare() {
    if (this.phonesDS.filter(phone => phone.isSelected).length < 2) {
      alert('Select at least 2 phones to compare.');
      return;
    }
    const dialogRef = this.dialog.open(CompareComponent, {
      width: (window.innerWidth < 760 ? '100%' : '90%'),
      data: {
        phones: this.phonesDS.filter(phone => phone.isSelected)
      }
    });

    this.exitComparisonMode();
  }
}
