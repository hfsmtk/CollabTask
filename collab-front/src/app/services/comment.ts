import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CommentDTO } from '../models/comment.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

/** Service HTTP pour la gestion des commentaires de tâches. */
@Injectable({
  providedIn: 'root',
})
export class CommentService {
  constructor(private http: HttpClient) {}

  private api = environment.apiUrl;
  createComment(commentDTO: CommentDTO): Observable<CommentDTO> {
    return this.http.post<CommentDTO>(`${this.api}/comment`, commentDTO);
  }

  getCommentsByTask(taskId: number): Observable<CommentDTO[]> {
    return this.http.get<CommentDTO[]>(`${this.api}/comments/task/${taskId}`);
  }

  deleteComment(commentId: number): Observable<CommentDTO> {
    return this.http.delete<CommentDTO>(`${this.api}/comment/${commentId}`);
  }
}
