import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ErrorStateMatcherImpl} from '../../utils/error-state-matcher-impl';
import {UserService} from '../../user.service';
import {GlobalConstants} from '../../utils/GlobalConstants';

@Component({
  selector: 'app-buy-phone',
  templateUrl: './buy-phone.component.html',
  styleUrls: ['./buy-phone.component.css']
})
export class BuyPhoneComponent implements OnInit {

  nameFormGroup: FormGroup;
  addressFormGroup: FormGroup;
  matcher: ErrorStateMatcherImpl;

  purchase: {};
  canPurchase: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) private phone,
              private formBuilder: FormBuilder,
              private userService: UserService) {

  }

  ngOnInit() {
    this.matcher = new ErrorStateMatcherImpl();

    this.nameFormGroup = this.formBuilder.group({
      nameCtrl: ['', Validators.required],
      phoneCrtl: ['', [Validators.required, Validators.pattern(/^07\d{8}$/)]]
    });

    this.addressFormGroup = this.formBuilder.group({
      countryCrtl: ['', Validators.required],
      countyCrtl: ['', Validators.required],
      cityCrtl: ['', Validators.required],
      streetCrtl: ['', Validators.required],
      buildingCrtl: ['', Validators.required],
    });

    this.nameFormGroup.valueChanges.subscribe(() => {
      this.updatePurchase();
    });

    this.addressFormGroup.valueChanges.subscribe(() => {
      this.updatePurchase();
    });

    this.userService.getUser(JSON.parse(localStorage.getItem(GlobalConstants.LOGGED_USER_KEY)).userId).subscribe((user: {
      fullName: string,
      phone: string,
      address: {
        country: string,
        county: string,
        city: string,
        street: string,
        building: string
      }}) => {

      if (user.fullName) {
        this.nameFormGroup.controls.nameCtrl.setValue(user.fullName);
      }

      if (user.phone) {
        this.nameFormGroup.controls.phoneCrtl.setValue('0' + user.phone);
      }

      if (user.address) {
        this.addressFormGroup.controls.countryCrtl.setValue(user.address.country);
        this.addressFormGroup.controls.countyCrtl.setValue(user.address.county);
        this.addressFormGroup.controls.cityCrtl.setValue(user.address.city);
        this.addressFormGroup.controls.streetCrtl.setValue(user.address.street);
        this.addressFormGroup.controls.buildingCrtl.setValue(user.address.building);
      }
    });
  }

  updatePurchase() {
    this.purchase = {
      phoneId: this.phone.id,
      userId: JSON.parse(localStorage.getItem(GlobalConstants.LOGGED_USER_KEY)).userId,
      receiverName: this.nameFormGroup.controls.nameCtrl.value,
      receiverPhone: this.nameFormGroup.controls.phoneCrtl.value,
      address: {
        country: this.addressFormGroup.controls.countryCrtl.value,
        county: this.addressFormGroup.controls.countyCrtl.value,
        city: this.addressFormGroup.controls.cityCrtl.value,
        street: this.addressFormGroup.controls.streetCrtl.value,
        building: this.addressFormGroup.controls.buildingCrtl.value,
      }
    };
  }

  cantPurchase($event: MouseEvent) {
    this.canPurchase = false;
  }

  cannPurchase($event: MouseEvent) {
    this.canPurchase = true;
  }
}
