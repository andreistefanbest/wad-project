import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ErrorStateMatcherImpl} from '../../utils/error-state-matcher-impl';
import {UserService} from '../../user.service';

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

  constructor(private dialogRef: MatDialogRef<BuyPhoneComponent>,
              private formBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) private phone,
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
      this.purchase = {
        phoneId: this.phone.id,
        userId: 1,
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
    });

    this.addressFormGroup.valueChanges.subscribe(() => {
      this.purchase = {
        phoneId: this.phone.id,
        userId: 1,
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
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  cantPurchase($event: MouseEvent) {
    this.canPurchase = false;
  }

  cannPurchase($event: MouseEvent) {
    this.canPurchase = true;
  }
}
