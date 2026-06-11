import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

interface AuthResponse {
  token?: string;
  userId: number;
  email?: string;
  name?: string;
  workspaceId?: number;
  role?: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.authUrl;

  constructor(private httpClient: HttpClient, private router: Router) {}

  register(name: string, email: string, password: string) {
    return this.httpClient.post<AuthResponse>(`${this.apiUrl}/register`, { name, email, password })
      .pipe(tap(response => this.saveSession(response)));
  }

  login(email: string, password: string) {
    return this.httpClient.post<AuthResponse>(`${this.apiUrl}/login`, { email, password })
      .pipe(tap(response => this.saveSession(response)));
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getCurrentUserId(): number {
    return Number(localStorage.getItem('userId'));
  }

  getCurrentUserName(): string {
    return localStorage.getItem('userName') ?? '';
  }

  saveSession(response: AuthResponse) {
    localStorage.setItem('token', response.token ?? '');
    localStorage.setItem('userId', response.userId.toString());
    localStorage.setItem('userName', response.name ?? '');
    localStorage.setItem('userEmail', response.email ?? '');
    if (response.workspaceId) {
      localStorage.setItem('workspaceId', response.workspaceId.toString());
    }
    if (response.role) {
      localStorage.setItem('myRole', response.role);
    }
  }

  getCurrentUserRole(): string {
    return localStorage.getItem('myRole') ?? '';
  }
}
