import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  public setLoggedInUser(id: number) {
    localStorage.setItem('id', id.toString());
  }

  public removeLoggedInUser() {
    localStorage.removeItem('id');
  }

  public getLoggedInUser() {
    return localStorage.getItem('id');
  }

  public isConnected() {
    let logged: boolean;
    this.getLoggedInUser() !== null ? logged = true : logged = false;
    return logged;
  }
}
