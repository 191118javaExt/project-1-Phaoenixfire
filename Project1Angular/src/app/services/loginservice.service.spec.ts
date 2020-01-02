import { TestBed } from '@angular/core/testing';

import { LoginserviceService } from './login.service';

describe('LoginserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoginserviceService = TestBed.get(LoginserviceService);
    expect(service).toBeTruthy();
  });
});
