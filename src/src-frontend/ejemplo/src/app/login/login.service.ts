import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import firebase from 'firebase/compat/app';

import 'firebase/compat/auth';
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class LoginService {
  constructor(private router: Router, private cookies: CookieService) {}

  token: string;

  login(email: string, password: string): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      firebase
        .auth()
        .signInWithEmailAndPassword(email, password)
        .then((response) => {
          firebase
            .auth()
            .currentUser?.getIdToken()
            .then((token) => {
              this.token = token;
              this.cookies.set('token', this.token);
              this.router.navigate(['/']);
              resolve();
            });
        })
        .catch((error) => {
          reject(error);
        });
    });
  }

  getIdToken() {
    return this.cookies.get('token');
  }

  estaLogueado() {
    return this.cookies.get('token');
  }

  logout() {
    firebase
      .auth()
      .signOut()
      .then(() => {
        this.token = '';
        this.cookies.set('token', this.token);
        this.router.navigate(['/']);
        window.location.reload();
      });
  }
}
