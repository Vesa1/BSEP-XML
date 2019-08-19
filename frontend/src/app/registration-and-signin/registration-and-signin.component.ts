import { Component, OnInit } from '@angular/core';
import { UserRegistration } from '../classes/UserRegistration';
import { UserServiceService } from '../services/user-service/user-service.service';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-registration-and-signin',
  templateUrl: './registration-and-signin.component.html',
  styleUrls: ['./registration-and-signin.component.css']
})
export class RegistrationAndSigninComponent implements OnInit {

  user : UserRegistration = new UserRegistration();
  checkUser : UserRegistration = new UserRegistration();
  error : String;
  nameRequired: boolean;
  surnameRequired: boolean;
  emailRequired: boolean;
  telephoneRequired : boolean;
  adressRequired: boolean;
  cityRequired : boolean;
  passwordRequired: boolean;
  passwordRRequired : boolean;

  constructor(private userService : UserServiceService) { }

  ngOnInit() {
    this.nameRequired = false;
    this.surnameRequired = false;
    this.emailRequired = false;
    this.telephoneRequired =false;
    this.adressRequired = false;
    this.cityRequired = false;
    this.passwordRRequired = false;
    this.passwordRequired = false;
  }

  validate() {
    console.log("Validate registration");
    console.log("User: ");
    console.log(this.user);

    if(!this.user.name || this.user.name.length<2 || this.user.name.length>30) {
      this.nameRequired = true;
      this.error = "Field is required, min length - 2 charachters, max length - 30 characters";
    } else {
      this.nameRequired = false;
    }

    if(!this.user.surname || this.user.surname.length<2 || this.user.surname.length>30) {
      this.surnameRequired = true;
      this.error = "Field is required, min length - 2 charachters, max length - 30 characters";
    } else {
      this.surnameRequired = false;
    }

    if(!this.user.city || this.user.city.length<2 || this.user.city.length>30) {
      this.cityRequired = true;
      this.error = "Field is required, min length - 2 charachters, max length - 30 characters";
    } else {
      this.cityRequired = false;
    }

    if(!this.user.adress || this.user.adress.length<2 || this.user.adress.length>30) {
      this.adressRequired = true;
      this.error = "Field is required, min length - 2 charachters, max length - 30 characters";
    } else {
      this.adressRequired = false;
    }

    if(!this.user.password || this.user.password.length<8 || this.user.password.length>30) {
      this.passwordRequired = true;
    } else {
      this.passwordRequired = false;
    }

    console.log(this.user.password);
    console.log(this.user.passwordR);
    if(!this.user.passwordR || this.user.passwordR.length<8 || this.user.passwordR.length>30 || !(this.user.password===this.user.passwordR)) {
      this.passwordRRequired = true;
    } else { 
      this.passwordRRequired = false;
    }

    this.emailRequired = this.checkEmail(this.user.email);
    this.telephoneRequired = this.checkTelephone(this.user.telephone);

  if(!this.nameRequired && !this.surnameRequired && !this.emailRequired && !this.cityRequired && !this.adressRequired && !this.telephoneRequired && !this.passwordRRequired && !this.passwordRequired) {
    this.userService.registerUser(this.user).subscribe(korisnik => {
      this.checkUser = korisnik as UserRegistration;
      if(korisnik) {
        window.location.href = 'http://localhost:4200';
      }
     }
    )
  }

  }

  checkTelephone(telephone) {
    const verify = /^0\d{2}-\d{3}-\d{4}$/;
    if(verify.test(telephone)) {
      return false;
    }
    return true;
  }

  checkEmail(email) {
    const verify = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(verify.test(email)) {
      return false;
    }
    return true;
  }
}
