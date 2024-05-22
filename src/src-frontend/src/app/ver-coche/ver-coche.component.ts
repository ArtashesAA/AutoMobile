import { Component, OnInit } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../entidad/usuario.model';

@Component({
  selector: 'app-ver-coche',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, CocheHijoComponent],
  providers: [ServicioCocheService, CocheHijoComponent],
  templateUrl: './ver-coche.component.html',
  styleUrl: './ver-coche.component.css',
})
export class VerCocheComponent implements OnInit {
  coche!: Coche;
  id!: number;
  usuario: Usuario | undefined;

  estaLogueado: boolean = false;
  esAdmin: boolean = false;

  // Mensaje
  mostrar: boolean = false;
  nombre: string = '';
  email: string = '';
  numero: number = 0;
  mensaje: string = '';

  // Contacto
  mostrarTelefono: boolean = true;
  mostrarEmail: boolean = false;
  textoBoton: string = 'Mostar TLF/EMAIL';

  // Nueva variable para mostrar u ocultar la ventana de compartir
  mostrarCompartir: boolean = false;
  // URL del coche
  urlCoche: string = '';

  constructor(
    private route: ActivatedRoute,
    private servicioCoche: ServicioCocheService,
    private servicioAutenticacion: AutenticacionService,
    private cocheHijo: CocheHijoComponent
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];

      if (!isNaN(this.id)) {
        this.servicioCoche.cargarCochePorId(this.id).subscribe(
          (coche: Coche) => {
            this.coche = coche;
          },
          (error) => {
            console.error('Error al cargar coche:', error);
          }
        );
      } else {
        console.error('Índice inválido:', this.id);
      }
    });

    this.estaLogueado = this.servicioAutenticacion.estaAutenticado();

    if (this.estaLogueado) {
      this.servicioAutenticacion.cargarUsuarioActual().subscribe(
        (usuario: Usuario) => {
          this.usuario = usuario;

          // Comprueba si el usuario es administrador
          this.servicioAutenticacion.esAdmin().subscribe((isAdmin: boolean) => {
            this.esAdmin = isAdmin;
          });
        },
        (error) => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    }
  }

  // ------- Formulario Contactar y Telefono ----------

  // Muestra el formulario
  mostrarModal() {
    this.mostrar = true;
  }

  // Cierra el formulario
  ocultarModal() {
    this.mostrar = false;
  }

  // Envia el mensaje
  enviarMensaje() {
    console.log('Mensaje enviado:', this.mensaje);
    // También puedes restablecer los campos del formulario después de enviar el mensaje
    this.nombre = '';
    this.email = '';
    this.numero = 0;
    this.mensaje = '';
    // Ocultar el modal después de enviar el mensaje
    this.ocultarModal();
  }

  cambiarTexto() {
    if (this.mostrarTelefono) {
      // Si se está mostrando el número de teléfono, cambia el texto al correo electrónico
      this.textoBoton = this.coche.telefonoAdjunto.toString();
      this.mostrarTelefono = false;
      this.mostrarEmail = true;
    } else if (this.mostrarEmail) {
      // Si se está mostrando el correo electrónico, cambia el texto al número de teléfono
      this.textoBoton = this.coche.emailAdjunto;
      this.mostrarEmail = false;
    } else {
      this.textoBoton = 'Mostar TLF/EMAIL';
      this.mostrarTelefono = true;
    }
  }

  // Borra un coche por su id
  eliminarCoche(id: number) {
    this.cocheHijo.eliminarCoche(id);
  }

  // Función para mostrar u ocultar la ventana de compartir
  mostrarVentanaCompartir() {
    this.mostrarCompartir = !this.mostrarCompartir;
    if (this.mostrarCompartir) {
      // Generar el URL del coche cuando se muestra la ventana de compartir
      this.urlCoche = this.generarURLCoche();
    }
  }

  // Función para generar el URL del coche
  generarURLCoche(): string {
    return 'http://localhost:4200/verCoche/' + this.id;
  }

  // Función para cerrar la ventana de compartir
  cerrarVentanaCompartir() {
    this.mostrarCompartir = false;
  }
}
