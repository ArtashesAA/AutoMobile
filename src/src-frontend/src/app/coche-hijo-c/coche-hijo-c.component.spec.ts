import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CocheHijoCComponent } from './coche-hijo-c.component';

describe('CocheHijoCComponent', () => {
  let component: CocheHijoCComponent;
  let fixture: ComponentFixture<CocheHijoCComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CocheHijoCComponent]
    });
    fixture = TestBed.createComponent(CocheHijoCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
