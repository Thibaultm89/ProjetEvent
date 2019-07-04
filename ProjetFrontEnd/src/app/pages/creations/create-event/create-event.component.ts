import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Event } from 'src/app/models/event.model';
import { JavaService } from 'src/app/services/java.service';

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

  constructor(private fb: FormBuilder, private javaService: JavaService) {

    this.tabMonth();
    this.tabYear();
    this.tabDay();

    this.event = new Event();
    this.event.start = new Date();
    this.event.finish = new Date();

    this.eventForm = this.fb.group({

      name: this.fb.control(this.event.name, [Validators.required]),

      yearstart: this.fb.control(this.event.start.getFullYear, [Validators.required]),
      monthstart: this.fb.control(this.event.start.getMonth, [Validators.required]),
      daystart: this.fb.control(this.event.start.getDay, [Validators.required]),

      yearfinish: this.fb.control(this.event.finish.getFullYear, [Validators.required]),
      monthfinish: this.fb.control(this.event.finish.getMonth, [Validators.required]),
      dayfinish: this.fb.control(this.event.finish.getDay, [Validators.required]),

    });
  }

  ngOnInit() {
  }

  public submitForm() {

    const newValues = this.eventForm.value;

    const newEvent = new Event();
    newEvent.start = new Date();
    newEvent.finish = new Date();

    newEvent.name = newValues.name;

    newEvent.start.setFullYear(newValues.yearstart);
    newEvent.start.setMonth((newValues.monthstart) - 1);
    newEvent.start.setDate(newValues.daystart);

    newEvent.start.setHours(0);
    newEvent.start.setMinutes(0);
    newEvent.start.setSeconds(0);
    newEvent.start.setMilliseconds(0);

    newEvent.finish.setFullYear(newValues.yearfinish);
    newEvent.finish.setMonth((newValues.monthfinish) - 1);
    newEvent.finish.setDate(newValues.dayfinish);

    newEvent.finish.setHours(0);
    newEvent.finish.setMinutes(0);
    newEvent.finish.setSeconds(0);
    newEvent.finish.setMilliseconds(0);

    this.event = newEvent;

    this.javaService.createEvent(this.event).subscribe(e => {
      this.event = e;
    });

    console.log('submit', this.event, newValues);
  }

  public hasNameError() {
    const control = this.eventForm.get('name');
    return control.errors && control.errors.required;
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
}
