import { Routes } from '@angular/router';
import { Dashboard } from './components/dashboard/dashboard';
import { BoardView } from './components/board-view/board-view';
import { BoardsPageComponent } from './components/boards-page/boards-page';
import { authGuard } from './guards/auth.gaurd-guard';
 feature/auth-roles
import { roleGuard } from './guards/role.guard';

 develop
import { LoginComponent } from './components/login/login';
import { RegisterComponent } from './components/register/register';
import { TaskPageComponent } from './components/tasks-page/tasks-page';
import { ProfilePageComponent } from './components/profile-page/profile-page';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
 feature/auth-roles
 { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
  { path: 'board/:id', component: BoardView, canActivate: [authGuard, roleGuard(['OWNER', 'ADMIN', 'MEMBER'])] },
  { path: 'boards', component: BoardsPageComponent, canActivate: [authGuard, roleGuard(['OWNER', 'ADMIN', 'MEMBER'])] },
  { path: 'tasks', component: TaskPageComponent, canActivate: [authGuard, roleGuard(['OWNER', 'ADMIN', 'MEMBER'])] },

  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
  { path: 'board/:id', component: BoardView, canActivate: [authGuard] },
  { path: 'boards', component: BoardsPageComponent, canActivate: [authGuard] },
  { path: 'tasks', component: TaskPageComponent, canActivate: [authGuard] },
 develop
  { path: 'profile', component: ProfilePageComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: 'login' },

];
