import { CanActivateFn, Router } from '@angular/router';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { inject } from '@angular/core';
import { map } from 'rxjs';

export const adminGuard: CanActivateFn = () => {
  const servicioAutenticacion = inject(AutenticacionService);
  const router = inject(Router);

  return servicioAutenticacion.esAdmin().pipe(
    map((isAdmin: boolean) => {
      if (isAdmin) {
        return true;
      } else {
        // Redirige a la página de perfil si no es admin
        router.navigate(['/perfil']);
        return false;
      }
    })
  );
};
