import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecuperarCocheComponent } from './recuperar-coche.component';

describe('RecuperarCocheComponent', () => {
  let component: RecuperarCocheComponent;
  let fixture: ComponentFixture<RecuperarCocheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecuperarCocheComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RecuperarCocheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
