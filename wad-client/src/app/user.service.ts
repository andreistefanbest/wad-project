import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {GlobalConstants} from './utils/GlobalConstants';

@Injectable()
export class UserService {
  private PATH_TO_CONTROLLER = 'http://localhost:1337/localhost:8080/user/';

  public userChanged: EventEmitter<any> = new EventEmitter();

  constructor(private http: HttpClient) {

  }

  public signIn(name, mail, password) {
    let httpParams: HttpParams = new HttpParams();
    httpParams = httpParams.set('name', name);
    httpParams = httpParams.set('mail', mail);
    httpParams = httpParams.set('password', password);

    return this.http.put(this.PATH_TO_CONTROLLER + 'signIn',
      undefined, {params: httpParams});
  }
  public logIn(mail, password) {
    let httpParams: HttpParams = new HttpParams();
    httpParams = httpParams.set('mail', mail);
    httpParams = httpParams.set('password', password);

    return this.http.post(this.PATH_TO_CONTROLLER + 'logIn',
      undefined, {params: httpParams});
  }

  public getCurrentUser() {
    return JSON.parse(localStorage.getItem(GlobalConstants.LOGGED_USER_KEY));
  }

  public getPersons() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'persons');
  }

  public getNumOfTechSubjects(mail) {
    return this.http.get(this.PATH_TO_CONTROLLER + 'tech-subjects?mail=' + mail);
  }

  public getNumOfProgramming() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'programming');
  }

  public deleteJob(mail, jobName) {
    return this.http.delete(this.PATH_TO_CONTROLLER + 'delete-job?mail=' + mail + '&jobTitle=' + jobName);
  }

  public addJob(mail, job) {
    return this.http.post(this.PATH_TO_CONTROLLER + 'add-job', job, {params: new HttpParams().set('mail', mail)});
  }

  public uploadFile(dto) {
    return this.http.post(this.PATH_TO_CONTROLLER + 'upload-file', dto);
  }

  public addEditCompany(who, newCompany) {
    let httpParams = new HttpParams().set("who", who).set('newCompany', newCompany);
    return this.http.post(this.PATH_TO_CONTROLLER + 'company', null, {params: httpParams});
  }

  public queryProfessions(profession: string) {
    return this.http.get(this.PATH_TO_CONTROLLER + 'profession?profession=' + profession);
  }

  public info() {
    return this.http.get(this.PATH_TO_CONTROLLER + 'info');
  }
}
