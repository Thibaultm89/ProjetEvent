import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Event } from 'src/app/models/event.model';
@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit {

  public event: Event;

  public eventForm: FormGroup;

  constructor(private fb: FormBuilder) {

    this.event = new Event();

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

}
