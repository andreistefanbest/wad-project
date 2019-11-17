import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl} from '@angular/forms';
import {Validator} from './validator';

@Component({
  selector: 'app-form-control-error-display',
  templateUrl: './form-control-error-display.component.html',
  styleUrls: ['./form-control-error-display.component.css']
})
export class FormControlErrorDisplayComponent implements OnInit {

  @Input()
  public submitted: boolean;

  @Input()
  public formControl: AbstractControl;

  @Input()
  public validators: Validator[];

  constructor() { }

  ngOnInit() {
  }

}
