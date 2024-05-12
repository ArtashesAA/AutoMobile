import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginService } from './login/servicio/login.service';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { AutenticacionService } from './servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [RouterOutlet, HeaderComponent, FooterComponent],
})
export class AppComponent {
  title = 'AutoMobile';
  constructor(
    private servicioAutenticacion: AutenticacionService,
    private servicioLogin: LoginService
  ) {}

  estaLogueado() {
    return this.servicioAutenticacion.estaAutenticado();
  }

  logout() {
    this.servicioLogin.logout();
  }
}
