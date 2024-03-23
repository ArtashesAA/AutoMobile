import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorPersonalizadoComponent } from './error-personalizado/error-personalizado.component';
import { ActualizaComponentComponent } from './actualiza-component/actualiza-component.component';
import { InicioComponentComponent } from './inicio-component/inicio-component.component';

const routes: Routes = [
  { path: '', component: InicioComponentComponent },
  { path: 'actualiza/:id', component: ActualizaComponentComponent },
  { path: 'ver/:id', component: ActualizaComponentComponent },
  { path: '**', component: ErrorPersonalizadoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
