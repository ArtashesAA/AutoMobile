import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoritosUsuarioComponent } from './favoritos-usuario.component';

describe('FavoritosUsuarioComponent', () => {
  let component: FavoritosUsuarioComponent;
  let fixture: ComponentFixture<FavoritosUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FavoritosUsuarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FavoritosUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
