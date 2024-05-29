import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearCocheCorrectoComponent } from './crear-coche-correcto.component';

describe('CrearCocheCorrectoComponent', () => {
  let component: CrearCocheCorrectoComponent;
  let fixture: ComponentFixture<CrearCocheCorrectoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearCocheCorrectoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CrearCocheCorrectoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
