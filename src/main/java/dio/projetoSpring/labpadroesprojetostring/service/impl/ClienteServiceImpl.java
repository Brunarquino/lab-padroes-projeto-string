package dio.projetoSpring.labpadroesprojetostring.service.impl;

import dio.projetoSpring.labpadroesprojetostring.model.Cliente;
import dio.projetoSpring.labpadroesprojetostring.model.ClienteRepository;
import dio.projetoSpring.labpadroesprojetostring.model.Endereco;
import dio.projetoSpring.labpadroesprojetostring.model.EnderecoRepository;
import dio.projetoSpring.labpadroesprojetostring.service.ClienteService;
import dio.projetoSpring.labpadroesprojetostring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {


    @Autowired //Singleton: Ingetar os componentes do Spring com @Autowired
    private ClienteRepository clienteRepository;


    @Autowired //Usando o repository de Endereco
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        //Buscar todos os clientes
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        //Buscando pelo ID
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get(); //Retornando cliente
    }

    @Override
    public void inserir(Cliente cliente) {
        /*
        //verificar se o endereço existe
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            //Caso não exista
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return null;
        });
        cliente.setEndereco(endereco);
        //Inserir o cliente
        clienteRepository.save(cliente);
        */
        //chamando o método
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) { //Se o cliente no banco de dados existir
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        //chamar o cliente repository para excluir um registro
        //excluindo pelo Id
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
