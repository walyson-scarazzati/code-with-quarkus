package com.github.viniciusfcf;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @GET
    public List<Produto> buscarTodosProdutos() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void buscarTodosProdutos(CadastrarProdutoDTO dto) {
        Produto p = new Produto();
        p.nome = dto.nome;
        p.valor = dto.valor;
        p.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void buscarTodosProdutos(@PathParam("id") Long id, CadastrarProdutoDTO dto) {

        Optional<Produto> produtoOp = Produto.findByIdOptional(id);

        if (produtoOp.isPresent()) {
            Produto produto = produtoOp.get();
            produto.nome = dto.nome;
            produto.valor = dto.valor;
            produto.persist();
        } else {
            throw new NotFoundException();
        }

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void buscarTodosProdutos(@PathParam("id") Long id) {

        Optional<Produto> produtoOp = Produto.findByIdOptional(id);

        produtoOp.ifPresentOrElse(Produto::delete, () -> {
            throw new NotFoundException();
        });

    }

}
