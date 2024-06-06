import { Favorito } from './favorito.model';
import { Imagen } from './imagen.model';
import { Usuario } from './usuario.model';

export class Coche {
  id?: number;
  marca: string;
  modelo: string;
  imagenPrincipal: string;
  precio: number;
  anyo: number;
  potencia: number;
  kilometraje: number;
  combustible: string;
  consumo: string;
  tipoCambio: string;
  categoria: string;
  tipoVehiculo: string;
  traccion: string;
  plazas: number;
  puertas: number;
  garantia: string;
  peso: number;
  color: string;
  numeroMarchas: number;
  numeroCilindros: number;
  ciudad: string;
  descripcion: string;
  telefonoAdjunto: number;
  emailAdjunto: string;
  usuario?: Usuario;
  imagenes?: Imagen[];
  favoritos?: Favorito[];

  constructor(
    marca: string,
    modelo: string,
    imagenPrincipal: string,
    precio: number,
    anyo: number,
    potencia: number,
    kilometraje: number,
    combustible: string,
    consumo: string,
    tipoCambio: string,
    categoria: string,
    tipoVehiculo: string,
    traccion: string,
    plazas: number,
    puertas: number,
    garantia: string,
    peso: number,
    color: string,
    numeroMarchas: number,
    numeroCilindros: number,
    ciudad: string,
    descripcion: string,
    telefonoAdjunto: number,
    emailAdjunto: string,
    usuario?: Usuario,
    imagenes?: Imagen[],
    favoritos?: Favorito[]
  ) {
    this.marca = marca;
    this.modelo = modelo;
    this.imagenPrincipal = imagenPrincipal;
    this.precio = precio;
    this.anyo = anyo;
    this.potencia = potencia;
    this.kilometraje = kilometraje;
    this.combustible = combustible;
    this.consumo = consumo;
    this.tipoCambio = tipoCambio;
    this.categoria = categoria;
    this.tipoVehiculo = tipoVehiculo;
    this.traccion = traccion;
    this.plazas = plazas;
    this.puertas = puertas;
    this.garantia = garantia;
    this.peso = peso;
    this.color = color;
    this.numeroMarchas = numeroMarchas;
    this.numeroCilindros = numeroCilindros;
    this.ciudad = ciudad;
    this.descripcion = descripcion;
    this.telefonoAdjunto = telefonoAdjunto;
    this.emailAdjunto = emailAdjunto;
    this.usuario = usuario;
    this.imagenes = imagenes;
    this.favoritos = favoritos;
  }
}
