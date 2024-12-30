package br.com.projeto.oliver.mapper;

import br.com.projeto.oliver.dto.UserFindAllDTO;
import br.com.projeto.oliver.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserFindAllDTO toFindAllDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return new UserFindAllDTO(
                userEntity.getId(),
                userEntity.getCodigoExterno(),
                userEntity.getNomeCompleto(),
                userEntity.getCpfCnpj(),
                userEntity.getTelefone(),
                userEntity.isAssinatura(),
                userEntity.isStatus()
        );
    }

}
