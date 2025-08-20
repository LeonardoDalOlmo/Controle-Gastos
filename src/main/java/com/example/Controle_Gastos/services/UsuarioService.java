package com.example.Controle_Gastos.services;

import com.example.Controle_Gastos.dto.TransacaoDTO;
import com.example.Controle_Gastos.dto.UsuarioDTO;
import com.example.Controle_Gastos.entities.Transacao;
import com.example.Controle_Gastos.entities.Usuario;
import com.example.Controle_Gastos.repositories.TransacaoRepository;
import com.example.Controle_Gastos.repositories.UsuarioRepository;
import com.example.Controle_Gastos.services.Exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public UsuarioDTO findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    @Transactional
    public UsuarioDTO inserirUsuario(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        usuarioRepository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional
    public void deletarUsuario(Integer id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
    }

    @Transactional
    public TransacaoDTO inserirTransacao(Integer transacaoId, Integer usuarioId) {
        try {
            var usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

            var transacao = transacaoRepository.findById(transacaoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada"));

            usuario.addTransacao(transacao);
            usuarioRepository.save(usuario);
            return new TransacaoDTO(transacao);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deletarTransacao(Integer transacaoId, Integer usuarioId) {
        try {
            var usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

            var transacao = transacaoRepository.findById(transacaoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada"));

            usuario.removeTransacao(transacao);
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        for(TransacaoDTO tranDTO: dto.getTranscacoes()){
            Transacao transacao = new Transacao();
            copyDtoToEntity(tranDTO, transacao);
            entity.addTransacao(transacao);
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
