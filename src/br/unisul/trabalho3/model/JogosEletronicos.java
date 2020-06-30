package br.unisul.trabalho3.model;

import java.io.Serializable;

/**
 * Classe base do Registro de Jogos Eletronicos
 * 
 * @author Guilherme Noronha
 */
public class JogosEletronicos implements Serializable {

    /**
     * int id - Atributo Chave 4 bytes String nome - Limite de 32 caracteres, 64
     * bytes String nome - Limite de 18 caracteres, 36 bytes String nome -
     * Limite de 16 caracteres, 32 bytes int quantidade_de_jogadores total 140
     * bytes por linha
     */
    private int id;
    private String nome; //32
    private String plataforma; //18
    private String tipo; //16
    private int quantidade_de_jogadores;

    /**
     * Construtor sem parametros
     */
    public JogosEletronicos() {
        id = 0;
        nome = "Vazio";
        plataforma = "Vazio";
        tipo = "Vazio";
        quantidade_de_jogadores = 0;
    }

    /**
     * Construtor com os parametros
     *
     * @param idNovo - id que sera registrado o Jogo
     * @param nomeNovo - nome do jogo a ser registrado
     * @param plataformaNovo - plataformas que o jogo se encontra, limitado para
     * PC, Xbox One e PS4
     * @param tipoNovo - Tipo do Jogo registrado
     * @param jogadoresNovo - Numero maximo de jogadores em uma partida, minimo
     * de 1 jogador
     */
    public JogosEletronicos(int idNovo, String nomeNovo, String plataformaNovo, String tipoNovo, int jogadoresNovo) {
        id = idNovo;
        nome = nomeNovo;
        plataforma = plataformaNovo;
        tipo = tipoNovo;
        quantidade_de_jogadores = jogadoresNovo;
    }

    /**
     * Metodo get para o ID
     *
     * @return int ID
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo set para o ID
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo get para o Nome
     *
     * @return String Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo set para o Nome
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo get para a Plataforma
     *
     * @return String Plataforma
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Metodo set para a Plataforma
     *
     * @param plataforma
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * Metodo get para o Tipo
     *
     * @return Tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo set para o Tipo
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo get para a Quantidade de Jogadores
     *
     * @return quantidade_de_jogadores
     */
    public int getQuantidade_de_jogadores() {
        return quantidade_de_jogadores;
    }

    /**
     * Metodo set para a Quantidade de Jogadores
     *
     * @param quantidade_de_jogadores
     */
    public void setQuantidade_de_jogadores(int quantidade_de_jogadores) {
        this.quantidade_de_jogadores = quantidade_de_jogadores;

    }

    /**
     * Metodo que retorna uma String com todos os dados do registro
     * @return String com os Dados do Registro
     */
    public String showDados() {
        return "Id: " + getId() + " Nome: " + getNome() + " Plataformas: " + getPlataforma() + " Tipo: " + getTipo() + " Quantidade de Jogadores: " + getQuantidade_de_jogadores() + "\n";
    }
}
