import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';

@Component({
  selector: 'app-phones',
  templateUrl: './phones.component.html',
  styleUrls: ['./phones.component.css']
})
export class PhonesComponent implements OnInit {

  public persons: [];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getPersons().subscribe((persons: []) => {
      console.log(persons);
      this.persons = persons;
    });
  }
}
