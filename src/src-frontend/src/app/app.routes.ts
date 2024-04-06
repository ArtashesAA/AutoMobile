import { Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { ErrorPersonalizadoComponent } from './error-personalizado/error-personalizado.component';
import { LoginComponent } from './login/login.component';
import { CatalogoCocheComponent } from './catalogo-coche/catalogo-coche.component';
import { RegistroComponent } from './registro/registro.component';
import { VerCocheComponent } from './ver-coche/ver-coche.component';
import { CrearCocheComponent } from './crear-coche/crear-coche.component';
import { NoticiasComponent } from './noticias/noticias.component';
import { guardianGuard } from './guardian/guardian.guard';

export const routes: Routes = [
  { path: '', component: InicioComponent },
  {
    path: 'catalogo',
    component: CatalogoCocheComponent,
  },
  {
    path: 'noticias',
    component: NoticiasComponent,
    canActivate: [guardianGuard],
  },
  { path: 'ver/:id', component: VerCocheComponent },
  {
    path: 'crear',
    component: CrearCocheComponent,
    canActivate: [guardianGuard],
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistroComponent },
  { path: '**', component: ErrorPersonalizadoComponent },
];
