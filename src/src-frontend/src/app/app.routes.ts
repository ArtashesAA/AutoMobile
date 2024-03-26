import { Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { ActualizarCocheComponent } from './actualizar-coche/actualizar-coche.component';
import { RecuperarCocheComponent } from './recuperar-coche/recuperar-coche.component';
import { ErrorPersonalizadoComponent } from './error-personalizado/error-personalizado.component';

export const routes: Routes = [
  { path: '', component: InicioComponent },
  { path: 'actualiza/:id', component: ActualizarCocheComponent },
  { path: 'ver/:id', component: RecuperarCocheComponent },
  { path: '**', component: ErrorPersonalizadoComponent },
];
