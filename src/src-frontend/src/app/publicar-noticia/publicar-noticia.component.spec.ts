import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicarNoticiaComponent } from './publicar-noticia.component';

describe('PublicarNoticiaComponent', () => {
  let component: PublicarNoticiaComponent;
  let fixture: ComponentFixture<PublicarNoticiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicarNoticiaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublicarNoticiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
