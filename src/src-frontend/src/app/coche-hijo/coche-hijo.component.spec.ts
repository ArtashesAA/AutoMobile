import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CocheHijoComponent } from './coche-hijo.component';

describe('CocheHijoComponent', () => {
  let component: CocheHijoComponent;
  let fixture: ComponentFixture<CocheHijoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CocheHijoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CocheHijoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
