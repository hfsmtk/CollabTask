package com.example.collab.dtos;

import com.example.collab.enums.WorkspaceRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** DTO de transfert pour un membre de workspace, incluant son rôle. */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceMemberDTO {

    private Long userId;
    private String name;
    private String email;
    private String avatarUrl;
    private WorkspaceRole role;

}
