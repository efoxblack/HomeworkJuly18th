import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MatInputModule,
        MatButtonModule,
        MatCardModule,
        ReactiveFormsModule,
        BrowserAnimationsModule
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should render title in a mat-card-title tag', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    const component = fixture.componentInstance;
    expect(compiled.querySelector('mat-card-title').textContent).toContain(component.title);
  });

  it('should render username input', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelectorAll('input')[0]).toBeDefined();
    expect(compiled.querySelectorAll('input')[0].name).toEqual('username');
    expect(compiled.querySelectorAll('input')[0].type).toEqual('text');
  });

  it('should render password input', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelectorAll('input')[1]).toBeDefined();
    expect(compiled.querySelectorAll('input')[1].name).toEqual('password');
    expect(compiled.querySelectorAll('input')[1].type).toEqual('password');
  });

  it('should render email input', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelectorAll('input')[2]).toBeDefined();
    expect(compiled.querySelectorAll('input')[2].name).toEqual('email');
    expect(compiled.querySelectorAll('input')[2].type).toEqual('email');
  });

  it('should be invalid when form empty', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const component = fixture.componentInstance;

    expect(component.signUpForm.valid).toBeFalsy();
  });

  it('should correctly validate the email', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const component = fixture.componentInstance;

    const email = component.signUpForm.controls['email'];
    email.setValue("test.com")
    expect(email.status).toBe('INVALID');
    email.setValue('test@example.com');
    expect(email.status).toBe('VALID');
  });

  it('should be invalid when some fields are wrong', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const component = fixture.componentInstance;

    expect(component.signUpForm.valid).toBeFalsy();

    component.signUpForm.controls['username'].setValue("testUser");
    component.signUpForm.controls['password'].setValue("12");
    component.signUpForm.controls['email'].setValue("test@example.com");
    expect(component.signUpForm.valid).toBeFalsy();
  })

});
