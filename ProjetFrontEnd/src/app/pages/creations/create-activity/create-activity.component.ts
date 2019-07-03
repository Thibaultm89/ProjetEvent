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

  public activity: Activity;

  public activityForm: FormGroup;

  constructor(private fb: FormBuilder) {

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

  public hasYearstartError() {
    const control = this.activityForm.get('yearstart');
    return control.errors && control.errors.required;
  }

  public hasMonthstartError() {
    const control = this.activityForm.get('monthstart');
    return control.errors && control.errors.required;
  }

  public hasDaystartError() {
    const control = this.activityForm.get('daystart');
    return control.errors && control.errors.required;
  }

  public hasHourstartError() {
    const control = this.activityForm.get('hourstart');
    return control.errors && control.errors.required;
  }

  public hasMinutestartError() {
    const control = this.activityForm.get('minutestart');
    return control.errors && control.errors.required;
  }

  public hasHourfinishError() {
    const control = this.activityForm.get('hourfinish');
    return control.errors && control.errors.required;
  }

  public hasMinutefinishError() {
    const control = this.activityForm.get('minutefinish');
    return control.errors && control.errors.required;
  }

}
