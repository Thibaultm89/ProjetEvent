import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Activity } from 'src/app/models/activity.model';
import { Event } from 'src/app/models/event.model';
import { JavaService } from 'src/app/services/java.service';
import { cleanSession } from 'selenium-webdriver/safari';

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

  public eventForActivity: Event[] = [null];

  public activity: Activity;
  public activityForm: FormGroup;

  constructor(private fb: FormBuilder, private javaService: JavaService) {

    this.tabMonth();
    this.tabYear();
    this.tabDay();
    this.tabHour();
    this.tabMinute();

    this.tabEvent();

    this.activity = new Activity();
    this.activity.start = new Date();
    this.activity.finish = new Date();


    this.activityForm = this.fb.group({

      name: this.fb.control(this.activity.name, [Validators.required]),

      idEvent: this.fb.control(this.activity.idEvent),

      yearstart: this.fb.control(this.activity.start.getFullYear, [Validators.required]),
      monthstart: this.fb.control(this.activity.start.getMonth, [Validators.required]),
      daystart: this.fb.control(this.activity.start.getDay, [Validators.required]),

      hourstart: this.fb.control(this.activity.start.getHours, [Validators.required]),
      minutestart: this.fb.control(this.activity.start.getMinutes, [Validators.required]),

      hourfinish: this.fb.control(this.activity.finish.getHours, [Validators.required]),
      minutefinish: this.fb.control(this.activity.finish.getMinutes, [Validators.required]),

    });
  }

  ngOnInit() {
  }

  public submitForm() {

    const newValues = this.activityForm.value;

    const newActivity = new Activity();
    newActivity.start = new Date();
    newActivity.finish = new Date();


    newActivity.name = newValues.name;
    newActivity.idEvent = newValues.idEvent;

    newActivity.start.setFullYear(newValues.yearstart);
    newActivity.start.setMonth((newValues.monthstart) - 1);
    newActivity.start.setDate(newValues.daystart);

    newActivity.start.setHours(newValues.hourstart);
    newActivity.start.setMinutes(newValues.minutestart);
    newActivity.start.setSeconds(0);
    newActivity.start.setMilliseconds(0);

    newActivity.finish.setFullYear(newValues.yearstart);
    newActivity.finish.setMonth((newValues.monthstart) - 1);
    newActivity.finish.setDate(newValues.daystart);

    newActivity.finish.setHours(newValues.hourfinish);
    newActivity.finish.setMinutes(newValues.minutefinish);
    newActivity.finish.setSeconds(0);
    newActivity.finish.setMilliseconds(0);

    this.activity = newActivity;

    this.javaService.createActivity(this.activity).subscribe(e => {
      this.activity = e;
    });
    console.log('subimti de newValues' , newValues);
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

  public tabEvent() {
    this.javaService.getListEvent().subscribe(e => {
      this.eventForActivity = e;
    });
  }

}
