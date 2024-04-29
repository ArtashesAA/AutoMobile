import { Imagen } from './imagen.model';
import { Usuario } from './usuario.model';

export class Coche {
  id?: number;
  marca: string;
  modelo: string;
  precio: number;
  anyo: number;
  potencia: number;
  kilometraje: number;
  combustible: string;
  consumo: string;
  tipo_cambio: string;
  categoria: string;
  tipo_vehiculo: string;
  traccion: string;
  plazas: number;
  puertas: number;
  garantia: string;
  peso: number;
  color: string;
  numero_marchas: number;
  numero_cilindros: number;
  ciudad: string;
  descripcion: string;
  usuario?: Usuario;
  imagenes: Imagen[];

  constructor(
    marca: string,
    modelo: string,
    precio: number,
    anyo: number,
    potencia: number,
    kilometraje: number,
    combustible: string,
    consumo: string,
    tipo_cambio: string,
    categoria: string,
    tipo_vehiculo: string,
    traccion: string,
    plazas: number,
    puertas: number,
    garantia: string,
    peso: number,
    color: string,
    numero_marchas: number,
    numero_cilindros: number,
    ciudad: string,
    descripcion: string,
    usuario: Usuario,
    imagenes: Imagen[]
  ) {
    this.marca = marca;
    this.modelo = modelo;
    this.precio = precio;
    this.anyo = anyo;
    this.potencia = potencia;
    this.kilometraje = kilometraje;
    this.combustible = combustible;
    this.consumo = consumo;
    this.tipo_cambio = tipo_cambio;
    this.categoria = categoria;
    this.tipo_vehiculo = tipo_vehiculo;
    this.traccion = traccion;
    this.plazas = plazas;
    this.puertas = puertas;
    this.garantia = garantia;
    this.peso = peso;
    this.color = color;
    this.numero_marchas = numero_marchas;
    this.numero_cilindros = numero_cilindros;
    this.ciudad = ciudad;
    this.descripcion = descripcion;
    this.usuario = usuario;
    this.imagenes = imagenes;
  }
}
