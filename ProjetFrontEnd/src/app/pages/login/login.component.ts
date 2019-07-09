import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { People } from 'src/app/models/people.model';
import { MyLogin } from 'src/app/models/login.model';
import { JavaService } from 'src/app/services/java.service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public login: MyLogin;
  public logForm: FormGroup;
  public person: People;
  public isConnected = false;

  public people: People;
  public peopleForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private javaService: JavaService,
    private router: Router,
    private authService: AuthenticationService
    ) {

    this.login = new MyLogin();

    this.logForm = this.fb.group({
      login : this.fb.control(this.login.email),
      pwd : this.fb.control(this.login.password)
    });

    this.login = new MyLogin();


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

  next() {
    this.router.navigate(['/home']);
  }


  public submitForm1() {

    const newValues = this.logForm.value;
    const newLogin = new MyLogin();

    newLogin.email = newValues.login;
    newLogin.password = newValues.pwd;
    this.login = newLogin;

    this.javaService.postLogin(this.login).subscribe(p =>  {
      this.connection(p);
      this.router.navigate(['/home']);
    });
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
      this.router.navigate(['/home']);
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


  public hasLoginError() {
    const control = this.logForm.get('login');
    return control.errors && control.errors.required;
  }

  public hasPwdError() {
    const control = this.logForm.get('pwd');
    return control.errors && control.errors.required;
  }

  public connection(pers: People) {
    if (pers === null || pers === undefined) {
     this.isConnected = false;
    } else {
      this.isConnected = true;
      this.authService.setLoggedInUser(pers.id);
      this.next();
    }
  }
}
