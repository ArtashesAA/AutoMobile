export class Usuario {
  id: number = 0;
  nombre_usuario: string = '';
  email: string = '';
  imagen_usuario: string = '';
  password: string = '';
  role: string = '';

  constructor(
    id: number,
    nombre_usuario: string,
    email: string,
    imagen_usuario: string,
    password: string,
    role: string
  ) {
    this.id = id;
    this.nombre_usuario = nombre_usuario;
    this.email = email;
    this.imagen_usuario = imagen_usuario;
    this.password = password;
    this.role = role;
  }
}
