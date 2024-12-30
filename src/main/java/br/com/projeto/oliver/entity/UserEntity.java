package br.com.projeto.oliver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "userentity", schema = "gerenciamento")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_externo", nullable = false, length = 50)
    private String codigoExterno;
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "cpf_cnpj", nullable = false, length = 20, unique = true)
    private String cpfCnpj;
    @Column(name = "sexo", length = 10)
    private String sexo;
    @Column(name = "civil", length = 10)
    private String civil;
    @Column(name = "conjuge", length = 100)
    private String conjuge;
    @Column(name = "doc_identificacao", length = 50)
    private String docIdentificacao;
    @Column(name = "observacao", length = 255)
    private String observacao;
    @Column(name = "condicao_pessoal", length = 255)
    private String condicaoPessoal;
    @Column(name = "codigo_telefone", length = 5)
    private String codigoTelefone;
    @Column(name = "telefone", length = 20)
    @Pattern(regexp = "^[0-9]*$", message = "O telefone deve conter apenas números")
    private String telefone;
    @Column(name = "codigo_celular", length = 5)
    private String codigoCelular;
    @Column(name = "telefone_celular")
    @Pattern(regexp = "^[0-9]*$", message = "O celular deve conter apenas números")
    private String telefoneCelular;
    @Column(name = "validade_ficha")
    private LocalDate validadeFicha;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "habilitar_sms")
    private boolean habilitarEnvioSms;
    @Column(name = "ativo")
    private boolean ativo;
    @Column(name = "pessoa_exposta")
    private boolean pessoaExposta;
    @Column(name = "assinatura")
    private boolean assinatura;
    @Column(name = "status")
    private boolean status;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
