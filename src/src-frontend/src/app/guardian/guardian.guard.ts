import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

export const guardianGuard: CanActivateFn = () => {
  const servicioAutenticacion = inject(AutenticacionService);
  const router = inject(Router);

  // Permite el acceso si el usuario esta auntenticado
  if (servicioAutenticacion.estaAutenticado()) {
    return true;
  } else {
    // Lleva a login si el usuario no esta auntenticado
    const url = router.createUrlTree(['/login']);
    return url;
  }
};
