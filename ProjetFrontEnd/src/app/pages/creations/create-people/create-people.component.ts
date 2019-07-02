import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-create-people',
  templateUrl: './create-people.component.html',
  styleUrls: ['./create-people.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CreatePeopleComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
