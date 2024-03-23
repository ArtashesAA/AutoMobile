import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogoComponentComponent } from './catalogo-component.component';

describe('CatalogoComponentComponent', () => {
  let component: CatalogoComponentComponent;
  let fixture: ComponentFixture<CatalogoComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CatalogoComponentComponent]
    });
    fixture = TestBed.createComponent(CatalogoComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
