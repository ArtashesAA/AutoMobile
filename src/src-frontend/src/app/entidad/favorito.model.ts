import { Coche } from './coche.model';
import { Usuario } from './usuario.model';

export class Favorito {
  id: number;
  fecha?: Date;
  coche: Coche;
  usuario: Usuario;
  coche_id_recuperado?: number;

  constructor(
    id: number,
    coche: Coche,
    usuario: Usuario,
    coche_id_recuperado?: number,
    fecha?: Date
  ) {
    this.id = id;
    this.fecha = fecha;
    this.coche = coche;
    this.usuario = usuario;
    this.coche_id_recuperado = coche_id_recuperado;
  }
}
