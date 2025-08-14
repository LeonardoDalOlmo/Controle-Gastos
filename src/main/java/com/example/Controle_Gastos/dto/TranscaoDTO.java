package com.example.Controle_Gastos.dto;

import com.example.Controle_Gastos.entities.Transacao;
import com.example.Controle_Gastos.enums.TipoTransacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class TranscaoDTO {

    private Long id;

    @NotNull(message = "O nome da transação não pode ser nulo")
    private String nome;

    private String descricao;

    @NotNull
    @Positive
    private Double valor;

    private TipoTransacao tipoTransacao;

    private LocalDate data;

    public TranscaoDTO(Long id, String nome, String descricao, Double valor, TipoTransacao tipoTransacao, LocalDate data) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
        this.data = data;
    }

    public TranscaoDTO(Transacao entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.tipoTransacao = entity.getTipoTransacao();
        this.data = entity.getData();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public LocalDate getData() {
        return data;
    }
}
