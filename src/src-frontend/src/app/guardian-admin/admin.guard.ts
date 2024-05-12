import { CanActivateFn, Router } from '@angular/router';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { inject } from '@angular/core';

export const adminGuard: CanActivateFn = () => {
  const servicioAutenticacion = inject(AutenticacionService);
  const router = inject(Router);

  if (servicioAutenticacion.esAdmin()) {
    return true;
  } else {
    // Redirigir a la página de inicio u otra página
    const url = router.navigate(['/perfil']);
    return url;
  }
};
