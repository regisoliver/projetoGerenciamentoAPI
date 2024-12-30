package br.com.projeto.oliver.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {
    private String codigoExterno;
    private String nomeCompleto;
    private String email;
    private String cpfCnpj;
    private String cpfNaoSeAplica;
    private String sexo;
    private String civil;
    private String conjuge;
    private String docIdentificacao;
    private String observacao;
    private String condicaoPessoal;
    private String codigoTelefone;
    private String telefone;
    private String codigoCelular;
    private String telefoneCelular;
    private LocalDate validadeFicha;
    private String validadeNaoSeAplica;
    private LocalDate dataNascimento;
    private String habilitarEnvioSms;
    private String ativo;
    private String pessoaExposta;
    private String assinatura;
}
