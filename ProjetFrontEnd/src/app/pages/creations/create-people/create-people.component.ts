import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Observable } from 'rxjs';
import { JavaService } from 'src/app/services/java.service';
import { Activity } from 'src/app/models/activity.model';

@Component({
  selector: 'app-create-people',
  templateUrl: './create-people.component.html',
  styleUrls: ['./create-people.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CreatePeopleComponent implements OnInit {

  constructor(private javaService: JavaService) { }

  public data: Observable<Activity[]>;

  ngOnInit() {
    this.data = this.javaService.getListActivity();
  }

  public onSendChange(send: string) {
    console.log(send);
  }
}
