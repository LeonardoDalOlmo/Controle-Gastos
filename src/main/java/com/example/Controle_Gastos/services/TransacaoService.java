package com.example.Controle_Gastos.services;

import com.example.Controle_Gastos.dto.TransacaoDTO;
import com.example.Controle_Gastos.entities.Transacao;
import com.example.Controle_Gastos.repositories.TransacaoRepository;
import com.example.Controle_Gastos.services.Exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public TransacaoDTO findById(Integer id) {
        Transacao transacao = transacaoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Transação não encontrada"));
        return new TransacaoDTO(transacao);
    }

    @Transactional
    public List<TransacaoDTO> findAll() {
        List<Transacao> transacoes = transacaoRepository.findAll();
        return transacoes.stream().map(x -> new TransacaoDTO(x)).toList();
    }

    public TransacaoDTO inserirTransacao(TransacaoDTO transacaoDTO) {
        Transacao entity = new Transacao();
        copyDtoToEntity(transacaoDTO, entity);
        transacaoRepository.save(entity);
        return new TransacaoDTO(entity);
    }

    public TransacaoDTO atualizarTransacao(Integer id, TransacaoDTO dto) {
        try{
            Transacao transacao = transacaoRepository.getReferenceById(dto.getId());
            copyDtoToEntity(dto, transacao);
            transacao = transacaoRepository.save(transacao);
            return new TransacaoDTO(transacao);
        }
        catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Transação não encontrada");
        }
    }

    public void deletarTransacao(Integer id) {
        try {
            transacaoRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Transação não encontrada");
        }
    }

    private static void copyDtoToEntity(TransacaoDTO transacaoDTO, Transacao entity) {
        entity.setNome(transacaoDTO.getNome());
        entity.setDescricao(transacaoDTO.getDescricao());
        entity.setValor(transacaoDTO.getValor());
        entity.setTipoTransacao(transacaoDTO.getTipoTransacao());
        entity.setData(transacaoDTO.getData());
    }

}
