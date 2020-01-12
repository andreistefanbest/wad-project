import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';
import {take} from 'rxjs/operators';
import {AddJobComponent} from './add-job/add-job.component';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-phones',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public persons: any[] = [];
  public numOfTechSubjects: any = 0;

  constructor(public userService: UserService,
              public dialog: MatDialog) {

  }

  ngOnInit() {
    let currentUser = this.userService.getCurrentUser();
    if (!currentUser) {
      return;
    }
    this.userService.getPersons().subscribe((persons: []) => {
      this.persons = persons;
    });
    this.numOfTechnologies();
  }

  numOfTechnologies() {
    let currentUser = this.userService.getCurrentUser();
    if (!currentUser) {
      return;
    }

    this.userService.getNumOfTechSubjects(currentUser.mail).pipe(take(1)).subscribe((result) => {
      this.numOfTechSubjects = result;
    });
  }

  isLoggedPerson(person) {
    let currentUser = this.userService.getCurrentUser();
    if (!currentUser) {
      return false;
    }

    return currentUser.mail == person.email;
  }

  deleteJob(email, job) {
    let person: any = this.persons.find(p => p.email == email);
    let jobIndex = person.jobs.findIndex(j => j.title == job.title);
    person.jobs.splice(jobIndex, 1);

    this.userService.deleteJob(email, job.title).pipe(take(1)).subscribe();
  }

  addJob(email: any) {
    let dialogRef = this.dialog.open(AddJobComponent, {
      width: (window.innerWidth < 760 ? '100%' : '70%'),
      data: { email }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result == null) {
        return;
      }

      this.userService.addJob(email, result).pipe(take(1)).subscribe();

      let person = this.persons.find(p => p.email == email);
      person.jobs.push(result);
    });
  }
}
