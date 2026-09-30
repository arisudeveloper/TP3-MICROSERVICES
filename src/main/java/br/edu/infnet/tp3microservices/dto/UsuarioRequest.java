package br.edu.infnet.tp3microservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank(message = "nome e obrigatorio")
    private String nome;

    @NotBlank(message = "email e obrigatorio")
    @Email(message = "email invalido")
    private String email;

    @NotBlank(message = "senha e obrigatoria")
    @Size(min = 6, message = "senha deve ter no minimo 6 caracteres")
    private String senha;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}