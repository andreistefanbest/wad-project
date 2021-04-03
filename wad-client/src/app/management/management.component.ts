import {Component, OnInit} from '@angular/core';
import {PhonesService} from '../phones/phones.service';
import {MatDialog} from '@angular/material/dialog';
import {AddEditPhoneComponent} from './add-edit-phone/add-edit-phone.component';
import {AddButtonModel} from './add-button.model';
import {AddBrandComponent} from './add-brand/add-brand.component';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.scss']
})
export class ManagementComponent implements OnInit {

  availableButtons: AddButtonModel[] = [
    {
      icon: 'insights',
      caption: 'Brand',
      fn: 'addBrand'
    },
    {
      icon: 'phone',
      caption: 'Phone',
      fn: 'add'
    },
  ];

  buttons: AddButtonModel[] = [];

  fabToggleState = 'inactive';

  phonesDS: any;

  constructor(private phonesService: PhonesService,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.phonesService.getPhones().subscribe((phones) => {
      this.phonesDS = phones;
    });
  }

  add() {
    const dialogRef = this.dialog.open(AddEditPhoneComponent, {
      width: (window.innerWidth < 760 ? '100%' : '70%'),
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result == null) {
        return;
      }

      this.phonesService.add(result).subscribe((phone) => {
        this.phonesDS.push(phone);
      });
    });
  }

  delete(phone) {
    if (confirm('Are you sure you want to delete ' + phone.name + '?')) {
      this.phonesService.delete(phone.id).subscribe(() => {
        const index = this.phonesDS.findIndex(x => x === phone);
        this.phonesDS.splice(index, 1);
      });
    }
  }

  update(phone) {
    const dialogRef = this.dialog.open(AddEditPhoneComponent, {
      width: (window.innerWidth < 760 ? '100%' : '70%'),
      data: phone
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result == null) {
        return;
      }

      this.phonesService.update(result).subscribe((updatedPhone) => {
        const index = this.phonesDS.findIndex(x => x === phone);
        this.phonesDS[index] = updatedPhone;
      });
    });
  }

  showItems() {
    this.fabToggleState = 'active';
    this.buttons = this.availableButtons;
  }

  hideItems() {
    this.fabToggleState = 'inactive';
    this.buttons = [];
  }

  onToggleFab() {
    this.buttons.length ? this.hideItems() : this.showItems();
  }

  addBrand() {
    const dialogRef = this.dialog.open(AddBrandComponent, {
      width: (window.innerWidth < 760 ? '100%' : '70%'),
      data: null
    });

    dialogRef.afterClosed().subscribe((newBrandName: string) => {
      if (newBrandName == null) {
        return;
      }

      this.phonesService.addBrand(newBrandName).subscribe();
    });
  }

  onFabClick(fn) {
    this[fn]();
  }
}
