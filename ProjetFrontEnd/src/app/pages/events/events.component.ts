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

  public listActivities: Observable<Activity[]>;

  ngOnInit() {
    this.listActivities = this.javaService.getListActivity();
  }

}
