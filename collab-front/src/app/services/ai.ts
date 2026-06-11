import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AiRequest, AiResponse } from '../models/ai.model';

@Injectable({
  providedIn: 'root'
})
export class AiService {

  private apiUrl = 'http://localhost:8086/api/ai';

  constructor(private httpClient: HttpClient) {}

  generateDescription(request: AiRequest): Observable<AiResponse> {
    return this.httpClient.post<AiResponse>(
      `${this.apiUrl}/generate-description`,
      request
    );
  }
}