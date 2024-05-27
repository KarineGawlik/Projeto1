package br.com.karineg.IClienteDAO;
import br.com.karineg.domain.Cliente;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


//declara a classe ClienteMapDAO e implementa os métodos da interface IClienteDAO 
public class ClienteMapDAO implements IClienteDAO {
    
	//Declara um mapa que mapeia os cpfd para instancia Cliente
    private Map<Long, Cliente> map;
    
    //Construtor da classe, inicializa o atributo map como uma instãncia de TreeMap para manter as chaves ordenadas
    public ClienteMapDAO() {
        map = new TreeMap<>();
    }

    //Adiciona novo cliente ao mapa, verifica e retorna false se o cliente com o mesmo cpf já existe. Se o cpf não existe adiciona o cliente ao mapa e retorna true
    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (map.containsKey(cliente.getCpf())) {
            return false;
        }
        
        map.put(cliente.getCpf(), cliente);
        return true;
    }

    //método excluir, obtém e remove um cliente do mapa com base no cpf
    @Override
    public void excluir(Long cpf) {
        Cliente clienteCadastrado = map.get(cpf);
        map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
    }

    //Método alterar, altera os dados de um cliente existente. Obtém o cliente pelo cpf e atualiza seus atributos 
    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = map.get(cliente.getCpf());
        clienteCadastrado.setNome(cliente.getNome());
        clienteCadastrado.setTel(cliente.getTel());
        clienteCadastrado.setNumero(cliente.getNumero());
        clienteCadastrado.setEnd(cliente.getEnd());
        clienteCadastrado.setCidade(cliente.getCidade());
        clienteCadastrado.setEstado(cliente.getEstado());
    }

    //Faz a consulta no mapa pelo cpf e retorna o cliente correspondente
    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    //Busca todos os clientes cadastrados e retorna uma coleçao contendo todos os clientes do mapa
    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }

}