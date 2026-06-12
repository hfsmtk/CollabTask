import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AiRequest, AiResponse } from '../models/ai.model';
import { environment } from '../../environments/environment';

/** Service HTTP pour la génération de descriptions de tâches via l'API IA (Groq). */
@Injectable({
  providedIn: 'root'
})
export class AiService {

  private apiUrl = `${environment.apiUrl}/ai`;

  constructor(private httpClient: HttpClient) {}

  generateDescription(request: AiRequest): Observable<AiResponse> {
    return this.httpClient.post<AiResponse>(
      `${this.apiUrl}/generate-description`,
      request
    );
  }
}