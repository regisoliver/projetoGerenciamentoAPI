package br.com.projeto.oliver.service;

import br.com.projeto.oliver.dto.UserDTO;
import br.com.projeto.oliver.dto.UserFindAllDTO;
import br.com.projeto.oliver.entity.UserEntity;
import br.com.projeto.oliver.exception.ResourceNotFoundException;
import br.com.projeto.oliver.mapper.UserMapper;
import br.com.projeto.oliver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;
    private UserDTO userDTO;

    @BeforeEach
    public void setup() {
        userEntity = UserEntity.builder()
                .id(1L)
                .codigoExterno("123456")
                .nomeCompleto("John Wick")
                .email("john.wick@email.com")
                .cpfCnpj("12345678901")
                .sexo("Masculino")
                .civil("Solteiro")
                .telefone("123456789")
                .telefoneCelular("987654321")
                .dataNascimento(LocalDate.now())
                .dataCriacao(LocalDateTime.now())
                .assinatura(true)
                .build();

        userDTO = UserDTO.builder()
                .codigoExterno("123456")
                .nomeCompleto("Oliver Ribeiro")
                .email("oliver.ribeiro@email.com")
                .cpfCnpj("12345678901")
                .sexo("Masculino")
                .civil("Solteiro")
                .telefone("123456789")
                .telefoneCelular("987654321")
                .dataNascimento(LocalDate.now())
                .build();
    }

    @Test
    public void testGetAllUsers() {
        UserFindAllDTO userFindAllDTO = new UserFindAllDTO(
                userEntity.getId(),
                userEntity.getCodigoExterno(),
                userEntity.getNomeCompleto(),
                userEntity.getCpfCnpj(),
                userEntity.getTelefone(),
                userEntity.isAssinatura(),
                userEntity.isStatus());

        when(userRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(userEntity)));
        when(userMapper.toFindAllDTO(userEntity)).thenReturn(userFindAllDTO);

        Page<UserFindAllDTO> result = userService.getAllUsers(mock(Pageable.class));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(userRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testGetUserById_UserFound() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(userEntity));

        UserEntity result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(userEntity.getId(), result.getId());
        assertEquals("John Wick", result.getNomeCompleto());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity result = userService.createUser(userDTO);

        assertNotNull(result);
        assertEquals(userEntity.getNomeCompleto(), result.getNomeCompleto());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity result = userService.updateUser(1L, userDTO);

        assertNotNull(result);
        assertEquals(userEntity.getNomeCompleto(), result.getNomeCompleto());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testDeleteUsersSuccessfully() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1L);
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2L);

        List<Long> ids = Arrays.asList(1L, 2L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(userEntity2));

        userService.deleteUsers(ids);

        verify(userRepository, times(1)).deleteAll(Arrays.asList(userEntity1, userEntity2));
    }

}
