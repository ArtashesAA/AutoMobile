import { TestBed } from '@angular/core/testing';

import { ServicioGeneralService } from './servicio-general.service';

describe('ServicioGeneralService', () => {
  let service: ServicioGeneralService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioGeneralService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
