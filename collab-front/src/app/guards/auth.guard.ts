import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth';

/**
 * Guard fonctionnel Angular qui bloque l'accès aux routes protégées
 * si l'utilisateur n'est pas connecté (token absent du localStorage).
 * Redirige vers `/login` en cas d'accès non autorisé.
 */
export const authGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isLoggedIn()) {
    return true;
  }

  router.navigate(['/login']);
  return false;
};
