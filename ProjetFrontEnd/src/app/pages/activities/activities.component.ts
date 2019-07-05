import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JavaService } from 'src/app/services/java.service';
import { People } from 'src/app/models/people.model';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.scss']
})
export class ActivitiesComponent implements OnInit {

  constructor(private javaService: JavaService, private route: ActivatedRoute) { }

  public listPeople: People[];

  public activityImg: string;

  public activityName: string;

  ngOnInit() {
    this.route.params.subscribe(params => {

      const id: number = params.id;

      this.javaService.getActivityById(id).subscribe((act) => {
        this.activityImg = act.img;
        this.activityName = act.name;
        this.listPeople = act.listPeople;
      });
    });
  }

}
