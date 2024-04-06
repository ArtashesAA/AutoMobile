import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from '../login/servicio/login.service';
import { inject } from '@angular/core';

export const guardianGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);

  if (loginService.estaLogueado()) {
    return true;
  } else {
    const url = router.createUrlTree(['/login']);
    return url;
  }
};
