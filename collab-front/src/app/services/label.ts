import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LabelDTO } from '../models/label.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class LabelService {
  constructor(private httpClient: HttpClient) {}

  private api = environment.apiUrl;

  createLabel(labelDTO: LabelDTO): Observable<LabelDTO> {
    return this.httpClient.post<LabelDTO>(`${this.api}/labels`, labelDTO);
  }

  getLabelsByWorkspace(workspaceId: number): Observable<LabelDTO[]> {
    return this.httpClient.get<LabelDTO[]>(`${this.api}/labels/workspace/${workspaceId}`);
  }

  addLabelToTask(taskId: number, labelId: number): Observable<void> {
    return this.httpClient.post<void>(`${this.api}/labels/task/${taskId}/add/${labelId}`, {});
  }

  removeLabelFromTask(taskId: number, labelId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.api}/labels/task/${taskId}/remove/${labelId}`);
  }
}