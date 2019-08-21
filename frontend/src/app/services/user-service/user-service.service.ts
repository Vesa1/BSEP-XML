import { Injectable } from '@angular/core';
import { ZuulPath } from 'src/app/classes/ZuulPath';
import { UserRegistration } from 'src/app/classes/UserRegistration';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { Profile } from 'src/app/classes/profile';
import { LoginParams } from 'src/app/classes/LoginParams';
import { Observable } from 'rxjs';

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
  loginUser(user : LoginParams):  Observable<Profile>{
    console.log("DOSAO SAM U LOGIN USER");
    let config = {
      headers: {
        }
      }
    return this.http.post<Profile>(this.zuulPath.path + 'auth-service/api/auth/login', user, config);
  }

}