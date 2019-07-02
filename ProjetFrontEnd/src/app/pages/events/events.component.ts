import { Component, OnInit } from '@angular/core';
import { JavaService } from 'src/app/services/java.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  constructor(private javaService: JavaService) { }

  public listEvents: Observable<Event[]>;

  ngOnInit() {
    this.listEvents = this.javaService.getListEvent();
  }

}
