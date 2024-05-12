import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroCorrectoComponent } from './registro-correcto.component';

describe('RegistroCorrectoComponent', () => {
  let component: RegistroCorrectoComponent;
  let fixture: ComponentFixture<RegistroCorrectoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistroCorrectoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegistroCorrectoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
