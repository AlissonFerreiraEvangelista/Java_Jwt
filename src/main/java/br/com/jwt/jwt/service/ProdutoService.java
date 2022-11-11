package br.com.jwt.jwt.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jwt.jwt.entity.ProdutoEntity;
import br.com.jwt.jwt.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    ProdutoRepository produtoRepository;

    public List<ProdutoEntity> getProdutoEntities(){
       return produtoRepository.findAll();
    }

    public Optional<ProdutoEntity> getProdutoEntity(UUID uuid){
        return produtoRepository.findById(uuid);
    }

    @Transactional
    public ProdutoEntity saveProdutoEntity(ProdutoEntity entity){
        return produtoRepository.save(entity);
    }

    @Transactional
    public ProdutoEntity updateProdutoEntity(ProdutoEntity entity){
        return produtoRepository.saveAndFlush(entity);
    }

    @Transactional
    public void delProdutoEntity(ProdutoEntity produtoEntity){  
        produtoRepository.delete(produtoEntity);    
    }
}
