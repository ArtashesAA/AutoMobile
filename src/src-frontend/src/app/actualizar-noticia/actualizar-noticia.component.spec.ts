import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarNoticiaComponent } from './actualizar-noticia.component';

describe('ActualizarNoticiaComponent', () => {
  let component: ActualizarNoticiaComponent;
  let fixture: ComponentFixture<ActualizarNoticiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActualizarNoticiaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ActualizarNoticiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
