import { Component, OnInit } from '@angular/core';
import { JavaService } from 'src/app/services/java.service';
import { Activity } from 'src/app/models/activity.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  constructor(private javaService: JavaService, private route: ActivatedRoute) { }

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
  }

}
