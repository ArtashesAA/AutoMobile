import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { Usuario } from '../entidad/usuario.model';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
})
export class FooterComponent implements OnInit {
  // Almacena si esta logueado o no
  estaLogueado: boolean = false;

  constructor(private servicioAutenticacion: AutenticacionService) {}

  // Si esta logueado, muestra el nombre del usuario en el header
  ngOnInit(): void {
    // Comprueba que esté logueado
    this.estaLogueado = this.servicioAutenticacion.estaAutenticado();
  }
}
