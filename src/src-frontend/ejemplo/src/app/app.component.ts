import { Component, OnInit } from '@angular/core';

import firebase from 'firebase/compat/app';
import { LoginService } from './login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  titulo = 'Listado de Coches';
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
