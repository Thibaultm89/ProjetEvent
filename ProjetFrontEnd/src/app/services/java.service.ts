import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Activity } from '../models/activity.model';
import { Event } from '../models/event.model';
import { People } from '../models/people.model';
import { MyLogin } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class JavaService {

  constructor(private http: HttpClient) {}

  public getListEvent(): Observable<Event[]> {
    return this.http
      .get<Event[]>(`http://localhost:8080/projet-back-end/json/event/all`)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public getEventById(n: number): Observable<Event> {
    return this.http
      .get<Event>(`http://localhost:8080/projet-back-end/json/event/${n}`)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public getListActivity(): Observable<Activity[]> {
    return this.http
      .get<Activity[]>(`http://localhost:8080/projet-back-end/json/activity/all`)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public getActivityById(n: number): Observable<Activity> {
    return this.http
      .get<Activity>(`http://localhost:8080/projet-back-end/json/activity/${n}`)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }





  public postLogin(login: MyLogin): Observable<People> {
    return this.http
    .post<People>(`http://localhost:8080/projet-back-end/json/login/`, login, {withCredentials: true} );
  }





  public createEvent(payload: Event): Observable<Event> {
    return this.http
      .post<Event>(`http://localhost:8080/projet-back-end/json/create-event/`, payload)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public createActivity(payload: Activity): Observable<Activity> {
    return this.http
      .post<Activity>(`http://localhost:8080/projet-back-end/json/create-activity/`, payload)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public createPeople(payload: People): Observable<People> {
    return this.http
      .post<People>(`http://localhost:8080/projet-back-end/json/create-people/`, payload)
// tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

}
