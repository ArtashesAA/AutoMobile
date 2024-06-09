import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicarNoticiaCorrectoComponent } from './publicar-noticia-correcto.component';

describe('PublicarNoticiaCorrectoComponent', () => {
  let component: PublicarNoticiaCorrectoComponent;
  let fixture: ComponentFixture<PublicarNoticiaCorrectoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicarNoticiaCorrectoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublicarNoticiaCorrectoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
