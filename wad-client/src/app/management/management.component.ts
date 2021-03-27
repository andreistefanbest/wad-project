import {Component, OnInit} from '@angular/core';
import {PhonesService} from '../phones/phones.service';
import {take} from 'rxjs/operators';
import {MatDialog} from '@angular/material/dialog';
import {AddEditPhoneComponent} from './add-edit-phone/add-edit-phone.component';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css']
})
export class ManagementComponent implements OnInit {

  phonesDS: any;

  constructor(private phonesService: PhonesService,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.phonesService.getPhones().pipe(take(1)).subscribe((phones) => {
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

      this.phonesService.add(result).pipe(take(1)).subscribe((phone) => {
        this.phonesDS.push(phone);
      });
    });
  }

  delete(phone) {
    if (confirm('Are you sure you want to delete ' + phone.name + '?')) {
      this.phonesService.delete(phone.id).pipe(take(1)).subscribe(() => {
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

      this.phonesService.update(result).pipe(take(1)).subscribe((updatedPhone) => {
        const index = this.phonesDS.findIndex(x => x === phone);
        this.phonesDS[index] = updatedPhone;
      });
    });
  }
}
