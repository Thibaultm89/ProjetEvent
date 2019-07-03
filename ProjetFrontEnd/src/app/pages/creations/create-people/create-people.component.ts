import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { People } from 'src/app/models/people.model';

@Component({
  selector: 'app-create-people',
  templateUrl: './create-people.component.html',
  styleUrls: ['./create-people.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CreatePeopleComponent implements OnInit {

  public people: People;

  public peopleForm: FormGroup;

  constructor(private fb: FormBuilder) {

    this.people = new People();

    this.peopleForm = this.fb.group({

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

    this.people = newPeople;

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
