import { Component, OnInit} from '@angular/core';
import { JavaService } from 'src/app/services/java.service';
import { Event } from '../../models/event.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  constructor(private javaService: JavaService) { }

  public listEvents: Event[];

  ngOnInit() {
    this.javaService.getListEvent().subscribe((e) => this.listEvents = e);
  }

}
