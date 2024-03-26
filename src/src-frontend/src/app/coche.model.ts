export class Coche {
  constructor(
    marca: string,
    modelo: string,
    anyo: number,
    potencia: number,
    kilometraje: number,
    peso: number,
    combustible: string,
    color: string,
    precio: number,
    descripcion: string
  ) {
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
  }

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
}
