import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginService } from './login/login.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'AutoMobile';
  constructor(private loginService: LoginService) {}
  ngOnInit(): void {
    firebase.initializeApp({
      apiKey: 'AIzaSyAKFg3dJOriEX6oeICUOq4LRp1OlJm9I_c',
      authDomain: 'app-coches-dbe72.firebaseapp.com',
    });
  }

  estaLogueado() {
    return this.loginService.estaLogueado();
  }

  logout() {
    this.loginService.logout();
  }
}
