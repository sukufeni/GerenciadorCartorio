import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // BASE_PATH: 'http://passgenarator_passgenarator_some_net:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  pass_SESSION_ATTRIBUTE_NAME = 'authenticatedPass'

  public username: string;
  public password: string;

  constructor(private http: HttpClient) {

  }

  authenticationService(username: string, password: string) {
    console.log(environment.apiBaseUrl + "/api/v1/basicauth");
    return this.http.get(environment.apiBaseUrl + "/api/v1/basicauth",
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {

        this.username = username;
        this.password = password;
        this.registerSuccessfulLogin(username, password);
      }));
  }

  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username: string, password: string) {
    localStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username)
    localStorage.setItem(this.pass_SESSION_ATTRIBUTE_NAME, password)
  }

  logout() {
    localStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    localStorage.removeItem(this.pass_SESSION_ATTRIBUTE_NAME);
    this.username = null;
    this.password = null;
  }

  isUserLoggedIn() {
    let user = localStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }

  getLoggedInUserName() {
    let user = localStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }
  getLoggedPassWord() {
    let pass = localStorage.getItem(this.pass_SESSION_ATTRIBUTE_NAME)
    if (pass === null) return ''
    return pass
  }
}