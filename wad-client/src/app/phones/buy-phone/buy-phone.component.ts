import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ErrorStateMatcherImpl} from '../../utils/error-state-matcher-impl';

@Component({
  selector: 'app-buy-phone',
  templateUrl: './buy-phone.component.html',
  styleUrls: ['./buy-phone.component.css']
})
export class BuyPhoneComponent implements OnInit {

  nameFormGroup: FormGroup;
  addressFormGroup: FormGroup;

  matcher: ErrorStateMatcherImpl;

  constructor(private dialogRef: MatDialogRef<BuyPhoneComponent>,
              private formBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) private phone) { }

  ngOnInit() {
    this.matcher = new ErrorStateMatcherImpl();
    this.nameFormGroup = this.formBuilder.group({
      nameCrtl: ['', Validators.required]
    });
    this.addressFormGroup = this.formBuilder.group({
      countryCrtl: ['', Validators.required],
      countyCrtl: ['', Validators.required],
      cityCrtl: ['', Validators.required],
      streetCrtl: ['', Validators.required],
      buildingCrtl: ['', Validators.required],
      phoneCrtl: ['', [Validators.required, Validators.pattern(/^07\d{8}$/)]]
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
