import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ErrorStateMatcherImpl} from '../../utils/error-state-matcher-impl';
import {PhonesService} from '../../phones/phones.service';

@Component({
  selector: 'app-add-edit-phone',
  templateUrl: './add-edit-phone.component.html',
  styleUrls: ['./add-edit-phone.component.css']
})
export class AddEditPhoneComponent implements OnInit {
  finishAction: string;
  title: string;

  addEditFormGroup: FormGroup;
  matcher: ErrorStateMatcherImpl;
  brandsDS: any;
  phoneTypeDS = [{id: 1, name: 'smartphone'}, {id: 2, name: 'dumb phone'}];
  bluetoothDS = [1, 0];
  canClose: boolean;
  result: any;

  constructor(@Inject(MAT_DIALOG_DATA) private phone,
              private phoneService: PhonesService,
              private formBuilder: FormBuilder) {
    this.result = phone;
    this.finishAction = phone ? 'Update' : 'Add';
    this.title = phone ? 'Update ' + phone.name : 'Add phone';
  }

  ngOnInit() {
    this.matcher = new ErrorStateMatcherImpl();
    this.phoneService.getBrands().subscribe((brands) => {
      this.brandsDS = brands;
    });

    this.addEditFormGroup = this.formBuilder.group({
      nameCtrl: ['', Validators.required],
      phoneCtrl: ['', Validators.required],
      photoCtrl: ['', Validators.required],
      priceCtrl: ['', Validators.compose([Validators.required, Validators.pattern(/^[^0]\d*$/)])],
      ramCtrl: ['', Validators.compose([Validators.required, Validators.pattern(/^[^0]\d*$/)])],
      ramTypeCtrl: ['', Validators.required],
      cpuCtrl: ['', Validators.required],
      clockSpeedCtrl: ['', Validators.compose([Validators.required, Validators.pattern(/^[^0]\d*\.?\d+$/)])],
      bluetoothCtrl: ['', Validators.required],
      networkCtrl: ['', Validators.required],
      storageCtrl: ['', Validators.compose([Validators.required, Validators.pattern(/^[^0]\d*$/)])],
      dateCtrl: ['', Validators.required],
      typeCtrl: ['', Validators.required],
    });

    if (this.phone) {
      this.addEditFormGroup.controls.nameCtrl.setValue(this.phone.name);
      this.addEditFormGroup.controls.phoneCtrl.setValue(this.phone.brand);
      this.addEditFormGroup.controls.photoCtrl.setValue(this.phone.imageLink);
      this.addEditFormGroup.controls.priceCtrl.setValue(this.phone.price);
      this.addEditFormGroup.controls.ramCtrl.setValue(this.phone.specs.ramCapacity);
      this.addEditFormGroup.controls.ramTypeCtrl.setValue(this.phone.specs.ramType);
      this.addEditFormGroup.controls.cpuCtrl.setValue(this.phone.specs.cpu);
      this.addEditFormGroup.controls.clockSpeedCtrl.setValue(this.phone.specs.clockSpeed);
      this.addEditFormGroup.controls.bluetoothCtrl.setValue(this.phone.specs.bluetooth ? 1 : 0);
      this.addEditFormGroup.controls.networkCtrl.setValue(this.phone.specs.networkSupport);
      this.addEditFormGroup.controls.storageCtrl.setValue(this.phone.specs.storageCapacity);
      this.addEditFormGroup.controls.dateCtrl.setValue(new Date(this.phone.releaseDate));
      this.addEditFormGroup.controls.typeCtrl.setValue(this.phone.phoneType);
    }

    this.addEditFormGroup.valueChanges.subscribe(() => {
      this.detailsUpdate();
    });

    this.detailsUpdate();
  }

  private detailsUpdate() {
    this.canClose = this.addEditFormGroup.valid;
    if (!this.addEditFormGroup.valid) {
      return;
    }

    this.result = {
      id: this.phone ? this.phone.id : null,
      name: this.addEditFormGroup.controls.nameCtrl.value,
      brand: this.addEditFormGroup.controls.phoneCtrl.value,
      imageLink: this.addEditFormGroup.controls.photoCtrl.value,
      price: this.addEditFormGroup.controls.priceCtrl.value,
      releaseDate: Date.parse(this.addEditFormGroup.controls.dateCtrl.value),
      phoneType: this.addEditFormGroup.controls.typeCtrl.value,
      specs: {
        id: this.phone ? this.phone.specs.id : null,
        ramCapacity: this.addEditFormGroup.controls.ramCtrl.value,
        ramType: this.addEditFormGroup.controls.ramTypeCtrl.value,
        cpu: this.addEditFormGroup.controls.cpuCtrl.value,
        clockSpeed: this.addEditFormGroup.controls.clockSpeedCtrl.value,
        bluetooth: this.addEditFormGroup.controls.bluetoothCtrl.value,
        networkSupport: this.addEditFormGroup.controls.networkCtrl.value,
        storageCapacity: this.addEditFormGroup.controls.storageCtrl.value,
      }
    };
  }

  brandDisplay(brand) {
    return brand.name;
  }

  bluetoothDisplay(bluetooth) {
    if (bluetooth == null) {
      return;
    }
    return bluetooth === 1 ? 'Yes' : 'No';
  }

  phoneTypeDisplay(type) {
    return type.name;
  }
}
