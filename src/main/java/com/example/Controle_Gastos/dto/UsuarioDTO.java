package com.example.Controle_Gastos.dto;

import com.example.Controle_Gastos.entities.Usuario;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private Long id;

    @NotNull(message = "O nome do usuário não pode ser nulo")
    private String nome;

    private List<TransacaoDTO> transcacoes = new ArrayList<>();

    public UsuarioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UsuarioDTO(Usuario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<TransacaoDTO> getTranscacoes() {
        return transcacoes;
    }
}
