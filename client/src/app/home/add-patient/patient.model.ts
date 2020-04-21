import {PatientDetailsModel} from "./patient-details.model";

export class PatientModel {
  public name;
  public age;
  public pic;

  public details: PatientDetailsModel = new PatientDetailsModel();
}
