import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Coche } from '../entidad/coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { Usuario } from '../entidad/usuario.model';
import { HttpClient } from '@angular/common/http';
import { MarcasService } from '../servicio-marcas/marcas.service';
import { Imagen } from '../entidad/imagen.model';

@Component({
  selector: 'app-actualizar-coche',
  standalone: true,
  imports: [CommonModule, FormsModule, CocheHijoComponent],
  providers: [ServicioCocheService],
  templateUrl: './actualizar-coche.component.html',
  styleUrl: './actualizar-coche.component.css',
})
export class ActualizarCocheComponent implements OnInit {
  // Array para almacenar las marcas que se obtendrán del servicio marcas
  marcas: any[] = [];

  // Array para almacenar los modelos que se obtendrán de la api
  modelos: any[] = [];

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
  cuadroImagenes: { imagen_url: string }[] = [];

  indice!: number;
  coches: Coche[] = [];

  constructor(
    private router: Router,
    private miServicio: ServicioCocheService,
    private http: HttpClient,
    private route: ActivatedRoute,
    private servicioMarcas: MarcasService
  ) {}

  ngOnInit(): void {
    this.coches = this.miServicio.coches;
    this.indice = this.route.snapshot.params['id'];

    this.miServicio.cargarCochePorId(this.indice).subscribe((coche: Coche) => {
      this.cuadroMarca = coche.marca;
      this.cuadroModelo = coche.modelo;
      this.cuadroImagenPrincipal = coche.imagenPrincipal;
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
    this.marcas = this.servicioMarcas.obtenerMarcas();
  }

  // Al cambiar la marca
  onMarcaChange() {
    // Si no se ha seleccionado ninguna marca, reinicia los modelos
    if (!this.cuadroMarca) {
      this.modelos = [];
      return;
    }

    // Realiza una solicitud HTTP para obtener los modelos de la marca seleccionada
    const url = `https://www.autoscout24.es/listing-form/models/C/${this.cuadroMarca}`;
    console.log('URL de solicitud:', url); // Agregar impresión para depuración

    this.http.get<any[]>(url).subscribe(
      (response) => {
        // Mapea la respuesta para obtener solo los nombres de los modelos
        console.log('Respuesta de la solicitud:', response); // Agregar impresión para depuración
        this.modelos = response.map((item) => item.label);
      },
      (error) => {
        console.error('Error al obtener los modelos:', error); // Agregar manejo de errores
      }
    );
  }

  // Actualizar coche
  actualizaCoche() {
    const marcaId = Number(this.cuadroMarca);
    const nombreMarca = this.servicioMarcas.obtenerNombreMarcaPorId(marcaId);

    // Asegurarse de que cuadroImagenes es un array antes de mapear
    const imagenes: Imagen[] = Array.isArray(this.cuadroImagenes)
      ? this.cuadroImagenes.map((imagen) => new Imagen(imagen.imagen_url))
      : [];

    // Verificar que todos los campos estén completos
    if (
      !this.cuadroMarca ||
      !this.cuadroModelo ||
      !this.cuadroTipoVehiculo ||
      !this.cuadroPlazas ||
      !this.cuadroPuertas ||
      !this.cuadroColor ||
      !this.cuadroPeso ||
      !this.cuadroKilometraje ||
      !this.cuadroAnyo ||
      !this.cuadroGarantia ||
      !this.cuadroTraccion ||
      !this.cuadroTipoCambio ||
      !this.cuadroNumeroMarchas ||
      !this.cuadroPotencia ||
      !this.cuadroNumeroCilindros ||
      !this.cuadroCombustible ||
      !this.cuadroConsumo ||
      !this.cuadroImagenPrincipal ||
      !this.cuadroPrecio ||
      !this.cuadroEmailAdjunto ||
      !this.cuadroTelefonoAdjunto ||
      !this.cuadroCiudad
    ) {
      alert('Debe rellenar todos los datos.');
      return;
    }

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
        nombreMarca,
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
        this.cuadroUsuario,
        imagenes
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
                  window.scrollTo({ top: 0, behavior: 'smooth' });
                },
                (error) => {
                  if (error.status === 200) {
                    alert('Coche actualizado correctamente');
                    // Vuelve a la página de gestión
                    this.volverACatalogo();
                    window.scrollTo({ top: 0, behavior: 'smooth' });
                  } else {
                    console.error('Error al eliminar coche:', error);
                  }
                }
              );
            }
          } else {
            alert('Error al actualizar el coche');
            console.error('El coche con ID', this.indice, 'no fue encontrado.');
          }
        },
        (error) => {
          // Si ocurre un error al cargar el coche por su ID, se captura aquí
          alert('Error al actualizar el coche');
          console.error('Error al cargar el coche:', error);
        }
      );
    }
  }

  // Modificar datos con botones
  sumarPlaza() {
    if (this.cuadroPlazas < 20) {
      this.cuadroPlazas++;
    }
  }

  restarPlaza() {
    if (this.cuadroPlazas > 0) {
      this.cuadroPlazas--;
    }
  }

  sumarPuerta() {
    if (this.cuadroPuertas < 10) {
      this.cuadroPuertas++;
    }
  }

  restarPuerta() {
    if (this.cuadroPuertas > 0) {
      this.cuadroPuertas--;
    }
  }

  sumarMarchas() {
    if (this.cuadroNumeroMarchas < 10) {
      this.cuadroNumeroMarchas++;
    }
  }

  restarMarchas() {
    if (this.cuadroNumeroMarchas > 0) {
      this.cuadroNumeroMarchas--;
    }
  }

  sumarCilindros() {
    if (this.cuadroNumeroCilindros < 20) {
      this.cuadroNumeroCilindros++;
    }
  }

  restarCilindros() {
    if (this.cuadroNumeroCilindros > 0) {
      this.cuadroNumeroCilindros--;
    }
  }

  agregarImagen() {
    this.cuadroImagenes.push({ imagen_url: '' });
  }

  eliminarImagen(index: number) {
    this.cuadroImagenes.splice(index, 1);
  }

  // Volver a catálogo
  volverACatalogo() {
    this.router.navigate(['catalogo']);
  }

  /* Navegación */
  irDatosVehiculo() {
    window.scrollTo({ top: window.innerHeight * 0.1, behavior: 'smooth' });
  }

  irCaractristicas() {
    window.scrollTo({ top: window.innerHeight * 0.4, behavior: 'smooth' });
  }

  irEstadoVehiculo() {
    window.scrollTo({ top: window.innerHeight * 0.85, behavior: 'smooth' });
  }

  irTransmision() {
    window.scrollTo({ top: window.innerHeight * 1.15, behavior: 'smooth' });
  }

  irMotor() {
    window.scrollTo({ top: window.innerHeight * 1.45, behavior: 'smooth' });
  }

  irImagenes() {
    window.scrollTo({ top: window.innerHeight * 1.8, behavior: 'smooth' });
  }

  irDescripcion() {
    window.scrollTo({ top: window.innerHeight * 2, behavior: 'smooth' });
  }

  irPrecio() {
    window.scrollTo({ top: window.innerHeight * 2.3, behavior: 'smooth' });
  }

  irContacto() {
    window.scrollTo({ top: window.innerHeight * 2.6, behavior: 'smooth' });
  }
}
