export class Coche {
  constructor(marca: string, modelo: string, anyo: number, caballos: number) {
    this.marca = marca;
    this.modelo = modelo;
    this.anyo = anyo;
    this.caballos = caballos;
  }

  marca: string = '';
  modelo: string = '';
  anyo: number = 0;
  caballos: number = 0;
}
