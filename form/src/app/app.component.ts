import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

function emailValidator(email: FormControl) {
  const regexp = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

  if (regexp.test(email.value)) {
    console.log('valid');
    return null;
  } else {
    console.log('not valid');
    return { error: 'not valid email' };
  }

}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Registration Form!';

  signUpForm: FormGroup;

  username = new FormControl('', Validators.required);

  password = new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(5)]);

  email = new FormControl('', emailValidator);

  constructor(private fb: FormBuilder) {
    this.signUpForm = this.fb.group({
      username: this.username,
      password: this.password,
      email: this.email
    })

    const usernameControl = this.signUpForm.get('username');
    usernameControl.valueChanges.forEach(
      (value: string) => console.log('change', value)
    );
  }

  public onSubmit() {
    console.log(this.signUpForm.value);
  }

}
