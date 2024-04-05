import { TestBed } from '@angular/core/testing';

import { ServicioNoticiaService } from './servicio-noticia.service';

describe('ServicioNoticiaService', () => {
  let service: ServicioNoticiaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioNoticiaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
