import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JavaService } from 'src/app/services/java.service';
import { People } from 'src/app/models/people.model';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.scss']
})
export class ActivitiesComponent implements OnInit {

  public isConnected: boolean;

  public isManager = false;

  constructor(
    private javaService: JavaService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthenticationService) { }

  public listPeople: People[];

  public activityImg: string;

  public activityName: string;

  public activityId: number;

  public p = new People();

  ngOnInit() {
    this.route.params.subscribe(params => {

      const id: number = params.id;

      this.javaService.getActivityById(id).subscribe((act) => {
        this.activityImg = act.img;
        this.activityName = act.name;
        this.listPeople = act.listPeople;
        this.activityId = act.id;

        this.p.id = Number(this.authService.getLoggedInUser());
        this.p.idActivity = this.activityId;
      });
    });

    this.isConnected = this.authService.isConnected();
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

  public deleteActivity(id: number) {
    this.javaService.deleteActivity(id).subscribe();
  }

  public deletePeople(id: number) {
    this.javaService.deletePeople(id).subscribe();
  }

  public registerPeopleToActivity() {
    this.javaService.registerPeople(this.p).subscribe();
    console.log(this.p);
  }
}
