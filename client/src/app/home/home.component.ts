import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {PatientDetailsComponent} from "./patient-details/patient-details.component";
import {AddPatientComponent} from "./add-patient/add-patient.component";
import {PatientModel} from "./add-patient/patient.model";

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  patientDS: PatientModel[] = [];

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
    if (localStorage.getItem("patients") == null) {
      localStorage.setItem("patients", JSON.stringify([]));
    }
    this.patientDS = JSON.parse(localStorage.getItem("patients"));
  }

  moreDetails(data: PatientModel) {
    const dialogRef = this.dialog.open(PatientDetailsComponent, {
      width: (window.innerWidth < 760 ? '100%' : '40%'), data
    });
  }

  onAddPatient() {
    this.dialog.open(AddPatientComponent, {
      width: (window.innerWidth < 760 ? '100%' : '40%')
    }).afterClosed().subscribe((patient: PatientModel) => {
      if (patient == null) {
        return;
      }

      if (localStorage.getItem("patients") == null) {
        localStorage.setItem("patients", JSON.stringify([]));
      }

      let patientList: any[] = JSON.parse(localStorage.getItem("patients"));
      patientList.push(patient);
      this.patientDS = patientList;

      localStorage.setItem("patients", JSON.stringify(patientList));
    });
  }
}
