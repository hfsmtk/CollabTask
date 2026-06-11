import { LabelDTO } from './label.model';

/** Modèle de données pour une tâche. */
export interface TaskDTO {
  id?: number;
  title: string;
  description: string;
  priority: 'HIGH' | 'MEDIUM' | 'LOW';
  taskColumnId?: number;
  assigneeId?: number;
  createdAt?: string;
  updatedAt?: string;
  position?: number;
  dueDate?: string;
  boardId?: number;
  labels?: LabelDTO[];
}
