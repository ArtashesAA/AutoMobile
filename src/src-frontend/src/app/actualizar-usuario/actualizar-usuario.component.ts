import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { GestionUsuariosComponent } from '../gestion-usuarios/gestion-usuarios.component';

@Component({
  selector: 'app-actualizar-usuario',
  standalone: true,
  imports: [FormsModule, GestionUsuariosComponent],
  providers: [ServicioUsuarioService],
  templateUrl: './actualizar-usuario.component.html',
  styleUrl: './actualizar-usuario.component.css',
})
export class ActualizarUsuarioComponent implements OnInit {
  cuadroId: number = 0;
  cuadroNombreDeUsuario: string = '';
  cuadroEmail: string = '';
  cuadroImagen: string = '';
  cuadroPassword: string = '';
  cuadroRole: string = '';

  indice!: number;
  usuarios: Usuario[] = [];

  constructor(
    private router: Router,
    private miServicio: ServicioUsuarioService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const id = +params['id'];
      this.indice = id;
      this.miServicio.cargarUsuarioPorId(id).subscribe((usuario: Usuario) => {
        this.cuadroId = usuario.id;
        this.cuadroNombreDeUsuario = usuario.nombre_usuario;
        this.cuadroEmail = usuario.email;
        this.cuadroImagen = usuario.imagen_usuario;
        this.cuadroPassword = usuario.password;
        this.cuadroRole = usuario.role;
      });
    });

    const botonVolverAGestionUsuarios = document.getElementById(
      'boton-volver-gestion-usuarios'
    );
    if (botonVolverAGestionUsuarios) {
      botonVolverAGestionUsuarios.addEventListener(
        'click',
        this.volverAGestionDeUsuarios.bind(this)
      );
    }
  }

  actualizaUsuario() {
    let miUsuario = new Usuario(
      this.cuadroId,
      this.cuadroNombreDeUsuario,
      this.cuadroEmail,
      this.cuadroImagen,
      this.cuadroPassword,
      this.cuadroRole
    );

    this.miServicio.actualizarUsuario(this.indice, miUsuario);
    this.volverAGestionDeUsuarios();
  }

  volverAGestionDeUsuarios() {
    this.router.navigate(['gestionUsuarios']);
  }
}
