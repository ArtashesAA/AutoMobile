import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarCochePropioComponent } from './actualizar-coche-propio.component';

describe('ActualizarCochePropioComponent', () => {
  let component: ActualizarCochePropioComponent;
  let fixture: ComponentFixture<ActualizarCochePropioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActualizarCochePropioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ActualizarCochePropioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
