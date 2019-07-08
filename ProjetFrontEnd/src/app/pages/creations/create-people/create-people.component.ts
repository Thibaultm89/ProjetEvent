import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { People } from 'src/app/models/people.model';
import { JavaService } from 'src/app/services/java.service';
import { Activity } from 'src/app/models/activity.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-people',
  templateUrl: './create-people.component.html',
  styleUrls: ['./create-people.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CreatePeopleComponent implements OnInit {

  public people: People;
  public peopleForm: FormGroup;

  public listActivitiesByEvent: Activity[];

  constructor(private fb: FormBuilder, private javaService: JavaService, private route: ActivatedRoute) {

    this.route.params.subscribe(params => {

      const id: number = params.id;
      this.javaService.getEventById(id).subscribe((e) => {
        this.listActivitiesByEvent = e.listActivities;
      });
    });

    this.people = new People();

    this.peopleForm = this.fb.group({

      idActivity: this.fb.control(this.people.idActivity),

      firstName: this.fb.control(this.people.firstName, [Validators.required]),
      lastName: this.fb.control(this.people.lastName, [Validators.required]),
      email: this.fb.control(this.people.email, [Validators.required]),
      password: this.fb.control(this.people.password, [Validators.required]),

    });
  }

  ngOnInit() {
  }

  public submitForm() {

    const newValues = this.peopleForm.value;

    const newPeople = new People();

    newPeople.firstName = newValues.firstName;
    newPeople.lastName = newValues.lastName;
    newPeople.email = newValues.email;
    newPeople.password = newValues.password;
    newPeople.idActivity = newValues.idActivity;

    this.people = newPeople;

    this.javaService.createPeople(this.people).subscribe(p => {
      this.people = p;
    });

    console.log('submit', this.people, newValues);
  }

  public hasFirstnameError() {
    const control = this.peopleForm.get('firstName');
    return control.errors && control.errors.required;
  }

  public hasLastnameError() {
    const control = this.peopleForm.get('lastName');
    return control.errors && control.errors.required;
  }

  public hasEmailError() {
    const control = this.peopleForm.get('email');
    return control.errors && control.errors.required;
  }

  public hasPasswordError() {
    const control = this.peopleForm.get('password');
    return control.errors && control.errors.required;
  }

}
