package br.com.karineg.IClienteDAO;

	import br.com.karineg.domain.Cliente;
	import java.util.Collection;

	//declara a interface IClienteDAO, as interfaces são usadas para definir um contrato que outras classes podem implementar
	public interface IClienteDAO {
	    
		//A interface define 5 métodos, cada um representando uma operação que pode ser realizada em um objeto cliente
		
	    public Boolean cadastrar(Cliente cliente);
	    
	    public void excluir(Long cpf);
	    
	    public void alterar(Cliente cliente);
	    
	    public Cliente consultar(Long cpf);

	    public Collection<Cliente> buscarTodos();
	}