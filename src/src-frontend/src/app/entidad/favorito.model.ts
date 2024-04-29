import { Coche } from './coche.model';
import { Usuario } from './usuario.model';

export class Favorito {
  constructor(id: number, coche: Coche, usuario: Usuario, fecha: Date) {
    this.id = id;
    this.coche = coche;
    this.usuario = usuario;
    this.fecha = fecha;
  }
  id: number = 0;
  coche: Coche;
  usuario: Usuario;
  fecha: Date;
}
