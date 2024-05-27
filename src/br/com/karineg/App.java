package br.com.karineg;

import br.com.karineg.IClienteDAO.ClienteSetDAO;
import br.com.karineg.IClienteDAO.IClienteDAO;
import br.com.karineg.domain.Cliente;

//O import javax.swing.*; é usado para importar todas as classes do pacote javax.swing, que contém classes para criação de interfaces gráficas em Java. Isso inclui componentes como botões, caixas de diálogo, painéis, entre outros, que são úteis para a criação de interfaces de usuário em aplicações desktop.
import javax.swing.*;

//Define App como classe principal que contém o método main
public class App {

	//Atributo ICliente DAO, interface para o Data Access Object (DAO) que será usada para operações de CRUD (create, read, update, delete) com a coleção de clientes.
    private static IClienteDAO iClienteDAO;

    //Método Main, ponto de entrada do programa
    public static void main(String args[]) {
    	//Inicializa IClienteDAO como um implementação baseada em HashSet
    	//A instanciação é o processo de criação de um objeto a partir de uma classe. Quando você instancia uma classe, está criando uma cópia dessa classe que pode ser manipulada e utilizada no seu programa. No trecho de código fornecido, new ClienteSetDAO() cria uma nova instância da classe ClienteSetDAO, ou seja, um novo objeto ClienteSetDAO é criado e atribuído à variável iClienteDAO.
    	//Implementação, por sua vez, refere-se à maneira como uma classe concretiza ou realiza uma determinada interface ou comportamento. No contexto do código fornecido, ClienteSetDAO e IClienteDAO são interfaces e classes concretas que implementam essas interfaces fornecendo a implementação real dos métodos definidos nas interfaces. Dessa forma, ClienteSetDAO é uma implementação específica de IClienteDAO, onde os métodos definidos em IClienteDAO são implementados de acordo com as necessidades da classe ClienteSetDAO.
        iClienteDAO = new ClienteSetDAO();

        //Primeira entrada do usuário, solicita escolher uma operação usando uma caixa de diálogo
        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        
        //validação da primeira opção, verfica se a opção fornecida pelo usuário é válida. Se não for, solicita novamente até que seja válida
        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida digite 1 para cadastro, 2 para consulta, 3 para cadastro, 4 para alteração ou 5 para sair",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        }

        //Loop principal para operações de CRUD
        //Continua executando enquanto a opção for válida, compara a opção e chama o método correspondente (cadastrar, consultar, excluir, atualizar).
        //Solicita uma nova opção após cada operação
        while (isOpcaoValida(opcao)) { // Enquanto a opção digitada for válida

            if (isOpcaoSair(opcao)) { // Se a opção for para sair
                sair(); // Chama o método sair()
            } else if (isCadastro(opcao)) { // Se a opção for para cadastrar
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vígula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados); // Chama o método cadastrar() passando os dados digitados
            } else if (isConsulta(opcao)) { // Se a opção for para consultar
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados); // Chama o método consultar() passando o CPF digitado
            } else if (isExclusao(opcao)) { // Se a opção for para excluir
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados); // Chama o método excluir() passando o CPF digitado
            } else { // Se a opção for para atualizar
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vígula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                        "Atualização", JOptionPane.INFORMATION_MESSAGE);
                atualizar(dados); // Chama o método atualizar() passando os dados digitados
            }

            // Pede ao usuário para digitar novamente a opção
            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consulta, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private static void atualizar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        iClienteDAO.alterar(cliente);
    }

    private static void excluir(String dados) {
        iClienteDAO.excluir(Long.parseLong(dados));
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso: ", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
    }


    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado com sucesso: " + cliente.toString(), "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "ERRO",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso ", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean isExclusao(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isConsulta(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void sair() {
        String clientesCadastrados = "";
        for (Cliente cliente : iClienteDAO.buscarTodos()) {
            clientesCadastrados += cliente.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, "Clientes cadastrados: " + clientesCadastrados, "Até logo",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao)
                || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

}