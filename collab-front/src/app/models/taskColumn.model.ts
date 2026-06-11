/** Modèle de données pour une colonne Kanban. */
export interface TaskColumnDTO {
  id?: number;
  name: string;
  position: number;
  boardId: number;
}
