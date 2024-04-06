export class Usuario {
  constructor(
    nombre_usuario: string,
    email: string,
    imagen_usuario: string,
    role: string
  ) {
    this.nombre_usuario = nombre_usuario;
    this.email = email;
    this.imagen_usuario = imagen_usuario;
    this.role = role;
  }

  nombre_usuario: string = '';
  email: string = '';
  imagen_usuario: string = '';
  role: string = '';
}
