import {Component, OnInit} from '@angular/core';
import {take} from 'rxjs/operators';
import {UserService} from '../user.service';

@Component({
  selector: 'app-products',
  templateUrl: './programming.component.html',
  styleUrls: ['./programming.component.css']
})
export class ProgrammingComponent implements OnInit {
  public numProgramming: any = 0;
  public persons: [];

  constructor(private userService: UserService) {
  }


  ngOnInit(): void {
    this.userService.getPersons().subscribe((persons: []) => {
      console.log(persons);
      this.persons = persons;
    });
    this.userService.getNumOfProgramming().pipe(take(1)).subscribe(result => {
      this.numProgramming = result;
    });
  }
}
