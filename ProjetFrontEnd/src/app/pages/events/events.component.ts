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

  public listActivitiesByEvent: Activity[];

  public findEventImgById: string;

  ngOnInit() {

    this.route.params.subscribe(params => {

      const id: number = params.id;
      console.log(id);
      this.javaService.getFindEventImgById(id).subscribe((ei) => {this.findEventImgById = ei; console.log(ei);
      });

      this.javaService.getListActivityByEvent(id).subscribe((el) => this.listActivitiesByEvent = el);
    });
    console.log(this.findEventImgById);
  }

}
