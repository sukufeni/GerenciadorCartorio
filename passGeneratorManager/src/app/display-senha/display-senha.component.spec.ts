import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplaySenhaComponent } from './display-senha.component';

describe('DisplaySenhaComponent', () => {
  let component: DisplaySenhaComponent;
  let fixture: ComponentFixture<DisplaySenhaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplaySenhaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplaySenhaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
