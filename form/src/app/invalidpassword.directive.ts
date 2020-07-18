import { Directive } from '@angular/core';
import { FormControl } from '@angular/forms';

@Directive({
  selector: '[appInvalidpassword]'
})
export class InvalidpasswordDirective {

  constructor() { }

  validate(c: FormControl) {
    if (c.value === '1234') {
      return { error: 'invalid password' }
    } else {
      return null;
    }
  }

}
