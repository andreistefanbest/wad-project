import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ErrorStateMatcherImpl} from '../../utils/error-state-matcher-impl';
import {UserService} from '../../user.service';
import {PurchaseService} from './purchase.service';
import {LastPurchaseDTO} from './dto/LastPurchaseDTO';
import {NewPurchaseDTO} from './dto/NewPurchaseDTO';
import {Purchase} from './dto/Purchase';
import {Address} from './dto/Address';

@Component({
  selector: 'app-buy-phone',
  templateUrl: './buy-phone.component.html',
  styleUrls: ['./buy-phone.component.css']
})
export class BuyPhoneComponent implements OnInit {

  nameFormGroup: FormGroup;
  addressFormGroup: FormGroup;
  matcher: ErrorStateMatcherImpl;

  newPurchaseDTO: NewPurchaseDTO;
  canPurchase: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public phone,
              private formBuilder: FormBuilder,
              private userService: UserService,
              private purchaseService: PurchaseService) {

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

    const userId = this.userService.getCurrentUser().userId;
    this.purchaseService.getLastPurchase(userId).subscribe((lastPurchase: LastPurchaseDTO) => {
      this.nameFormGroup.controls.nameCtrl.setValue(lastPurchase.fullName);
      this.nameFormGroup.controls.phoneCrtl.setValue(lastPurchase.phone);
      this.addressFormGroup.controls.countryCrtl.setValue(lastPurchase.country);
      this.addressFormGroup.controls.countyCrtl.setValue(lastPurchase.county);
      this.addressFormGroup.controls.cityCrtl.setValue(lastPurchase.city);
      this.addressFormGroup.controls.streetCrtl.setValue(lastPurchase.street);
      this.addressFormGroup.controls.buildingCrtl.setValue(lastPurchase.building);
    });
  }

  updatePurchase() {
    const purchase = new Purchase();
    purchase.receiverName = this.nameFormGroup.controls.nameCtrl.value;
    purchase.receiverPhone = this.nameFormGroup.controls.phoneCrtl.value;
    purchase.phoneId = this.phone.id;
    purchase.userId = this.userService.getCurrentUser().userId;

    const address = new Address();
    address.country = this.addressFormGroup.controls.countryCrtl.value;
    address.county = this.addressFormGroup.controls.countyCrtl.value;
    address.city = this.addressFormGroup.controls.cityCrtl.value;
    address.street = this.addressFormGroup.controls.streetCrtl.value;
    address.building = this.addressFormGroup.controls.buildingCrtl.value;

    this.newPurchaseDTO = new NewPurchaseDTO();
    this.newPurchaseDTO.purchase = purchase;
    this.newPurchaseDTO.address = address;
  }

  cantPurchase($event: MouseEvent) {
    this.canPurchase = false;
  }

  cannPurchase($event: MouseEvent) {
    this.canPurchase = true;
  }
}
