import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarCocheComponent } from './actualizar-coche.component';

describe('ActualizarCocheComponent', () => {
  let component: ActualizarCocheComponent;
  let fixture: ComponentFixture<ActualizarCocheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActualizarCocheComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ActualizarCocheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
