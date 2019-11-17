import {AfterViewInit, Component, ContentChildren, Input, OnInit, QueryList} from '@angular/core';
import {FormControlErrorDisplayComponent} from '../form-control-error-display/form-control-error-display.component';

@Component({
  selector: 'app-cool-form',
  templateUrl: './cool-form.component.html',
  styleUrls: ['./cool-form.component.css']
})
export class CoolFormComponent implements OnInit, AfterViewInit {

  @ContentChildren(FormControlErrorDisplayComponent) public errDisplays: QueryList<FormControlErrorDisplayComponent>;

  @Input()
  public submitted;

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    console.log(this.errDisplays);
  }

}
