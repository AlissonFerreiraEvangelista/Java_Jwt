package br.com.jwt.jwt.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jwt.jwt.entity.ProdutoEntity;
import br.com.jwt.jwt.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RequestMapping("produto")
@RestController
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;

    @GetMapping("/todos")
    @ApiOperation(value = "Retorna Todos os Produtos - Perfil de User")
    public ResponseEntity<List<ProdutoEntity>> achaTodos(ProdutoEntity entity){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.getProdutoEntities());
    }

    @GetMapping("/um/{id}")
    @ApiOperation(value = "Retorna um os Produto - Perfil de User")
    public ResponseEntity<Object> achaUm(@PathVariable(value = "id") UUID uuid){
        ProdutoEntity produto = produtoService.getProdutoEntity(uuid).get();
        if(produto != null){
            return ResponseEntity.status(HttpStatus.OK).body(produto);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Produto não encontrado");
    }

    @PostMapping("/salva")
    @ApiOperation(value = "Cadastra Produto - Perfil de Admin")
    public ResponseEntity<ProdutoEntity> salva(@RequestBody ProdutoEntity produtoEntity){  
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.saveProdutoEntity(produtoEntity));
    }

    @PutMapping("/atualiza")
    @ApiOperation(value = "Atualiza Produto - Perfil de Admin")
    public ProdutoEntity atualiza(@RequestBody ProdutoEntity produtoEntity){
        ProdutoEntity produto = produtoService.updateProdutoEntity(produtoEntity);
        return produto;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deleta Produto - Perfil de Admin")
    public ResponseEntity<Object> delete(@PathVariable (value = "id") UUID uuid){
        Optional <ProdutoEntity> produto = produtoService.getProdutoEntity(uuid);
        if(produto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }else{
            produtoService.delProdutoEntity(produto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
