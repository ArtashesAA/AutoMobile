import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.css',
})
export class UsuarioComponent {
  @Input() usuario: Usuario | undefined;
  @Output() eliminarUsuario = new EventEmitter<number>();
  @Output() editarUsuario = new EventEmitter<Usuario>();

  constructor() {}

  eliminar(id: number): void {
    this.eliminarUsuario.emit(id);
  }

  editar(usuario: Usuario): void {
    this.editarUsuario.emit(usuario);
  }
}
