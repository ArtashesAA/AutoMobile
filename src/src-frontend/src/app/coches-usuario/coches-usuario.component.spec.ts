import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CochesUsuarioComponent } from './coches-usuario.component';

describe('CochesUsuarioComponent', () => {
  let component: CochesUsuarioComponent;
  let fixture: ComponentFixture<CochesUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CochesUsuarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CochesUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
