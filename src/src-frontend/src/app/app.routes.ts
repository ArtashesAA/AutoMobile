import { Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { ErrorPersonalizadoComponent } from './error-personalizado/error-personalizado.component';
import { LoginComponent } from './login/login.component';
import { CatalogoCocheComponent } from './catalogo-coche/catalogo-coche.component';
import { RegistroComponent } from './registro/registro.component';
import { CrearCocheComponent } from './crear-coche/crear-coche.component';
import { NoticiasComponent } from './noticias/noticias.component';
import { guardianGuard } from './guardian/guardian.guard';
import { VerCocheComponent } from './ver-coche/ver-coche.component';
import { ActualizarCocheComponent } from './actualizar-coche/actualizar-coche.component';
import { PerfilComponent } from './perfil/perfil.component';
import { VerNoticiaComponent } from './ver-noticia/ver-noticia.component';
import { GestionUsuariosComponent } from './gestion-usuarios/gestion-usuarios.component';
import { ActualizarUsuarioComponent } from './actualizar-usuario/actualizar-usuario.component';
export const routes: Routes = [
  { path: '', component: InicioComponent },
  {
    path: 'catalogo',
    component: CatalogoCocheComponent,
  },
  {
    path: 'explorar',
    component: NoticiasComponent,
    canActivate: [guardianGuard],
  },
  {
    path: 'vender',
    component: CrearCocheComponent,
    canActivate: [guardianGuard],
  },
  { path: 'actualiza/:id', component: ActualizarCocheComponent },
  { path: 'actualizaUsuario/:id', component: ActualizarUsuarioComponent },
  { path: 'verCoche/:id', component: VerCocheComponent },
  { path: 'verNoticia/:id', component: VerNoticiaComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrarse', component: RegistroComponent },
  { path: 'perfil', component: PerfilComponent },
  { path: 'gestionUsuarios', component: GestionUsuariosComponent },
  { path: '**', component: ErrorPersonalizadoComponent },
];
