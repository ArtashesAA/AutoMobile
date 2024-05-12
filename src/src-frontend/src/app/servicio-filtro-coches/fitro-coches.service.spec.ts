import { TestBed } from '@angular/core/testing';

import { FitroCochesService } from './fitro-coches.service';

describe('FitroCochesService', () => {
  let service: FitroCochesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FitroCochesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
