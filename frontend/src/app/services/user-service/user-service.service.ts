import { Injectable } from '@angular/core';
import { ZuulPath } from 'src/app/classes/ZuulPath';
import { UserRegistration } from 'src/app/classes/UserRegistration';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http : HttpClient, private zuulPath:ZuulPath) { }
  
  registerUser(user : UserRegistration) {
    let config = {
      headers: {
        }
      }
    return this.http.post(this.zuulPath.path + 'auth-service/api/auth/registration', user, config);
  }
  loginUser(user : UserRegistration){
    let config = {
      headers: {
        }
      }
    return this.http.post(this.zuulPath.path + 'auth-service/api/auth/login', user, config);
  }

}