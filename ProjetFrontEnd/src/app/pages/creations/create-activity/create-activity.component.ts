import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Activity } from 'src/app/models/activity.model';
import { DateWithTime } from 'src/app/models/date-with-time.model';

@Component({
  selector: 'app-create-activity',
  templateUrl: './create-activity.component.html',
  styleUrls: ['./create-activity.component.scss']
})
export class CreateActivityComponent implements OnInit {

  public yearList: string[] = [null];
  public monthList: string[] = [null];
  public dayList: string[] = [null];
  public hourList: string[] = [null];
  public minuteList: string[] = [null];

  public activity: Activity;
  public activityForm: FormGroup;

  constructor(private fb: FormBuilder) {

    this.tabMonth();
    this.tabYear();
    this.tabDay();
    this.tabHour();
    this.tabMinute();

    this.activity = new Activity();
    this.activity.start = new DateWithTime();
    this.activity.finish = new DateWithTime();

    this.activityForm = this.fb.group({

      name: this.fb.control(this.activity.name, [Validators.required]),

      yearstart: this.fb.control(this.activity.start.year, [Validators.required]),
      monthstart: this.fb.control(this.activity.start.monthValue, [Validators.required]),
      daystart: this.fb.control(this.activity.start.dayOfMonth, [Validators.required]),

      hourstart: this.fb.control(this.activity.start.dayOfMonth, [Validators.required]),
      minutestart: this.fb.control(this.activity.start.dayOfMonth, [Validators.required]),

      hourfinish: this.fb.control(this.activity.finish.dayOfMonth, [Validators.required]),
      minutefinish: this.fb.control(this.activity.finish.dayOfMonth, [Validators.required]),

    });
  }

  ngOnInit() {
  }

  public submitForm() {

    const newValues = this.activityForm.value;

    const newActivity = new Activity();
    newActivity.start = new DateWithTime();
    newActivity.finish = new DateWithTime();

    newActivity.name = newValues.name;

    newActivity.start.year = newValues.yearstart;
    newActivity.start.monthValue = newValues.monthstart;
    newActivity.start.dayOfMonth = newValues.daystart;
    newActivity.start.hour = newValues.hourstart;
    newActivity.start.minute = newValues.minutestart;

    newActivity.finish.year = newValues.yearstart;
    newActivity.finish.monthValue = newValues.monthstart;
    newActivity.finish.dayOfMonth = newValues.daystart;
    newActivity.finish.hour = newValues.hourfinish;
    newActivity.finish.minute = newValues.minutefinish;

    this.activity = newActivity;

    console.log('submit', this.activity, newValues);
  }

  public hasNameError() {
    const control = this.activityForm.get('name');
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

  public tabHour() {
    for (let i = 0 ; i < 24 ; i++) {
      if (i < 10) {
        this.hourList[i + 1] = '0' + i.toString();
      } else {
        this.hourList[i + 1] = i.toString();
      }
    }
  }

  public tabMinute() {
    for (let i = 0 ; i < 60 ; i++) {
      if (i < 10) {
        this.minuteList[i + 1] = '0' + i.toString();
      } else {
        this.minuteList[i + 1] = i.toString();
      }
    }
  }

}
