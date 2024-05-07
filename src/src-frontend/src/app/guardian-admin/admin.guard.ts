import { CanActivateFn, Router } from '@angular/router';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { inject } from '@angular/core';

export const adminGuard: CanActivateFn = () => {
  const autenticacionService = inject(AutenticacionService);
  const router = inject(Router);

  // Permite el acceso si el usuario es administrador
  if (autenticacionService.isAdmin()) {
    return true;
  } else {
    // Vuelve a perfil si el usuario no es administrador
    const url = router.createUrlTree(['/perfil']);
    return url;
  }
};
