/** Requête envoyée à l'API IA pour la génération de description. */
export interface AiRequest {
  title: string;
}

/** Réponse de l'API IA contenant la description générée. */
export interface AiResponse {
  description: string;
}