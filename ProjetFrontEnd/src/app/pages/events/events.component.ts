import { Component, OnInit } from '@angular/core';
import { JavaService } from 'src/app/services/java.service';
import { Observable } from 'rxjs';
import { Activity } from 'src/app/models/activity.model';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  constructor(private javaService: JavaService) { }

  public listActivitiesByEvent: Observable<Activity[]>;

  ngOnInit() {
    this.listActivitiesByEvent = this.javaService.getListActivityByEvent(3);
  }

}
