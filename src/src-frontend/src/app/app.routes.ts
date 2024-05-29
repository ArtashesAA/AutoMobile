import { Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
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
import { adminGuard } from './guardian-admin/admin.guard';
import { RegistroCorrectoComponent } from './registro-correcto/registro-correcto.component';
import { ModificarUsuarioComponent } from './modificar-usuario/modificar-usuario.component';
import { ModificarUsuarioCorrectoComponent } from './modificar-usuario-correcto/modificar-usuario-correcto.component';
import { ErrorComponent } from './error/error.component';
import { CochesUsuarioComponent } from './coches-usuario/coches-usuario.component';
import { FavoritosUsuarioComponent } from './favoritos-usuario/favoritos-usuario.component';
import { CrearCocheCorrectoComponent } from './crear-coche-correcto/crear-coche-correcto.component';
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
  { path: 'venderCorrecto', component: CrearCocheCorrectoComponent },
  { path: 'actualiza/:id', component: ActualizarCocheComponent },
  {
    path: 'actualizaUsuario/:id',
    component: ActualizarUsuarioComponent,
    canActivate: [adminGuard],
  },
  { path: 'verCoche/:id', component: VerCocheComponent },
  { path: 'verNoticia/:id', component: VerNoticiaComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrarse', component: RegistroComponent },
  { path: 'perfil', component: PerfilComponent },
  { path: 'cochesUsuario', component: CochesUsuarioComponent },
  { path: 'favoritosUsuario', component: FavoritosUsuarioComponent },
  { path: 'modificarUsuario', component: ModificarUsuarioComponent },
  { path: 'modificarCorrecto', component: ModificarUsuarioCorrectoComponent },
  {
    path: 'gestionUsuarios',
    component: GestionUsuariosComponent,
    canActivate: [adminGuard],
  },
  { path: 'registroCorrecto', component: RegistroCorrectoComponent },
  { path: '**', component: ErrorComponent },
];
