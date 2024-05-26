import { Coche } from './coche.model';

export class Imagen {
  id?: number;
  imagen_url: string;
  coche?: Coche;

  constructor(imagen_url: string, id?: number, coche?: Coche) {
    this.id = id;
    this.coche = coche;
    this.imagen_url = imagen_url;
  }
}
