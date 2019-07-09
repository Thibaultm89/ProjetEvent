import { Component, OnInit} from '@angular/core';
import { JavaService } from 'src/app/services/java.service';
import { Event } from '../../models/event.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  public isConnected: boolean;

  public isManager = false;

  constructor(private javaService: JavaService, private router: Router, private authService: AuthenticationService) {
   }

  public listEvents: Event[];

  ngOnInit() {
    this.javaService.getListEvent().subscribe((e) => this.listEvents = e);

    this.isConnected = this.authService.isConnected();
    if (this.authService.getLoggedInUser() === '1' || this.authService.getLoggedInUser() === '2') {
      this.isManager = true;
    }
  }

  next() {
    this.router.navigate(['/home']);
  }

  public disconnect(){
    this.authService.removeLoggedInUser();
    this.next();
  }

}
