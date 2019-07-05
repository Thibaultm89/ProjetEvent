import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JavaService } from 'src/app/services/java.service';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.scss']
})
export class ActivitiesComponent implements OnInit {

  constructor(private javaService: JavaService, private route: ActivatedRoute) { }

  public findActivityImgById: string;

  ngOnInit() {
    this.route.params.subscribe(params => {

      const id: number = params.id;

      this.javaService.getFindActivityImgById(id).subscribe((ai) => this.findActivityImgById = ai);
    });
  }

}
