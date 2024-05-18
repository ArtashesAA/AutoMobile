import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error',
  standalone: true,
  imports: [],
  templateUrl: './error.component.html',
  styleUrl: './error.component.css',
})
export class ErrorComponent {
  error: boolean = false;
  mensajeError: string = '';

  constructor(private router: Router) {}

  handleError(error: any): void {
    this.error = true;
    this.mensajeError = error.message;
  }

  volver() {
    this.router.navigate(['']);
  }
}
