import { Component, Input } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { LoginService } from '../login/login.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-coche-hijo',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './coche-hijo.component.html',
  styleUrl: './coche-hijo.component.css',
})
export class CocheHijoComponent {
  @Input() cochedelista!: Coche;
  @Input() indice!: number;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {}

  estaLogueado() {
    return this.loginService.estaLogueado();
  }
}
