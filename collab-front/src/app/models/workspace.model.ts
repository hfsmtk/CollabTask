/** Modèle de données pour un workspace. */
export interface WorkspaceDTO {
  id: number;
  name: string;
  description?: string;
  slug?: string;
  ownerId?: number;
  myRole?: string;
}

export type WorkspaceRole = 'OWNER' | 'ADMIN' | 'MEMBER' | 'VIEWER';

/** Modèle de données pour un membre de workspace. */
export interface WorkspaceMemberDTO {
  userId: number;
  name: string;
  email: string;
  avatarUrl?: string;
  role: WorkspaceRole;
}
