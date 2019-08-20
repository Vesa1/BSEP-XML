import { Injectable } from '@angular/core';
import { ZuulPath } from 'src/app/classes/ZuulPath';
import { UserRegistration } from 'src/app/classes/UserRegistration';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : '*' })
};


@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http : HttpClient, private zuulPath:ZuulPath) { }
  
  registerUser(user : UserRegistration) :Observable<any>{
    return this.http.post<string>(this.zuulPath.path + 'auth-service/api/auth/registration', user, httpOptions);
  }
  loginUser(user : UserRegistration){
    return this.http.post(this.zuulPath.path + 'auth-service/api/auth/login', user);
  }

}