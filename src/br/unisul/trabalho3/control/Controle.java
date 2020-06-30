package br.unisul.trabalho3.control;

import br.unisul.trabalho3.model.JogosEletronicos;
import br.unisul.trabalho3.socket.SocketCliente;
import br.unisul.trabalho3.view.Tela;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Classe que realiza o controle da Interface e o Socket, necessarios para a
 * criação e envio do Objeto.
 *
 * Realiza os metodos que ira manipular o Objeto.
 *
 * @author Guilherme Noronha
 */
public class Controle {

    private SocketCliente conexao;
    private JogosEletronicos objeto;
    private ArrayList<Integer> enviados;
    private Tela tela;

    /**
     * Construtor da Interface Cria todos os eventos necessarios para o Trabalho
     * funcionar alem de criar o Socket que conecta com o Servidor
     */
    public Controle() {
        this.enviados = new ArrayList<>();
        setConexao(new SocketCliente());
        setTela(new Tela());

        getTela().jMenuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fechar_action(e);
            }
        });
        getTela().jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarObjeto_action(e);
            }
        });
        getTela().jMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarObjeto_action(e);
            }
        });

    }

    /**
     * Metodo que inicia a Interface na "Tela Principal" e realiza o teste da
     * conexão com o servidor
     */
    public void execute() {
        getTela().setVisible(true);
        if (conexao.conectado()) {
            getTela().jLabel18.setText("Servidor: Conectado!");
        } else {
            getTela().jLabel18.setText("Servidor: Erro Desconectado!");
        }
    }

    /**
     * Metodo GET
     *
     * @return
     */
    public SocketCliente getManipulador() {
        return conexao;
    }

    /**
     * Metodo SET
     *
     * @param manipulador
     */
    public void setConexao(SocketCliente socket) {
        this.conexao = socket;
    }

    /**
     * Metodo GET
     *
     * @return
     */
    public Tela getTela() {
        return tela;
    }

    /**
     * Metodo SET
     *
     * @param tela
     */
    public void setTela(Tela tela) {
        this.tela = tela;
    }

    /**
     * Metodo GET
     *
     * @return
     */
    public JogosEletronicos getObjeto() {
        return objeto;
    }

    /**
     * Metodo SET
     *
     * @param objeto
     */
    public void setObjeto(JogosEletronicos objeto) {
        this.objeto = objeto;
    }

    /**
     * Metodo GET
     *
     * @return
     */
    public ArrayList<Integer> getEnviados() {
        return enviados;
    }
    
    /**
     * Metodo SET
     * 
     * @param enviados 
     */
    public void setEnviados(ArrayList<Integer> enviados) {
        this.enviados = enviados;
    }

    /**
     * Metodo para fechar e sair do programa pela "Tela Principal"
     * Fecha tambem a conexão do Socket
     *
     * @param e
     */
    void Fechar_action(ActionEvent e) {
        conexao.fecharSocket();
        System.exit(0);
    }

    /**
     * Metodo que pega todos dados fornecidos na Tela e cria o
     * Objeto que sera enviado para o servido. 
     * Faz o tratamento do atributo ID para conferir se não
     * esta repetido, conferindo a lista de enviados.
     *
     * @param e
     */
    void criarObjeto_action(ActionEvent e) {
        int nID = (Integer) getTela().jSpinner2.getValue();
        String nNome = getTela().jTextField1.getText();
        nNome = nNome.trim();
        boolean pc = getTela().jCheckBox1.isSelected();
        boolean xbox = getTela().jCheckBox2.isSelected();
        boolean ps4 = getTela().jCheckBox3.isSelected();
        String nPlataforma = "Vazio";
        if (pc && xbox && ps4) {
            nPlataforma = "PC, Xbox One, PS4";
        } else if (pc && xbox) {
            nPlataforma = "PC, Xbox One";
        } else if (pc && ps4) {
            nPlataforma = "PC, PS4";
        } else if (xbox && ps4) {
            nPlataforma = "Xbox One, PS4";
        } else if (pc) {
            nPlataforma = "PC";
        } else if (xbox) {
            nPlataforma = "Xbox One";
        } else if (ps4) {
            nPlataforma = "PS4";
        }
        String nTipo = getTela().jTextField2.getText();
        int nQuantia = (Integer) getTela().jSpinner1.getValue();
        boolean resultado;
        if (nNome.isEmpty()) {
            resultado = false;
        } else if (nID == 0) {
            resultado = false;
        } else {
            if (enviados.contains(nID)) {
                resultado = false;
            } else {
                JogosEletronicos objetoAdd = new JogosEletronicos(nID, nNome, nPlataforma, nTipo, nQuantia);
                setObjeto(objetoAdd);
                resultado = true;
            }
        }
        if (resultado == true) {
            getTela().jLabel11.setText("Resultado: Objeto foi criado.");
            getTela().jLabel12.setText("ID: " + getObjeto().getId());
            getTela().jLabel13.setText("Nome: " + getObjeto().getNome());
            getTela().jLabel14.setText("Plataforma: " + getObjeto().getPlataforma());
            getTela().jLabel15.setText("Tipo: " + getObjeto().getTipo());
            getTela().jLabel16.setText("Quantidade de Jogadores: " + getObjeto().getQuantidade_de_jogadores());
        } else {
            getTela().jLabel11.setText("Resultado: ERRO! Objeto não foi criado, ID ja enviado.");
            getTela().jLabel12.setText("ID: ");
            getTela().jLabel13.setText("Nome: ");
            getTela().jLabel14.setText("Plataforma: ");
            getTela().jLabel15.setText("Tipo: ");
            getTela().jLabel16.setText("Quantidade de Jogadores: ");
        }
    }

    /**
     * Metodo que ira enviar o Objeto criado usando o Socket Cliente,
     * enviando o Objeto para o Socket Servidor, na aplicação do Servidor.
     * Esse metodo tambem atualiza a lista de IDs enviados, para poder controlar
     * os IDs.
     * 
     * @param e 
     */
    void enviarObjeto_action(ActionEvent e) {
        if (enviados.contains(getObjeto().getId())) {
            getTela().jLabel11.setText("Resultado: ERRO! Objeto com esse ID ja foi enviado");
            getTela().jLabel12.setText("ID: ");
            getTela().jLabel13.setText("Nome: ");
            getTela().jLabel14.setText("Plataforma: ");
            getTela().jLabel15.setText("Tipo: ");
            getTela().jLabel16.setText("Quantidade de Jogadores: ");
        } else {
            conexao.enviarObjeto(getObjeto());
            boolean adicionado = enviados.add(getObjeto().getId());
            getTela().jLabel11.setText("Resultado: Objeto a seguir foi enviado.");
            getTela().jLabel12.setText("ID: " + getObjeto().getId());
            getTela().jLabel13.setText("Nome: " + getObjeto().getNome());
            getTela().jLabel14.setText("Plataforma: " + getObjeto().getPlataforma());
            getTela().jLabel15.setText("Tipo: " + getObjeto().getTipo());
            getTela().jLabel16.setText("Quantidade de Jogadores: " + getObjeto().getQuantidade_de_jogadores());
        }
    }

}
