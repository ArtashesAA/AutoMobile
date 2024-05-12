import { Coche } from './coche.model';
import { Noticia } from './noticia.model';

export class Usuario {
  id: number;
  nombre_usuario: string;
  email: string;
  imagen_usuario: string;
  password: string;
  role: string;
  coches?: Coche[];
  noticias?: Noticia[];

  constructor(
    id: number,
    nombre_usuario: string,
    email: string,
    imagen_usuario: string,
    password: string,
    role: string,
    coches?: Coche[],
    noticias?: Noticia[]
  ) {
    this.id = id;
    this.nombre_usuario = nombre_usuario;
    this.email = email;
    this.imagen_usuario = imagen_usuario;
    this.password = password;
    this.role = role;
    this.coches = coches;
    this.noticias = noticias;
  }
}
