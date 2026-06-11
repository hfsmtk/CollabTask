import { Component, Input } from '@angular/core';

/** Carte de statistiques du dashboard : total boards, tâches terminées, tâches en retard, membres actifs. */
@Component({
  selector: 'app-stats-cards',
  standalone: true,
  imports: [],
  templateUrl: './stats-cards.html',
  styleUrls: ['./stats-cards.css']
})
export class StatsCardsComponent {
  @Input() totalBoards = 0;
  @Input() completedTasks = 0;
  @Input() lateTasks = 0;
  @Input() activeMembers = 0;
}
