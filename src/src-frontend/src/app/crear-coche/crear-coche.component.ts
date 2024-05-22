import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coche } from '../entidad/coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { HttpClient } from '@angular/common/http';
import { MarcasService } from '../servicio-marcas/marcas.service';
import { Imagen } from '../entidad/imagen.model';

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

  // Array para almacenar las marcas que se obtendrán del servicio marcas
  marcas: any[] = [];

  // Array para almacenar los modelos que se obtendrán de la api
  modelos: any[] = [];

  cuadroMarca: string = '';
  cuadroModelo: string = '';
  cuadroImagenPrincipal: string = '';
  cuadroPrecio: number = 10000;
  cuadroAnyo: number = 2024;
  cuadroPotencia: number = 100;
  cuadroKilometraje: number = 1000;
  cuadroCombustible: string = '';
  cuadroConsumo: string = '';
  cuadroTipoCambio: string = 'Manual';
  cuadroCategoria: string = '';
  cuadroTipoVehiculo: string = 'Sedan';
  cuadroTraccion: string = '';
  cuadroPlazas: number = 5;
  cuadroPuertas: number = 5;
  cuadroGarantia: string = 'Sin Garantia';
  cuadroPeso: number = 1700;
  cuadroColor: string = '';
  cuadroNumeroMarchas: number = 6;
  cuadroNumeroCilindros: number = 4;
  cuadroCiudad: string = '';
  cuadroDescripcion: string = '';
  cuadroTelefonoAdjunto: number = 0;
  cuadroEmailAdjunto: string = '';
  cuadroUsuario: Usuario | undefined;
  cuadroImagenes: { imagen_url: string }[] = [];

  constructor(
    private router: Router,
    private http: HttpClient,
    private servicioCoche: ServicioCocheService,
    private servicioUsuario: ServicioUsuarioService,
    private servicioMarcas: MarcasService
  ) {}

  ngOnInit(): void {
    this.marcas = this.servicioMarcas.obtenerMarcas();
  }

  // Función para manejar el cambio en la selección de marca
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

  // Crea un coche con los datos del formulario
  crearCoche() {
    this.servicioUsuario.getUsuarioActual().subscribe(
      (usuario: Usuario) => {
        this.cuadroUsuario = usuario;

        const marcaId = Number(this.cuadroMarca);
        const nombreMarca =
          this.servicioMarcas.obtenerNombreMarcaPorId(marcaId);

        // Asegurarse de que cuadroImagenes es un array antes de mapear
        const imagenes: Imagen[] = Array.isArray(this.cuadroImagenes)
          ? this.cuadroImagenes.map((imagen) => new Imagen(imagen.imagen_url))
          : [];

        this.coche = new Coche(
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

        const cocheData = {
          marca: nombreMarca,
          modelo: this.cuadroModelo,
          imagen_principal: this.cuadroImagenPrincipal,
          precio: this.cuadroPrecio,
          anyo: this.cuadroAnyo,
          potencia: this.cuadroPotencia,
          kilometraje: this.cuadroKilometraje,
          combustible: this.cuadroCombustible,
          consumo: this.cuadroConsumo,
          tipoCambio: this.cuadroTipoCambio,
          categoria: this.cuadroCategoria,
          tipoVehiculo: this.cuadroTipoVehiculo,
          traccion: this.cuadroTraccion,
          plazas: this.cuadroPlazas,
          puertas: this.cuadroPuertas,
          garantia: this.cuadroGarantia,
          peso: this.cuadroPeso,
          color: this.cuadroColor,
          numeroMarchas: this.cuadroNumeroMarchas,
          numeroCilindros: this.cuadroNumeroCilindros,
          ciudad: this.cuadroCiudad,
          descripcion: this.cuadroDescripcion,
          telefonoAdjunto: this.cuadroTelefonoAdjunto,
          emailAdjunto: this.cuadroEmailAdjunto,
          imagenes: this.cuadroImagenes,
        };

        this.servicioCoche.crearCoche(cocheData).subscribe(
          (response) => {
            if (response.status === 200 || response.status === 201) {
              alert('Coche creado con éxito.');
              this.router.navigate(['/catalogo']);
            } else {
              alert('Error al crear el coche.');
            }
          },
          (error) => {
            console.error('Error al crear el coche:', error);
            alert('Error al crear el coche.');
          }
        );
      },
      (error) => {
        console.error('Error al obtener el usuario:', error);
      }
    );
  }

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

  volverAlInicio() {
    this.router.navigate(['']);
  }
}
