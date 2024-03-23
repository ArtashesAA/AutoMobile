import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearCocheComponentComponent } from './crear-coche-component.component';

describe('CrearCocheComponentComponent', () => {
  let component: CrearCocheComponentComponent;
  let fixture: ComponentFixture<CrearCocheComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrearCocheComponentComponent]
    });
    fixture = TestBed.createComponent(CrearCocheComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
