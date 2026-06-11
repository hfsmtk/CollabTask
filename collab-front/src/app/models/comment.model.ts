/** Modèle de données pour un commentaire de tâche. */
export interface CommentDTO {
  id?: number;
  content: string;
  taskId: number;
  createdAt?: string; 
  authorId: number;
  }
