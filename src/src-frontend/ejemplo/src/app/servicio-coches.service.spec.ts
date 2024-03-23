import { TestBed } from '@angular/core/testing';
import { ServicioCochesService } from './servicio-coches.service';

describe('ServicioCochesService', () => {
  let service: ServicioCochesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioCochesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
