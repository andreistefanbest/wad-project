import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {PatientModel} from "../add-patient/patient.model";

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private patientData: PatientModel) { }

  ngOnInit() {
  }
}
