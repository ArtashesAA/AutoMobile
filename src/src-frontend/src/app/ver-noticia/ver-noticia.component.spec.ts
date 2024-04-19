import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerNoticiaComponent } from './ver-noticia.component';

describe('VerNoticiaComponent', () => {
  let component: VerNoticiaComponent;
  let fixture: ComponentFixture<VerNoticiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerNoticiaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VerNoticiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
