package br.com.dio.laboratorio_padroes_de_projeto_com_spring.service.impl;

import br.com.dio.laboratorio_padroes_de_projeto_com_spring.model.Cliente;
import br.com.dio.laboratorio_padroes_de_projeto_com_spring.model.ClienteRepository;
import br.com.dio.laboratorio_padroes_de_projeto_com_spring.model.Endereco;
import br.com.dio.laboratorio_padroes_de_projeto_com_spring.model.EnderecoRepository;
import br.com.dio.laboratorio_padroes_de_projeto_com_spring.service.ClienteService;
import br.com.dio.laboratorio_padroes_de_projeto_com_spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    // TODO Singleton - Injetar os componentes com @Autowired
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    // TODO Strategy - Implementar os métodos definidos na interface
    // TODO Facade - Abstrair integrações com subsistemas, provendo uma interface simples



    @Override
    public Iterable<Cliente> buscarTodos() {

    return clienteRepository.findAll();}

    @Override
    public Cliente buscarPorId(Long id) {
        //TODO: caso o cliente não seja encontrado, deve ser lançada uma exception e retornar nulo
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }
    @Override
    public void inserir(Cliente cliente) {
        SalvarClienteComCep(cliente);

    }

    private void SalvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteDb = clienteRepository.findById(id);
        if (clienteDb.isPresent()) {
            SalvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }
}
