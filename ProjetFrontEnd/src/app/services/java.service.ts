import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Activity } from '../models/activity.model';

@Injectable({
  providedIn: 'root'
})
export class JavaService {

  constructor(private http: HttpClient) {}

  public getListActivity(): Observable<Activity[]> {
    return this.http
      .get<Activity[]>(`http://localhost:8080/projet-back-end/json/activity/all`)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public getListEvent(): Observable<Event[]> {
    return this.http
      .get<Event[]>(`http://localhost:8080/projet-back-end/json/event/all`)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

}
