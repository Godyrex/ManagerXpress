import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthenticationService} from "./authentication.service";

export const authGuard: CanActivateFn = async () => {
  const router = inject(Router);
  const authToken = localStorage.getItem('token');
  const authService = inject(AuthenticationService);

  if (!authToken) {
    router.navigate(['login']);
    return false;
  }
  try {
    const isValid = await authService.validateToken(authToken).toPromise();

    if (!isValid) {
      router.navigate(['login']);
      return false;
    }

    return true;
  } catch (error) {
    console.error('Error validating token:', error);
    router.navigate(['login']);
    return false;
  }

};
