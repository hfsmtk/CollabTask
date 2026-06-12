package com.example.collab.services;

import com.example.collab.dtos.TaskColumnDTO;
import com.example.collab.entities.Board;
import com.example.collab.entities.TaskColumn;
import com.example.collab.exceptions.TaskColumnException;
import com.example.collab.mappers.TaskColumnMappers;
import com.example.collab.repositories.BoardRepository;
import com.example.collab.repositories.TaskColumnRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/** Implémentation de {@link TaskColumnService}. */
@Service
@Transactional
@AllArgsConstructor
public class TaskColumnServiceImpl implements TaskColumnService {

    private TaskColumnRepository taskColumnRepository;
    private TaskColumnMappers dtoMapper;
    private BoardRepository boardRepository;

    @Override
    public TaskColumnDTO createColumn(TaskColumnDTO taskColumnDTO) {
        TaskColumn taskColumn = dtoMapper.taskColumnDTOToEntity(taskColumnDTO);

        Board board = boardRepository.findById(taskColumnDTO.getBoardId())
                .orElseThrow(() -> new RuntimeException("Board not found"));
        taskColumn.setBoard(board);

        return dtoMapper.taskColumnToDTO(taskColumnRepository.save(taskColumn));
    }

    @Override
    public TaskColumnDTO updateColumn(Long id, TaskColumnDTO taskColumnDTO) throws TaskColumnException {
        TaskColumn taskColumn = taskColumnRepository.findById(id)
                .orElseThrow(() -> new TaskColumnException("TaskColumn not found"));
        taskColumn.setName(taskColumnDTO.getName());
        taskColumn.setPosition(taskColumnDTO.getPosition());
        return dtoMapper.taskColumnToDTO(taskColumnRepository.save(taskColumn));
    }

    @Override
    public TaskColumnDTO deleteColumn(Long id) throws TaskColumnException {
        TaskColumn taskColumn = taskColumnRepository.findById(id)
                .orElseThrow(() -> new TaskColumnException("TaskColumn not found"));
        taskColumnRepository.delete(taskColumn);
        return dtoMapper.taskColumnToDTO(taskColumn);
    }

    @Override
    public TaskColumnDTO getColumn(Long id) throws TaskColumnException {
        TaskColumn taskColumn = taskColumnRepository.findById(id)
                .orElseThrow(() -> new TaskColumnException("TaskColumn Not found !"));
        return dtoMapper.taskColumnToDTO(taskColumn);
    }

    @Override
    public List<TaskColumnDTO> getColumnsByBoard(Long boardId) throws TaskColumnException {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new TaskColumnException("Board not found"));
        return board.getTaskColumns().stream()
                .map(dtoMapper::taskColumnToDTO)
                .toList();
    }
}
