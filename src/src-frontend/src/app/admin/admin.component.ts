import { Component, OnInit } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css',
})
export class AdminComponent implements OnInit {
  usuarios: Usuario[] = [];

  constructor(private servicioUsuario: ServicioUsuarioService) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    this.servicioUsuario.cargarUsuarios().subscribe(
      (usuarios: Usuario[]) => {
        this.usuarios = usuarios;
        console.log('Lista de usuarios:', this.usuarios);
      },
      (error) => {
        console.error('Error al cargar usuarios:', error);
      }
    );
  }
}
