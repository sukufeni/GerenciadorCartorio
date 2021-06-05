import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartorioComponent } from './cartorio.component';

describe('CartorioComponent', () => {
  let component: CartorioComponent;
  let fixture: ComponentFixture<CartorioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CartorioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartorioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
