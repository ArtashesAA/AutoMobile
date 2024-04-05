import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Noticia } from '../entidad/noticia.model';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-noticia-hijo',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './noticia-hijo.component.html',
  styleUrl: './noticia-hijo.component.css',
})
export class NoticiaHijoComponent {
  @Input() noticiadelista!: Noticia;
  @Input() indice!: number;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {}

  estaLogueado() {
    return this.loginService.estaLogueado();
  }
}
