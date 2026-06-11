package com.example.collab.services;

import com.example.collab.dtos.TaskDTO;
import com.example.collab.entities.Task;
import com.example.collab.entities.TaskColumn;
import com.example.collab.entities.User;
import com.example.collab.exceptions.TaskColumnException;
import com.example.collab.exceptions.TaskException;
import com.example.collab.exceptions.UserNotFoundException;
import com.example.collab.mappers.TaskMappers;
import com.example.collab.repositories.TaskColumnRepository;
import com.example.collab.repositories.TaskRepository;
import com.example.collab.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation de {@link TaskService}.
 * La gestion de la position lors d'un déplacement de tâche garantit
 * que la tâche est toujours insérée en fin de liste dans la colonne cible.
 */
@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskMappers dtoMapper;
    private TaskRepository taskRepository;
    private TaskColumnRepository taskColumnRepository;
    private UserRepository userRepository;
    private NotificationService notificationService;

    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) throws TaskException, UserNotFoundException {
        Task task = dtoMapper.taskDTOToTask(taskDTO);

        TaskColumn taskColumn = taskColumnRepository.findById(taskDTO.getTaskColumnId())
                .orElseThrow(() -> new TaskException("TaskColumn not found"));
        task.setTaskColumn(taskColumn);

        if (taskDTO.getAssigneeId() != null) {
            User assignee = userRepository.findById(taskDTO.getAssigneeId())
                    .orElseThrow(() -> new UserNotFoundException("User not found !"));
            task.setAssignee(assignee);
            taskRepository.save(task);

            Long boardId = taskColumn.getBoard() != null ? taskColumn.getBoard().getId() : null;
            notificationService.createNotification(
                    assignee.getId(),
                    "You have been assigned to task: " + task.getTitle(),
                    "TASK_ASSIGNED",
                    task.getId(),
                    boardId
            );
        } else {
            taskRepository.save(task);
        }

        return dtoMapper.taskToTaskDTO(task);
    }

    @Override
    public TaskDTO getTaskById(Long id) throws TaskException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found !"));
        return dtoMapper.taskToTaskDTO(task);
    }

    @Override
    public TaskDTO getTaskByTitle(String title) throws TaskException {
        Task task = taskRepository.findByTitle(title)
                .orElseThrow(() -> new TaskException("Task not found"));
        return dtoMapper.taskToTaskDTO(task);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) throws TaskException, UserNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found"));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPosition(taskDTO.getPosition());
        task.setPriority(taskDTO.getPriority());
        task.setDueDate(taskDTO.getDueDate() != null ? taskDTO.getDueDate().atStartOfDay() : null);

        if (taskDTO.getAssigneeId() != null) {
            User assignee = userRepository.findById(taskDTO.getAssigneeId())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            task.setAssignee(assignee);
        } else {
            task.setAssignee(null);
        }

        return dtoMapper.taskToTaskDTO(taskRepository.save(task));
    }

    @Override
    public TaskDTO deleteTask(Long id) throws TaskException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found"));
        taskRepository.delete(task);
        return dtoMapper.taskToTaskDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasksByTaskColumn(Long taskColumnId) throws TaskColumnException {
        TaskColumn taskColumn = taskColumnRepository.findById(taskColumnId)
                .orElseThrow(() -> new TaskColumnException("TaskColumn not found"));
        return taskColumn.getTasks().stream()
                .map(dtoMapper::taskToTaskDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> getAllTasksByAssignee(Long assigneeId) throws UserNotFoundException {
        User assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return assignee.getTasks().stream()
                .map(dtoMapper::taskToTaskDTO)
                .toList();
    }

    @Override
    public TaskDTO moveTask(Long taskId, Long targetColumnId) throws TaskException, TaskColumnException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task not found"));
        TaskColumn taskColumn = taskColumnRepository.findById(targetColumnId)
                .orElseThrow(() -> new TaskColumnException("TaskColumn not found"));

        // La tâche est positionnée après la dernière tâche de la colonne cible
        List<Task> targetTasks = taskRepository.findByTaskColumnIdOrderByPositionAsc(targetColumnId);
        int newPosition = targetTasks.isEmpty() ? 0 : targetTasks.get(targetTasks.size() - 1).getPosition() + 1;

        task.setTaskColumn(taskColumn);
        task.setPosition(newPosition);
        taskRepository.save(task);

        return dtoMapper.taskToTaskDTO(task);
    }
}
