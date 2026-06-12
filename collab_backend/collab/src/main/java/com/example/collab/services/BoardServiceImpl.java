package com.example.collab.services;

import com.example.collab.dtos.BoardDTO;
import com.example.collab.entities.Board;
import com.example.collab.entities.User;
import com.example.collab.entities.Workspace;
import com.example.collab.entities.WorkspaceMember;
import com.example.collab.enums.WorkspaceRole;
import com.example.collab.exceptions.BoardException;
import com.example.collab.exceptions.UserNotFoundException;
import com.example.collab.exceptions.WorkspaceException;
import com.example.collab.mappers.BoardMappers;
import com.example.collab.repositories.BoardRepository;
import com.example.collab.repositories.UserRepository;
import com.example.collab.repositories.WorkspaceMemberRepository;
import com.example.collab.repositories.WorkspaceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Implémentation de {@link BoardService}.
 * Toutes les opérations de modification vérifient le rôle de l'utilisateur connecté
 * via {@link #requireRole(Long, WorkspaceRole...)}.
 */
@Service
@Transactional
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private BoardMappers dtoMapper;
    private BoardRepository boardRepository;
    private WorkspaceRepository workspaceRepository;
    private WorkspaceMemberRepository workspaceMemberRepository;
    private UserRepository userRepository;

    @Override
    public BoardDTO getBoard(Long id) throws BoardException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardException("Board not found"));
        return dtoMapper.boardToBoardDTO(board);
    }

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) throws BoardException {
        requireRole(boardDTO.getWorkspaceId(), WorkspaceRole.OWNER, WorkspaceRole.ADMIN);

        Board board = dtoMapper.boardDTOToBoard(boardDTO);
        board.setId(null);

        Workspace workspace = workspaceRepository.findById(boardDTO.getWorkspaceId())
                .orElseThrow(() -> new BoardException("Workspace not found"));
        board.setWorkspace(workspace);

        return dtoMapper.boardToBoardDTO(boardRepository.save(board));
    }

    @Override
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) throws BoardException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardException("Board not found"));
        requireRole(board.getWorkspace().getId(), WorkspaceRole.OWNER, WorkspaceRole.ADMIN);

        board.setTitle(boardDTO.getTitle());
        board.setBackgroundColor(boardDTO.getBackgroundColor());
        board.setIsFavorite(boardDTO.getIsFavorite());

        return dtoMapper.boardToBoardDTO(boardRepository.save(board));
    }

    @Override
    public void deleteBoard(Long id) throws BoardException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardException("Board not found"));
        requireRole(board.getWorkspace().getId(), WorkspaceRole.OWNER, WorkspaceRole.ADMIN);
        boardRepository.delete(board);
    }

    @Override
    public List<BoardDTO> getBoards(Long workspaceId) throws WorkspaceException {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceException("Workspace not found"));
        return workspace.getBoards().stream()
                .map(dtoMapper::boardToBoardDTO)
                .toList();
    }

    @Override
    public BoardDTO toggleFavorite(Long id) throws BoardException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardException("Board not found"));
        board.setIsFavorite(!board.getIsFavorite());
        return dtoMapper.boardToBoardDTO(boardRepository.save(board));
    }

    @Override
    public void updateBoardColor(Long id, String color) throws BoardException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardException("Board not found"));
        requireRole(board.getWorkspace().getId(), WorkspaceRole.OWNER, WorkspaceRole.ADMIN);
        board.setBackgroundColor(color);
        boardRepository.save(board);
    }

    /**
     * Vérifie que l'utilisateur connecté possède l'un des rôles autorisés dans le workspace.
     * Lance une exception si l'utilisateur est introuvable, n'est pas membre, ou n'a pas le rôle requis.
     *
     * @param workspaceId identifiant du workspace à vérifier
     * @param allowed     rôles autorisés
     * @throws UserNotFoundException si l'utilisateur connecté n'est pas en base
     * @throws WorkspaceException    si l'utilisateur n'est pas membre ou n'a pas le bon rôle
     */
    private void requireRole(Long workspaceId, WorkspaceRole... allowed) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé !"));
        WorkspaceMember member = workspaceMemberRepository
                .findByWorkspaceIdAndUserId(workspaceId, user.getId())
                .orElseThrow(() -> new WorkspaceException("Vous n'êtes pas membre de ce workspace !"));
        if (!Arrays.asList(allowed).contains(member.getRole()))
            throw new WorkspaceException("Vous n'avez pas les droits nécessaires pour effectuer cette action !");
    }
}
