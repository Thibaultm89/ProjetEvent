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

  public yearList: string[] = [null];
  public monthList: string[] = [null];
  public dayList: string[] = [null];

  public event: Event;
  public eventForm: FormGroup;

  constructor(private fb: FormBuilder) {

    this.tabMonth();
    this.tabYear();
    this.tabDay();

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

  public tabMonth() {
    for (let i = 1 ; i < 13 ; i++) {
      if (i < 10) {
        this.monthList[i] = '0' + i.toString();
      } else {
         this.monthList[i] = i.toString();
      }
    }
  }

  public tabYear() {
    for (let i = 2018 ; i < 2100 ; i++) {
      this.yearList[i - 2017] = i.toString();
    }
  }

  public tabDay() {
    for (let i = 1 ; i < 32 ; i++) {
      if (i < 10) {
        this.dayList[i] = '0' + i.toString();
      } else {
        this.dayList[i] = i.toString();
      }
    }
  }

  public hasNameError() {
    const control = this.eventForm.get('name');
    return control.errors && control.errors.required;
  }
}
