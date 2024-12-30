package br.com.projeto.oliver.controller;

import br.com.projeto.oliver.dto.UserDTO;
import br.com.projeto.oliver.dto.UserFindAllDTO;
import br.com.projeto.oliver.entity.UserEntity;
import br.com.projeto.oliver.service.UserServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Gerenciamento de Usuário", description = "Responsável pelo gerenciamento de todos os usuários")
@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @ApiOperation(value = "Busca os usuários com paginação", response = UserFindAllDTO.class)
    @GetMapping
    public Page<UserFindAllDTO> getAllUsers(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return userService.getAllUsers(PageRequest.of(pageNumber, pageSize));
    }

    @ApiOperation(value = "Busca usuário pelo ID", response = UserEntity.class)
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "Cria um novo usuário", response = UserEntity.class)
    @PostMapping
    public UserEntity createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @ApiOperation(value = "Atualiza usuário pelo ID", response = UserEntity.class)
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @ApiOperation(value = "Deleta usuários pelos IDs")
    @DeleteMapping("/delete")
    public void deleteUsers(@RequestBody List<Long> ids) {
        userService.deleteUsers(ids);
    }

}
