import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { People } from 'src/app/models/people.model';
import { JavaService } from 'src/app/services/java.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public logForm: FormGroup;
  public people: People;

  constructor(private fb: FormBuilder, private js: JavaService) {
    this.people = new People();
    this.logForm = this.fb.group({
      login : this.fb.control(this.people.email),
      pwd : this.fb.control(this.people.password)
    });

  }

  ngOnInit() {
  }

  submitForm() {
    this.js.getLogin(this.logForm.value.login, this.logForm.value.pwd).subscribe(e => {
      this.logForm.value.login = e;
    });
    console.log(this.logForm.value);

  }

}
