import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerCocheComponent } from './ver-coche.component';

describe('VerCocheComponent', () => {
  let component: VerCocheComponent;
  let fixture: ComponentFixture<VerCocheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerCocheComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VerCocheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
