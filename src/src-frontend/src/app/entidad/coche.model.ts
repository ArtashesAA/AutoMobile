import { Usuario } from './usuario.model';

export class Coche {
  id?: number;
  marca: string;
  modelo: string;
  imagen_principal: string;
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

  constructor(
    marca: string,
    modelo: string,
    imagen_principal: string,
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
    usuario?: Usuario
  ) {
    this.marca = marca;
    this.modelo = modelo;
    this.imagen_principal = imagen_principal;
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
  }
}
