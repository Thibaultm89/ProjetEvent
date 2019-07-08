import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Activity } from 'src/app/models/activity.model';
import { ActivatedRoute } from '@angular/router';
import { People } from 'src/app/models/people.model';
import { MyLogin } from 'src/app/models/login.model';
import { JavaService } from 'src/app/services/java.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public login: MyLogin;
  public logForm: FormGroup;
  public person: People;

  public people: People;
  public peopleForm: FormGroup;

  constructor(private fb: FormBuilder, private javaService: JavaService, private route: ActivatedRoute) {

    this.person = new People();

    this.logForm = this.fb.group({
      login : this.fb.control(this.person.email),
      pwd : this.fb.control(this.person.password)
    });




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

  public submitForm1() {

    this.javaService.getLogin(this.login).subscribe(e => {
      this.logForm.value.login = e;
    });
    console.log(this.logForm.value);

  }



  public submitForm2() {

    const newValues = this.peopleForm.value;

    const newPeople = new People();

    newPeople.firstName = newValues.firstName;
    newPeople.lastName = newValues.lastName;
    newPeople.email = newValues.email;
    newPeople.password = newValues.password;

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
