import { TestBed } from '@angular/core/testing';

import { ServicioFavoritoService } from './servicio-favorito.service';

describe('ServicioFavoritoService', () => {
  let service: ServicioFavoritoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioFavoritoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
