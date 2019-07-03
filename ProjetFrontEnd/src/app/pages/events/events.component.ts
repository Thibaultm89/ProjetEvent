import { Component, OnInit } from '@angular/core';
import { JavaService } from 'src/app/services/java.service';
import { Observable } from 'rxjs';
import { Activity } from 'src/app/models/activity.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  public activity: Activity;
  public editedActivity: Activity;

  constructor(private javaService: JavaService, private route: ActivatedRoute) { }

  public listActivitiesByEvent: Observable<Activity[]>;

  ngOnInit() {

    this.route.params.subscribe(params => {

      const id: number = params.id;
      this.listActivitiesByEvent = this.javaService.getListActivityByEvent(id);
    });
  }

}
