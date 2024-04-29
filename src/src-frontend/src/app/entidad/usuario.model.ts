import { Coche } from './coche.model';

export class Usuario {
  id: number = 0;
  nombre_usuario: string = '';
  email: string = '';
  imagen_usuario: string = '';
  password: string = '';
  role: string = '';
  coches?: Coche[];

  constructor(
    id: number,
    nombre_usuario: string,
    email: string,
    imagen_usuario: string,
    password: string,
    role: string,
    coches?: Coche[]
  ) {
    this.id = id;
    this.nombre_usuario = nombre_usuario;
    this.email = email;
    this.imagen_usuario = imagen_usuario;
    this.password = password;
    this.role = role;
    this.coches = coches;
  }
}
