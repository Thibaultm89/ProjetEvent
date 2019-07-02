import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Activity } from 'src/app/models/activity.model';
import { JavaService } from 'src/app/services/java.service';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.scss']
})
export class ActivitiesComponent implements OnInit {

  constructor(private javaService: JavaService) { }

  public listActivities: Observable<Activity[]>;

  ngOnInit() {
    this.listActivities = this.javaService.getListActivity();
  }

}
