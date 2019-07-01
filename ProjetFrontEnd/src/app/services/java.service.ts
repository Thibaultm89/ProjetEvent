import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class JavaService {

  constructor(private httpClient: HttpClient) {}

  public getDataFromJava(): Observable<string[]> {
    return this.httpClient
    .get('http://localhost:8080/projet-back-end/json/activity/all')
    .pipe(
      map((value: any) => {
        return value.items.map((data) => data.name);
      }),
      shareReplay()
    );
  }
}
