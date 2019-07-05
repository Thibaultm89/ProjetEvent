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

  public eventImg: string;

  ngOnInit() {

    this.route.params.subscribe(params => {

      const id: number = params.id;
      console.log(id);
      this.javaService.getEventById(id).subscribe((ei) => this.eventImg = ei.img);

      this.javaService.getEventById(id).subscribe((el) => this.listActivities = el.listActi);
    });
  }

}
