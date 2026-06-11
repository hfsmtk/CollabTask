import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

/** Barre de navigation latérale du dashboard. Affiche le nom de l'utilisateur et des compteurs de boards/tâches. */
@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './sidebar.html',
  styleUrls: ['./sidebar.css']
})
export class SidebarComponent {
  @Input() totalBoards = 0;
  @Input() lateTasks = 0;
  name = localStorage.getItem('userName') ?? 'Utilisateur';
}
