import { Injectable } from '@angular/core';
import { LoginComponent } from '../components/login/login.component';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanDeactivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class DeactivateGuardService implements CanDeactivate<LoginComponent> {

  constructor() { }

  canDeactivate(component: LoginComponent,
                currentRoute: ActivatedRouteSnapshot,
                currentState: RouterStateSnapshot,
                nextState?: RouterStateSnapshot) {

    return window.confirm('Do you want to leave?');

  }

}
