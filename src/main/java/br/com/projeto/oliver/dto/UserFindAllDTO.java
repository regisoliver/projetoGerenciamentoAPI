package br.com.projeto.oliver.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserFindAllDTO {
    private Long id;
    private String codigoExterno;
    private String nomeCompleto;
    private String cpfCnpj;
    private String telefone;
    private boolean assinatura;
    private boolean status;
}
