import { TestBed } from '@angular/core/testing';

import { HttpInterceptorRequestService } from './http-interceptor-request.service';

describe('HttpInterceptorRequestService', () => {
  let service: HttpInterceptorRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpInterceptorRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
