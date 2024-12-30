package br.com.projeto.oliver.service;

import br.com.projeto.oliver.dto.UserDTO;
import br.com.projeto.oliver.dto.UserFindAllDTO;
import br.com.projeto.oliver.entity.UserEntity;
import br.com.projeto.oliver.exception.ResourceNotFoundException;
import br.com.projeto.oliver.mapper.UserMapper;
import br.com.projeto.oliver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserFindAllDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userEntity -> userMapper.toFindAllDTO(userEntity));
    }

    @Override
    public UserEntity getUserById(Long id) {
        return returnEntityById(id);
    }

    @Override
    public UserEntity createUser(UserDTO userDTO) {
        UserEntity userEntity = mountUser(userDTO);
        userEntity = userRepository.save(userEntity);

        return userEntity;
    }

    @Override
    public UserEntity updateUser(Long id, UserDTO userDTO) {
        UserEntity userEntity = returnEntityById(id);
        userEntity = updateUser(userEntity, userDTO);

        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUsers(List<Long> ids) {
        List<UserEntity> usersToDelete = new ArrayList<>();

        for (Long id : ids) {
            UserEntity userEntity = returnEntityById(id);
            if (userEntity == null) {
                throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
            }
            usersToDelete.add(userEntity);
        }

        userRepository.deleteAll(usersToDelete);
    }


    private UserEntity returnEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    private UserEntity mountUser(UserDTO userDTO) {
        return UserEntity.builder()
                .codigoExterno(userDTO.getCodigoExterno())
                .nomeCompleto(userDTO.getNomeCompleto())
                .email(userDTO.getEmail())
                .cpfCnpj("true".equalsIgnoreCase(userDTO.getCpfNaoSeAplica()) ? null : userDTO.getCpfCnpj())
                .sexo(userDTO.getSexo())
                .civil(userDTO.getCivil())
                .conjuge(userDTO.getConjuge())
                .docIdentificacao(userDTO.getDocIdentificacao())
                .observacao(userDTO.getObservacao())
                .condicaoPessoal(userDTO.getCondicaoPessoal())
                .codigoTelefone(userDTO.getCodigoTelefone())
                .telefone(userDTO.getTelefone())
                .codigoCelular(userDTO.getCodigoCelular())
                .telefoneCelular(userDTO.getTelefoneCelular())
                .validadeFicha("true".equalsIgnoreCase(userDTO.getValidadeNaoSeAplica()) ? null : userDTO.getValidadeFicha())
                .dataNascimento(userDTO.getDataNascimento())
                .habilitarEnvioSms(Boolean.parseBoolean(userDTO.getHabilitarEnvioSms()))
                .ativo(true)
                .pessoaExposta(Boolean.parseBoolean(userDTO.getPessoaExposta()))
                .assinatura(Boolean.parseBoolean(userDTO.getAssinatura()))
                .status(true)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    private UserEntity updateUser(UserEntity userEntity, UserDTO userDTO) {
        return userEntity.toBuilder()
                .codigoExterno(userDTO.getCodigoExterno())
                .nomeCompleto(userDTO.getNomeCompleto())
                .email(userDTO.getEmail())
                .cpfCnpj(userDTO.getCpfCnpj())
                .sexo(userDTO.getSexo())
                .civil(userDTO.getCivil())
                .conjuge(userDTO.getConjuge())
                .docIdentificacao(userDTO.getDocIdentificacao())
                .observacao(userDTO.getObservacao())
                .condicaoPessoal(userDTO.getCondicaoPessoal())
                .codigoTelefone(userDTO.getCodigoTelefone())
                .telefone(userDTO.getTelefone())
                .codigoCelular(userDTO.getCodigoCelular())
                .telefoneCelular(userDTO.getTelefoneCelular())
                .validadeFicha(userDTO.getValidadeFicha())
                .dataNascimento(userDTO.getDataNascimento())
                .habilitarEnvioSms(Boolean.parseBoolean(userDTO.getHabilitarEnvioSms()))
                .pessoaExposta(Boolean.parseBoolean(userDTO.getPessoaExposta()))
                .assinatura(Boolean.parseBoolean(userDTO.getAssinatura()))
                .dataAtualizacao(LocalDateTime.now())
                .build();
    }

}
