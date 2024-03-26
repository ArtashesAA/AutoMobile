import { TestBed } from '@angular/core/testing';

import { ServicioCocheService } from './servicio-coche.service';

describe('ServicioCocheService', () => {
  let service: ServicioCocheService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioCocheService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
