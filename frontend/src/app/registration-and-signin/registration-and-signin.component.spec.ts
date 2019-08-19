import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationAndSigninComponent } from './registration-and-signin.component';

describe('RegistrationAndSigninComponent', () => {
  let component: RegistrationAndSigninComponent;
  let fixture: ComponentFixture<RegistrationAndSigninComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrationAndSigninComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationAndSigninComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
