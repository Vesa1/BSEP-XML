import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http'; 
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { IndexComponent } from './index/index.component';
import { RegistrationAndSigninComponent } from './registration-and-signin/registration-and-signin.component';
import {FormsModule} from '@angular/forms';
import {ZuulPath} from './classes/ZuulPath';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    IndexComponent,
    RegistrationAndSigninComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ZuulPath],
  bootstrap: [AppComponent]
})
export class AppModule { }