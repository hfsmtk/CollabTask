import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BoardDTO } from '../models/board.model';
import { environment } from '../../environments/environment';

/** Service HTTP pour les opérations CRUD sur les tableaux (boards). */
@Injectable({ providedIn: 'root' })
export class BoardService {
  private api = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getBoard(boardId: number): Observable<BoardDTO> {
    return this.http.get<BoardDTO>(`${this.api}/board/${boardId}`);
    }

  createBoard(board: BoardDTO): Observable<BoardDTO> {
    return this.http.post<BoardDTO>(`${this.api}/board`, board);
  }

  updateBoard(boardId: number,boardDTO: BoardDTO): Observable<BoardDTO> {
    return this.http.put<BoardDTO>(`${this.api}/board/${boardId}`, boardDTO);
  }

  deleteBoard(boardId: number): Observable<BoardDTO> {
    return this.http.delete<BoardDTO>(`${this.api}/board/${boardId}`);
  }

  getBoardsByWorkspaceId(workspaceId: number): Observable<BoardDTO[]> {
    return this.http.get<BoardDTO[]>(`${this.api}/allBoards/${workspaceId}`);
  }


  toggleIsFavorite(boardId: number): Observable<BoardDTO> {
    return this.http.put<BoardDTO>(`${this.api}/board/toggleFavorite/${boardId}`, {});
  }


  updateBoardColor(boardId: number, color: string): Observable<BoardDTO> {
    return this.http.put<BoardDTO>(`${this.api}/board/color/${boardId}`, { color });
  }
}
