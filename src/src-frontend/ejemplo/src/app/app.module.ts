import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ActualizaComponentComponent } from './actualiza-component/actualiza-component.component';
import { AppComponent } from './app.component';
import { CocheHijoCComponent } from './coche-hijo-c/coche-hijo-c.component';
import { DataServices } from './data.services';
import { ErrorPersonalizadoComponent } from './error-personalizado/error-personalizado.component';
import { LoginGuardian } from './login/login-guardian';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login/login.service';
import { ServicioCochesService } from './servicio-coches.service';
import { InicioComponentComponent } from './inicio-component/inicio-component.component';
import { CatalogoComponentComponent } from './catalogo-component/catalogo-component.component';
import { CrearCocheComponentComponent } from './crear-coche-component/crear-coche-component.component';
import { VerCocheComponentComponent } from './ver-coche-component/ver-coche-component.component';

const appRoutes: Routes = [
  { path: '', component: InicioComponentComponent },
  {
    path: 'catalogo',
    component: CatalogoComponentComponent,
  },
  {
    path: 'ver/:id',
    component: VerCocheComponentComponent,
  },
  {
    path: 'crear',
    component: CrearCocheComponentComponent,
    canActivate: [LoginGuardian],
  },
  { path: 'actualiza/:id', component: ActualizaComponentComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', component: ErrorPersonalizadoComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    CocheHijoCComponent,
    ActualizaComponentComponent,
    LoginComponent,
    CocheHijoCComponent,
    InicioComponentComponent,
    CatalogoComponentComponent,
    CrearCocheComponentComponent,
    VerCocheComponentComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    ServicioCochesService,
    DataServices,
    LoginService,
    CookieService,
    LoginGuardian,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
