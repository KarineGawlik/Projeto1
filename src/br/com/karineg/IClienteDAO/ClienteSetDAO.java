package br.com.karineg.IClienteDAO;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.karineg.domain.Cliente;

//Declara a classe ClienteSetDAO e implmenta os métodos do IClienteDAO
public class ClienteSetDAO implements IClienteDAO {
    
	//Atributo da classe declara um conjunto set que armazena instancias de cliente
    private Set<Cliente> set;
    
    //Construtor da classe, inicializa o atributo set como uma instância de HashSet, que é uma implementação de conjunto que não permite duplicatas
    public ClienteSetDAO() {
        this.set = new HashSet<>();
    }

    //Método cadastrar adiciona um novo cliente ao conjunto. Usa o método add do hashSet que retorna true se o cliente foi adicionado 
    @Override
    public Boolean cadastrar(Cliente cliente) {
        return this.set.add(cliente);
    }

    //Método excluir remove um cliente do conjunto com base no cpf. Busca vai iterar o conjunto pra encontrar o cliente com o cpf correspondente, remoção, se o cliente for encontrado ele é removido do conjunto
    @Override
    public void excluir(Long cpf) {
        Cliente clienteEncontrato = null;
        for (Cliente cliente : this.set) {
            if (cliente.getCpf().equals(cpf)) {
                clienteEncontrato = cliente;
                break;
            }
        }
        
        if (clienteEncontrato != null) {
            this.set.remove(clienteEncontrato);
        }
    }
    
    //Altera os dados de um clinete existente, verifica se o clinete já está no conjunto, itera sibre o conjunto para encontrar o clinete corresposndente e atualiza seus atributos
    @Override
    public void alterar(Cliente cliente) {
        if (this.set.contains(cliente)) {
            for (Cliente clienteCadastrado : this.set) {
                if (clienteCadastrado.equals(cliente)) {
                    clienteCadastrado.setNome(cliente.getNome());
                    clienteCadastrado.setTel(cliente.getTel());
                    clienteCadastrado.setNumero(cliente.getNumero());
                    clienteCadastrado.setEnd(cliente.getEnd());
                    clienteCadastrado.setCidade(cliente.getCidade());
                    clienteCadastrado.setEstado(cliente.getEstado());
                    break;
                }
            }
        }
    }

    //Consulta um cliente no conjunto pelo cpf, itera sobre o conjunto para encontrar o cliente com o CPF correspondente, retorna o cliente encontrado ou null se não for encontrado
    @Override
    public Cliente consultar(Long cpf) {
        for (Cliente clienteCadastrado : this.set) {
             if (clienteCadastrado.getCpf().equals(cpf)) {
                 return clienteCadastrado;
             }
        }
        return null;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.set;
    }

}
