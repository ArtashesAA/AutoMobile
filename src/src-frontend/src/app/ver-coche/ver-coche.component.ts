import { Component, OnInit } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../entidad/usuario.model';
import { ServicioFavoritoService } from '../servicio-favorito/servicio-favorito.service';
import { Favorito } from '../entidad/favorito.model';

@Component({
  selector: 'app-ver-coche',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, CocheHijoComponent],
  providers: [ServicioCocheService, CocheHijoComponent],
  templateUrl: './ver-coche.component.html',
  styleUrl: './ver-coche.component.css',
})
export class VerCocheComponent implements OnInit {
  // Coches que pertenecen al usuario
  cochesUsuario: Coche[] = [];

  // Coche que se va a mostrar
  coche!: Coche;
  id!: number;

  chunkedImages: any[] = [];

  // Usuario actual
  usuario: Usuario | undefined;

  // Comprobaciones
  estaLogueado: boolean = false;
  esAdmin: boolean = false;
  esFavorito: boolean = false;
  esDelUsuario: boolean = false;

  // Mensaje
  mostrar: boolean = false;
  nombre: string = '';
  email: string = '';
  numero: number = 0;
  mensaje: string = '';

  // Contacto
  mostrarTelefono: boolean = true;
  mostrarEmail: boolean = false;
  textoBoton: string = 'Mostrar TLF/EMAIL';

  // Nueva variable para mostrar u ocultar la ventana de compartir
  mostrarCompartir: boolean = false;
  // URL del coche
  urlCoche: string = '';
  favoritoId: number = 0;

  constructor(
    private route: ActivatedRoute,
    private servicioCoche: ServicioCocheService,
    private servicioAutenticacion: AutenticacionService,
    private cocheHijo: CocheHijoComponent,
    private favoritoService: ServicioFavoritoService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
      this.getCoche(this.id);

      if (!isNaN(this.id)) {
        this.servicioCoche.cargarCochePorId(this.id).subscribe(
          (coche: Coche) => {
            this.coche = coche;
            // Comprueba si el coche ya está en favoritos
            if (this.usuario) {
              this.cargarFavoritosUsuario(this.usuario.id);
            }

            // Comprobar si el coche pertenece al usuario
            if (this.cochesUsuario.find((c) => c.id === this.coche.id)) {
              this.esDelUsuario = true;
            }
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

          // Carga los favoritos del usuario actual
          this.cargarFavoritosUsuario(this.usuario.id);

          // Comprueba si el usuario es administrador
          this.servicioAutenticacion.esAdmin().subscribe((isAdmin: boolean) => {
            this.esAdmin = isAdmin;
          });

          // Cargar coches del usuario actual
          this.cargarCochesPorIdUsuario(this.usuario.id);
        },
        (error) => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    }
  }

  cambiarImagenPrincipal(imagen: string): void {
    this.coche.imagenPrincipal = imagen;
  }

  getCoche(id: number): void {
    this.servicioCoche.cargarCochePorId(id).subscribe((coche) => {
      this.coche = coche;
      this.chunkImages();
    });
  }

  chunkImages(): void {
    const chunkSize = 4;
    if (this.coche && this.coche.imagenes) {
      for (let i = 0; i < this.coche.imagenes.length; i += chunkSize) {
        this.chunkedImages.push(this.coche.imagenes.slice(i, i + chunkSize));
      }
    }
  }

  // Carga todos los coches del usuario
  cargarCochesPorIdUsuario(idUsuario: number): void {
    this.servicioCoche.cargarCochesPorIdUsuario(idUsuario).subscribe(
      (coches: Coche[]) => {
        this.cochesUsuario = coches;

        // Comprobar si el coche actual pertenece al usuario
        if (
          this.coche &&
          this.cochesUsuario.find((c) => c.id === this.coche.id)
        ) {
          this.esDelUsuario = true;
        }
      },
      (error) => {
        console.error('Error al cargar coches del usuario:', error);
      }
    );
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
      this.textoBoton = 'Mostrar TLF/EMAIL';
      this.mostrarTelefono = true;
    }
  }

  // Borra un coche por su id
  eliminarCoche(id: number) {
    this.cocheHijo.eliminarCoche(id);
  }

  // Borra un coche por su id solo si pertenece a este usuario
  eliminarCochePropio(id: number) {
    this.cocheHijo.eliminarCochePropio(id);
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

  cargarFavoritosUsuario(idUsuario: number) {
    this.favoritoService.cargarFavoritosPorIdUsuario(idUsuario).subscribe(
      (favoritos: Favorito[]) => {
        if (favoritos && this.coche && this.coche.id !== undefined) {
          const encontrado = favoritos.find(
            (favorito) => favorito.coche_id_recuperado === this.coche.id
          );
          if (encontrado) {
            this.esFavorito = true;
            this.favoritoId = encontrado.id;
          }
        }
      },
      (error) => {
        console.error('Error al cargar los favoritos del usuario:', error);
      }
    );
  }

  // Alterna el estado de favorito
  toggleFavorito() {
    if (this.esFavorito) {
      if (this.favoritoId !== undefined) {
        this.eliminarFavorito();
      }
    } else {
      this.crearFavorito();
    }
  }

  // Añade el coche a favoritos
  crearFavorito() {
    if (this.usuario && this.coche) {
      const favoritoData = {
        usuario: { id: this.usuario.id },
        coche: { id: this.coche.id },
      };

      this.favoritoService.crearFavorito(favoritoData).subscribe(
        (favorito: Favorito) => {
          this.esFavorito = true;
          this.favoritoId = favorito.id;
        },
        (error) => {
          console.error('Error al añadir a favoritos:', error);
        }
      );
    }
  }

  // Elimina el coche de favoritos
  eliminarFavorito() {
    if (this.favoritoId !== undefined) {
      this.favoritoService.eliminarFavorito(this.favoritoId).subscribe(
        (response) => {
          if (response.status === 200) {
            this.esFavorito = false;
            console.log('Eliminado de favoritos con éxito');
            // Opcional: Mostrar una animación o mensaje de éxito
          } else {
            console.error('Error al eliminar de favoritos: ', response.status);
          }
        },
        (error) => {
          // Manejar el caso donde el error tiene un estado 200 pero aún se considera un error
          if (error.status === 200) {
            this.esFavorito = false;
            console.log('Eliminado de favoritos con éxito');
            // Opcional: Mostrar una animación o mensaje de éxito
          } else {
            console.error('Error al eliminar de favoritos:', error);
          }
        }
      );
    }
  }
}
