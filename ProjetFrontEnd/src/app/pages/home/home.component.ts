import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Observable } from 'rxjs';
import { JavaService } from 'src/app/services/java.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HomeComponent implements OnInit {

  constructor(private javaService: JavaService) { }

  public listEvents: Observable<Event[]>;

  ngOnInit() {
    this.listEvents = this.javaService.getListEvent();
  }

}
