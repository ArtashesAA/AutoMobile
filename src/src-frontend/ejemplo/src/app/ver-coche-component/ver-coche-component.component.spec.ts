import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerCocheComponentComponent } from './ver-coche-component.component';

describe('VerCocheComponentComponent', () => {
  let component: VerCocheComponentComponent;
  let fixture: ComponentFixture<VerCocheComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VerCocheComponentComponent]
    });
    fixture = TestBed.createComponent(VerCocheComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
