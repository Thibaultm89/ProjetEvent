import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Event } from 'src/app/models/event.model';
import { DateWithoutTime } from 'src/app/models/date-without-time.model';
@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit {

  public hourList = [null, '00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10',
   '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'];

  public event: Event;

  public eventForm: FormGroup;

  constructor(private fb: FormBuilder) {

    this.event = new Event();
    this.event.start = new DateWithoutTime();
    this.event.finish = new DateWithoutTime();

    this.eventForm = this.fb.group({

      name: this.fb.control(this.event.name, [Validators.required]),

      yearstart: this.fb.control(this.event.start.year, [Validators.required]),
      monthstart: this.fb.control(this.event.start.monthValue, [Validators.required]),
      daystart: this.fb.control(this.event.start.dayOfMonth, [Validators.required]),

      yearfinish: this.fb.control(this.event.finish.year, [Validators.required]),
      monthfinish: this.fb.control(this.event.finish.monthValue, [Validators.required]),
      dayfinish: this.fb.control(this.event.finish.dayOfMonth, [Validators.required]),

    });
  }

  ngOnInit() {
  }

  public submitForm() {

    const newValues = this.eventForm.value;

    const newEvent = new Event();
    newEvent.start = new DateWithoutTime();
    newEvent.finish = new DateWithoutTime();

    newEvent.name = newValues.name;

    newEvent.start.year = newValues.yearstart;
    newEvent.start.monthValue = newValues.monthstart;
    newEvent.start.dayOfMonth = newValues.daystart;

    newEvent.finish.year = newValues.yearfinish;
    newEvent.finish.monthValue = newValues.monthfinish;
    newEvent.finish.dayOfMonth = newValues.dayfinish;

    this.event = newEvent;

    console.log('submit', this.event, newValues);
  }

  public hasNameError() {
    const control = this.eventForm.get('name');
    return control.errors && control.errors.required;
  }

  public hasYearstartError() {
    const control = this.eventForm.get('yearstart');
    return control.errors && control.errors.required;
  }

  public hasMonthstartError() {
    const control = this.eventForm.get('monthstart');
    return control.errors && control.errors.required;
  }

  public hasDaystartError() {
    const control = this.eventForm.get('daystart');
    return control.errors && control.errors.required;
  }

  public hasYearfinishError() {
    const control = this.eventForm.get('yearfinish');
    return control.errors && control.errors.required;
  }

  public hasMonthfinishError() {
    const control = this.eventForm.get('monthfinish');
    return control.errors && control.errors.required;
  }

  public hasDayfinishError() {
    const control = this.eventForm.get('dayfinish');
    return control.errors && control.errors.required;
  }
}
