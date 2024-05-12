import { Coche } from './coche.model';

export class Imagen {
  id: number;
  imagen_url: string;
  coche: Coche;

  constructor(id: number, coche: Coche, imagen_url: string) {
    this.id = id;
    this.coche = coche;
    this.imagen_url = imagen_url;
  }
}
