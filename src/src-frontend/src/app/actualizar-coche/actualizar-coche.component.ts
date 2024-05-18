import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Coche } from '../entidad/coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { Usuario } from '../entidad/usuario.model';

@Component({
  selector: 'app-actualizar-coche',
  standalone: true,
  imports: [CommonModule, FormsModule, CocheHijoComponent],
  providers: [ServicioCocheService],
  templateUrl: './actualizar-coche.component.html',
  styleUrl: './actualizar-coche.component.css',
})
export class ActualizarCocheComponent implements OnInit {
  cuadroId: number = 0;
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

  indice!: number;
  coches: Coche[] = [];

  constructor(
    private router: Router,
    private miServicio: ServicioCocheService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.coches = this.miServicio.coches;
    this.indice = this.route.snapshot.params['id'];

    this.miServicio.cargarCochePorId(this.indice).subscribe((coche: Coche) => {
      this.cuadroMarca = coche.marca;
      this.cuadroModelo = coche.modelo;
      this.cuadroImagenPrincipal = coche.imagen_principal;
      this.cuadroPrecio = coche.precio;
      this.cuadroAnyo = coche.anyo;
      this.cuadroPotencia = coche.potencia;
      this.cuadroKilometraje = coche.kilometraje;
      this.cuadroCombustible = coche.combustible;
      this.cuadroConsumo = coche.consumo;
      this.cuadroTipoCambio = coche.tipoCambio;
      this.cuadroCategoria = coche.categoria;
      this.cuadroTipoVehiculo = coche.tipoVehiculo;
      this.cuadroTraccion = coche.traccion;
      this.cuadroPlazas = coche.plazas;
      this.cuadroPuertas = coche.puertas;
      this.cuadroGarantia = coche.garantia;
      this.cuadroPeso = coche.peso;
      this.cuadroColor = coche.color;
      this.cuadroNumeroMarchas = coche.numeroMarchas;
      this.cuadroNumeroCilindros = coche.numeroCilindros;
      this.cuadroCiudad = coche.ciudad;
      this.cuadroDescripcion = coche.descripcion;
      this.cuadroTelefonoAdjunto = coche.telefonoAdjunto;
      this.cuadroEmailAdjunto = coche.emailAdjunto;
    });

    // const botonActualizar = document.getElementById('boton-actualizar');
    // if (botonActualizar) {
    //   botonActualizar.addEventListener('click', this.actualizaCoche.bind(this));
    // }

    const botonVolverCatalogo = document.getElementById(
      'boton-volver-catalogo'
    );
    if (botonVolverCatalogo) {
      botonVolverCatalogo.addEventListener(
        'click',
        this.volverACatalogo.bind(this)
      );
    }
  }

  actualizaCoche() {
    if (
      this.cuadroMarca &&
      this.cuadroModelo &&
      this.cuadroImagenPrincipal &&
      this.cuadroPrecio &&
      this.cuadroAnyo &&
      this.cuadroPotencia &&
      this.cuadroKilometraje &&
      this.cuadroCombustible &&
      this.cuadroConsumo &&
      this.cuadroTipoCambio &&
      this.cuadroCategoria &&
      this.cuadroTipoVehiculo &&
      this.cuadroTraccion &&
      this.cuadroPlazas &&
      this.cuadroPuertas &&
      this.cuadroGarantia &&
      this.cuadroPeso &&
      this.cuadroColor &&
      this.cuadroNumeroMarchas &&
      this.cuadroNumeroCilindros &&
      this.cuadroCiudad &&
      this.cuadroDescripcion &&
      this.cuadroTelefonoAdjunto &&
      this.cuadroEmailAdjunto
    ) {
      let miCoche = new Coche(
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
        this.cuadroEmailAdjunto
      );

      // Cargar el coche por su ID
      this.miServicio.cargarCochePorId(this.indice).subscribe(
        (coche: Coche) => {
          if (coche) {
            // Mostrar alerta de confirmación
            const confirmacion = window.confirm(
              `¿Estás seguro que quieres actualizar el coche ${coche.marca}?`
            );

            // Si el coche confirma la actualización
            if (confirmacion) {
              // Actualizar el coche
              this.miServicio.actualizarCoche(this.indice, miCoche).subscribe(
                () => {
                  alert('Coche actualizado correctamente');
                  // Vuelve a la página de gestión
                  this.volverACatalogo();
                },
                (error) => {
                  if (error.status === 200) {
                    alert('Coche actualizado correctamente');
                    // Vuelve a la página de gestión
                    this.volverACatalogo();
                  } else {
                    console.error('Error al eliminar coche:', error);
                  }
                }
              );
            }
          } else {
            console.error('El coche con ID', this.indice, 'no fue encontrado.');
          }
        },
        (error) => {
          // Si ocurre un error al cargar el coche por su ID, se captura aquí
          console.error('Error al cargar el coche:', error);
          // Aquí puedes agregar más lógica para manejar el error, como mostrar un mensaje al coche
        }
      );
    }
  }

  volverACatalogo() {
    this.router.navigate(['catalogo']);
  }
}
