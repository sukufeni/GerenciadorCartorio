import { TestBed } from '@angular/core/testing';

import { DisplaySenhaService } from './display-senha.service';

describe('DisplaySenhaService', () => {
  let service: DisplaySenhaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisplaySenhaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
