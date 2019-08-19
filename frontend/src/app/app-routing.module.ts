import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { IndexComponent } from './index/index.component';
import { RegistrationAndSigninComponent } from './registration-and-signin/registration-and-signin.component';

const routes: Routes = [
  {
    path: 'registrationAndSignin', component:RegistrationAndSigninComponent
  },
  {
    path : '', component:IndexComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }