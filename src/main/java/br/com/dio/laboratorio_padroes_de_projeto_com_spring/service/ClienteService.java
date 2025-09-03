package br.com.dio.laboratorio_padroes_de_projeto_com_spring.service;

import br.com.dio.laboratorio_padroes_de_projeto_com_spring.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);


}
