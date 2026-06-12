/** Modèle de données pour un tableau (board). */
export interface BoardDTO {
  id: number;
  title: string;
  backgroundColor?: string;
  isFavorite: boolean;
  workspaceId: number;

}
