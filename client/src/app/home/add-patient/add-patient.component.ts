import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {PatientModel} from "./patient.model";

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent implements OnInit {
  patient: PatientModel = new PatientModel();

  nameFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.nameFormGroup = this.formBuilder.group({
      nameCtrl: [''],
      ageCtrl: [''],
      subjectCtrl: [''],
      genderCtrl: [''],
      heightCtrl: [''],
      weightCtrl: [''],
      smokeCtrl: [''],
      alcoholCtrl: [''],
      hyperCtrl: [''],
      numbCtrl: [''],
      diabeticRenopathyCtrl: [''],
      retiGradeCtrl: [''],
      eyeCtrl: [''],
      glucoseCtrl: [''],
      whiteCtrl: [''],
      co2Ctrl: [''],
      picCtrl: [''],
      mriCtrl: [''],
    });

    this.nameFormGroup.valueChanges.subscribe(() => {
      this.updatePatient();
    });
  }

  updatePatient() {
    this.patient.name = this.nameFormGroup.controls.nameCtrl.value;
    this.patient.age = this.nameFormGroup.controls.ageCtrl.value;
    this.patient.pic = this.nameFormGroup.controls.picCtrl.value;
    this.patient.details.subject.value = this.nameFormGroup.controls.subjectCtrl.value;
    this.patient.details.gender.value = this.nameFormGroup.controls.genderCtrl.value;
    this.patient.details.height.value = this.nameFormGroup.controls.heightCtrl.value;
    this.patient.details.weight.value = this.nameFormGroup.controls.weightCtrl.value;
    this.patient.details.smoke.value = this.nameFormGroup.controls.smokeCtrl.value;
    this.patient.details.alcohol.value = this.nameFormGroup.controls.alcoholCtrl.value;
    this.patient.details.hyper.value = this.nameFormGroup.controls.hyperCtrl.value;
    this.patient.details.numb.value = this.nameFormGroup.controls.numbCtrl.value;
    this.patient.details.diabeticRenopathy.value = this.nameFormGroup.controls.diabeticRenopathyCtrl.value;
    this.patient.details.retiGrade.value = this.nameFormGroup.controls.retiGradeCtrl.value;
    this.patient.details.eye.value = this.nameFormGroup.controls.eyeCtrl.value;
    this.patient.details.glucose.value = this.nameFormGroup.controls.glucoseCtrl.value;
    this.patient.details.white.value = this.nameFormGroup.controls.whiteCtrl.value;
    this.patient.details.co2.value = this.nameFormGroup.controls.co2Ctrl.value;
    this.patient.details.mri.value = this.nameFormGroup.controls.mriCtrl.value;
  }
}
