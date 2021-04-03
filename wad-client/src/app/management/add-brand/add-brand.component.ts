import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ErrorStateMatcherImpl} from '../../utils/error-state-matcher-impl';

@Component({
  selector: 'app-add-brand',
  templateUrl: './add-brand.component.html',
  styleUrls: ['./add-brand.component.css']
})
export class AddBrandComponent implements OnInit {

  addEditFormGroup: FormGroup;
  matcher: ErrorStateMatcherImpl;
  canClose: boolean;
  brandName: string;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.matcher = new ErrorStateMatcherImpl();

    this.addEditFormGroup = this.formBuilder.group({
      nameCtrl: ['', Validators.required],
    });

    this.addEditFormGroup.valueChanges.subscribe(() => {
      this.onFormChanges();
    });
  }

  onFormChanges() {
    this.canClose = this.addEditFormGroup.valid;
    if (!this.addEditFormGroup.valid) {
      return;
    }

    this.brandName = this.addEditFormGroup.controls.nameCtrl.value;
  }

}
