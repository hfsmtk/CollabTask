import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDTO } from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  private apiUrl = environment.apiUrl;

  getUsers(): Observable<UserDTO[]> {
    return this.httpClient.get<UserDTO[]>(`${this.apiUrl}/users`);
  }

  getUserById(userId: number): Observable<UserDTO> {
    return this.httpClient.get<UserDTO>(`${this.apiUrl}/user/${userId}`);
  }

  searchUser(email: string, name: string): Observable<UserDTO[]> {
    let params = new HttpParams();
    if (email) params = params.set('email', email);
    if (name) params = params.set('name', name);
    return this.httpClient.get<UserDTO[]>(`${this.apiUrl}/users/search`, { params });
  }

  createUser(userDTO: UserDTO): Observable<UserDTO> {
    return this.httpClient.post<UserDTO>(`${this.apiUrl}/users`, userDTO);
  }

  updateUser(userId: number, userDTO: UserDTO): Observable<UserDTO> {
    return this.httpClient.put<UserDTO>(`${this.apiUrl}/user/${userId}`, userDTO);
  }

  deleteUser(userId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiUrl}/user/${userId}`);
  }
}
