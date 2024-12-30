package br.com.projeto.oliver.service;

import br.com.projeto.oliver.dto.UserDTO;
import br.com.projeto.oliver.dto.UserFindAllDTO;
import br.com.projeto.oliver.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserServiceInterface {
    Page<UserFindAllDTO> getAllUsers(Pageable pageable);
    UserEntity getUserById(Long id);
    UserEntity createUser(UserDTO userDTO);
    UserEntity updateUser(Long id, UserDTO userDTO);
    void deleteUsers(List<Long> ids);
}
