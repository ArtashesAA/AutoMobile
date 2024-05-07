import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from '../login/servicio/login.service';
import { inject } from '@angular/core';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

export const guardianGuard: CanActivateFn = () => {
  const servicioAutenticacion = inject(AutenticacionService);
  const router = inject(Router);

  // Permite el acceso si el usuario es administrador
  if (servicioAutenticacion.estaAutenticado()) {
    return true;
  } else {
    // Lleva a login si el usuario no es administrador
    const url = router.createUrlTree(['/login']);
    return url;
  }
};
