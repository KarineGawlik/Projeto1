package br.com.karineg.domain;

//essa importação vai ser usada para comparação e cálcilo de hash (processo de transformar dados, como ex. objeto ou uma string em um valor numérico fixo, chamado código hash
import java.util.Objects;

public class Cliente {
    
	//criando os atributos da classe cliente, privados, quer dizer que só podem ser acessados diretamente de dentro da prórpia classe 
    private String nome;
    private Long cpf;
    private Long tel;
    private String end;
    private Integer numero;
    private String cidade;
    private String estado;

    //construtor da classe cliente inicia os atributos com os valores passados como argumentos. Converte strings de entrada em tipos númericos apropriados (long e integer) e trim remove espaçoes em branco
    public Cliente(String nome, String cpf, String tel, String end, String num, String cidade, String estado) {
        this.nome = nome;
        this.cpf = Long.valueOf(cpf.trim());
        this.tel = Long.valueOf(tel.trim());
        this.end = end;
        this.numero = Integer.valueOf(num.trim());
        this.cidade = cidade;
        this.estado = estado;
        
    }

    //metodos getters e setters, servem para acessar e modidifcar os atributos da classe
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //método hashcode, calcula um código hash para a instância da classe, ajuda a organizar e acessar instâncias de Cliente em coleção que usam tabelas hash. Gera um valor númerico único (código hash), para a instância de cliente baseado no atributo CPF 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    //método equals, verifica se dois objetos Cliente são iguais, baseado no cpf 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    //método toString, fornece uma representação em string da instancia da classe cliente 
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf=" + cpf +
                '}';
    }


    
}