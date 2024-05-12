import { Coche } from './coche.model';
import { Usuario } from './usuario.model';

export class Favorito {
  id: number;
  fecha: Date;
  coche: Coche;
  usuario: Usuario;

  constructor(id: number, fecha: Date, coche: Coche, usuario: Usuario) {
    this.id = id;
    this.fecha = fecha;
    this.coche = coche;
    this.usuario = usuario;
  }
}
