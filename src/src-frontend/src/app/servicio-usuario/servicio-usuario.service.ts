import { Injectable } from '@angular/core';
import { DataServices } from '../servicio-general/DataServices';
import { ServicioGeneralService } from '../servicio-general/servicio-general.service';
import { Usuario } from '../entidad/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class ServicioUsuarioService {
  usuarios: Usuario[] = [];

  constructor(
    private servicioGeneral: ServicioGeneralService,
    private dataService: DataServices
  ) {}

  getUsuarioPorId(id: number) {
    return this.dataService.cargarUsuarioPorId(id);
  }

  setUsuarios(misUsuarios: Usuario[]) {
    this.usuarios = misUsuarios;
  }

  getUsuarios() {
    return this.dataService.cargarUsuarios();
  }

  agregarUsuarioServicio(usuario: Usuario) {
    this.servicioGeneral.muestraMensaje(
      'Usuario que se va a agregar: ' + '\n' + usuario.nombre_usuario
    );
    this.usuarios.push(usuario);
    if (this.usuarios.length === 1) {
      this.dataService.guardarUsuarios(this.usuarios);
    } else {
      this.dataService.guardarUsuarios(this.usuarios);
    }
  }

  actualizarUsuario(indice: number, usuario: Usuario) {
    let usuarioModificado = this.usuarios[indice];

    usuarioModificado.email = usuario.email;
    usuarioModificado.nombre_usuario = usuario.nombre_usuario;
    usuarioModificado.imagen_usuario = usuario.imagen_usuario;
    usuarioModificado.role = usuario.role;

    this.dataService.actualizarUsuario(indice, usuario);
  }

  eliminarUsuario(indice: number) {
    this.servicioGeneral.muestraMensaje('Usuario eliminado');
    this.usuarios.splice(indice, 1);
    this.dataService.eliminarUsuario(indice);
  }
}
