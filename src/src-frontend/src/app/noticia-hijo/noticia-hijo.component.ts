import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Noticia } from '../entidad/noticia.model';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-noticia-hijo',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './noticia-hijo.component.html',
  styleUrl: './noticia-hijo.component.css',
})
export class NoticiaHijoComponent {
  @Input() noticiadelista!: Noticia;
  @Input() indiceNoticia!: number;

  constructor(private servicioAutenticacion: AutenticacionService) {}

  ngOnInit(): void {}

  estaLogueado() {
    return this.servicioAutenticacion.estaAutenticado();
  }
}
