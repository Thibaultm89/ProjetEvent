import { Component, OnInit } from '@angular/core';
import { JavaService } from 'src/app/services/java.service';
import { Activity } from 'src/app/models/activity.model';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  public isConnected: boolean;

  public isManager = false;

  constructor(
    private javaService: JavaService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthenticationService) { }

  public listActivities: Activity[];

  public eventId: number;

  public eventImg: string;

  public eventName: string;

  ngOnInit() {

    this.route.params.subscribe(params => {

      const id: number = params.id;
      this.javaService.getEventById(id).subscribe((e) => {
        this.eventImg = e.img;
        this.eventName = e.name;
        this.listActivities = e.listActivities;
        this.eventId = e.id;
      });
    });

    this.isConnected = this.authService.isConnected();
    if (this.authService.getLoggedInUser() === '1' || this.authService.getLoggedInUser() === '2') {
      this.isManager = true;
    }


  }

  next() {
    this.router.navigate(['/home']);
  }

  public disconnect() {
    this.authService.removeLoggedInUser();
    this.next();
  }

  public deleteEvent(id: number) {
    this.javaService.deleteEvent(id).subscribe(d => this.next());
  }
}
