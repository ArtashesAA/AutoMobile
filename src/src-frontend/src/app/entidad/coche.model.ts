import { Imagen } from './imagen.model';
import { Usuario } from './usuario.model';

export class Coche {
  constructor(
    id: number,
    marca: string,
    modelo: string,
    anyo: number,
    potencia: number,
    kilometraje: number,
    peso: number,
    combustible: string,
    color: string,
    precio: number,
    descripcion: string,
    usuario: Usuario,
    imagenes: Imagen[]
  ) {
    this.id = id;
    this.marca = marca;
    this.modelo = modelo;
    this.anyo = anyo;
    this.potencia = potencia;
    this.kilometraje = kilometraje;
    this.peso = peso;
    this.combustible = combustible;
    this.color = color;
    this.precio = precio;
    this.descripcion = descripcion;
    this.usuario = usuario;
    this.imagenes = imagenes;
  }

  id: number = 1;
  marca: string = '';
  modelo: string = '';
  anyo: number = 0;
  potencia: number = 0;
  kilometraje: number = 0;
  peso: number = 0;
  combustible: string = '';
  color: string = '';
  precio: number = 0;
  descripcion: string = '';
  usuario: Usuario;
  imagenes: Imagen[] = [];
}
