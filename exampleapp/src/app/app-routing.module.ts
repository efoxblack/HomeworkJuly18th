import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthenticateGuardService } from './services/authenticate-guard.service';
import { DeactivateGuardService } from './services/deactivate-guard.service';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'home', 
    children: [
      {
        path: '',
        component: HomeComponent
      },
      {
        path: ':id',
        canActivate: [AuthenticateGuardService],
        component: HomeComponent
      }
    ]
  },
  {
    path: 'login',
    canActivate: [DeactivateGuardService],
    component: LoginComponent
  },
  { 
    path: 'signup', 
    component: SignupComponent 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
