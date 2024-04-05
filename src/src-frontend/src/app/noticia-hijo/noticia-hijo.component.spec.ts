import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoticiaHijoComponent } from './noticia-hijo.component';

describe('NoticiaHijoComponent', () => {
  let component: NoticiaHijoComponent;
  let fixture: ComponentFixture<NoticiaHijoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NoticiaHijoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NoticiaHijoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
