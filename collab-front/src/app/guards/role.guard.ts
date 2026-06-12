import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth';

/**
 * Guard fonctionnel Angular paramétré par une liste de rôles autorisés.
 * Vérifie que le rôle de l'utilisateur connecté (stocké en session) figure dans `allowedRoles`.
 * Redirige vers `/dashboard` si le rôle est insuffisant.
 */
export const roleGuard = (allowedRoles: string[]): CanActivateFn => () => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const role = authService.getCurrentUserRole();

  if (allowedRoles.includes(role)) {
    return true;
  }

  router.navigate(['/dashboard']);
  return false;
};