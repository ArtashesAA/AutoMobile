import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coche } from '../entidad/coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';

@Component({
  selector: 'app-crear-coche',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [ServicioCocheService, ServicioUsuarioService],
  templateUrl: './crear-coche.component.html',
  styleUrl: './crear-coche.component.css',
})
export class CrearCocheComponent implements OnInit {
  coches: Coche[] = [];
  coche: Coche | undefined;

  cuadroMarca: string = '';
  cuadroModelo: string = '';
  cuadroImagenPrincipal: string = '';
  cuadroPrecio: number = 0;
  cuadroAnyo: number = 0;
  cuadroPotencia: number = 0;
  cuadroKilometraje: number = 0;
  cuadroCombustible: string = '';
  cuadroConsumo: string = '';
  cuadroTipoCambio: string = '';
  cuadroCategoria: string = '';
  cuadroTipoVehiculo: string = '';
  cuadroTraccion: string = '';
  cuadroPlazas: number = 0;
  cuadroPuertas: number = 0;
  cuadroGarantia: string = '';
  cuadroPeso: number = 0;
  cuadroColor: string = '';
  cuadroNumeroMarchas: number = 0;
  cuadroNumeroCilindros: number = 0;
  cuadroCiudad: string = '';
  cuadroDescripcion: string = '';
  cuadroTelefonoAdjunto: number = 0;
  cuadroEmailAdjunto: string = '';
  cuadroUsuario: Usuario | undefined;

  constructor(
    private router: Router,
    private servicioCoche: ServicioCocheService,
    private servicioUsuario: ServicioUsuarioService
  ) {}

  ngOnInit(): void {
    const botonVolverInicio = document.getElementById('boton-volver-inicio');
    if (botonVolverInicio) {
      botonVolverInicio.addEventListener(
        'click',
        this.volverAlInicio.bind(this)
      );
    }
  }

  // Crea un coche con los datos del formulario
  crearCoche() {
    // Obtener el usuario actual
    this.servicioUsuario.getUsuarioActual().subscribe(
      (usuario: Usuario) => {
        // Asignar el usuario al cuadroUsuario
        this.cuadroUsuario = usuario;

        this.coche = new Coche(
          this.cuadroMarca,
          this.cuadroModelo,
          this.cuadroImagenPrincipal,
          this.cuadroPrecio,
          this.cuadroAnyo,
          this.cuadroPotencia,
          this.cuadroKilometraje,
          this.cuadroCombustible,
          this.cuadroConsumo,
          this.cuadroTipoCambio,
          this.cuadroCategoria,
          this.cuadroTipoVehiculo,
          this.cuadroTraccion,
          this.cuadroPlazas,
          this.cuadroPuertas,
          this.cuadroGarantia,
          this.cuadroPeso,
          this.cuadroColor,
          this.cuadroNumeroMarchas,
          this.cuadroNumeroCilindros,
          this.cuadroCiudad,
          this.cuadroDescripcion,
          this.cuadroTelefonoAdjunto,
          this.cuadroEmailAdjunto,
          this.cuadroUsuario
        );
        // Crear el coche con los datos del formulario
        this.servicioCoche.crearCoche(this.coche);

        // Volver al catálogo
        this.router.navigate(['/catalogo']);
      },
      (error) => {
        console.error('Error al obtener el usuario:', error);
      }
    );
  }

  volverAlInicio() {
    this.router.navigate(['']);
  }
}
