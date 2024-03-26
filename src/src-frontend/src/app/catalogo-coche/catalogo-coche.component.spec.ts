import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogoCocheComponent } from './catalogo-coche.component';

describe('CatalogoCocheComponent', () => {
  let component: CatalogoCocheComponent;
  let fixture: ComponentFixture<CatalogoCocheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CatalogoCocheComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CatalogoCocheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
