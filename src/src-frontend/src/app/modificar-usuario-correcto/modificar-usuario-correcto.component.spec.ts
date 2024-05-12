import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarUsuarioCorrectoComponent } from './modificar-usuario-correcto.component';

describe('ModificarUsuarioCorrectoComponent', () => {
  let component: ModificarUsuarioCorrectoComponent;
  let fixture: ComponentFixture<ModificarUsuarioCorrectoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificarUsuarioCorrectoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModificarUsuarioCorrectoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
