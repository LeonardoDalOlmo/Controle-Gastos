package com.example.Controle_Gastos.entities;

import com.example.Controle_Gastos.enums.TipoTransacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Double valor;

    private TipoTransacao tipoTransacao;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Transacao() {
    }

    public Transacao(Long id, String nome, String descricao, Double valor, TipoTransacao tipoTransacao, LocalDate data) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
