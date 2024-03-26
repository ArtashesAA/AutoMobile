import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearCocheComponent } from './crear-coche.component';

describe('CrearCocheComponent', () => {
  let component: CrearCocheComponent;
  let fixture: ComponentFixture<CrearCocheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearCocheComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CrearCocheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
