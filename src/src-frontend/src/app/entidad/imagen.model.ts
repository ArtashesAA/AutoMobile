import { Coche } from './coche.model';

export class Imagen {
  id: number = 0;
  coche: Coche | undefined;
  imagen_url: string = '';

  constructor(id: number, coche: Coche, imagen_url: string) {
    this.id = id;
    this.coche = coche;
    this.imagen_url = imagen_url;
  }
}
