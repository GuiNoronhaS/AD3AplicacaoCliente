package br.unisul.trabalho3.socket;

import br.unisul.trabalho3.model.JogosEletronicos;
import java.net.*;
import java.io.*;

/**
 * Classe que faz a conexão com a Aplicação Servidor que ira receber os Objetos
 * enviados pela Aplicação Cliente
 *
 * @author Guilherme Noronha
 */
public class SocketCliente {

    Socket servidor;
    ObjectOutputStream oOS;

    /**
     * Construtor da classe SocketCliente, 
     * inicia a conexão com o Socket Servidor e cria
     * a ObjectOutputStream que ira ser usada para enviar o objeto.
     * 
     */
    public SocketCliente() {
        try {
            servidor = new Socket("localhost", 4444);
            oOS = new ObjectOutputStream(servidor.getOutputStream());
        } catch (IOException io) {
            System.err.println("Problema de IO");
        }
    }
    
    /**
     * Metodo que confere a conexão
     * 
     * @return 
     */
    public boolean conectado() {
        return servidor.isConnected();
    }

    /**
     * Metodo que envia o objeto para a aplicação Servidor
     * 
     * @param objetoEnviar
     * @return 
     */
    public boolean enviarObjeto(JogosEletronicos objetoEnviar) {
        try {
            oOS.writeObject(objetoEnviar);
            return true;
        } catch (IOException io) {
            System.err.println("Problema de IO");
            return false;
        }
    }

    /**
     * Metodo que termina o Socket,
     * esse metodo tambem envia um objeto que quando recebido
     * pelo Servidor ira terminar o Socket do Servidor tambem.
     * 
     */
    public void fecharSocket() {
        try {
            JogosEletronicos objetoTerminarServidor = new JogosEletronicos();
            oOS.writeObject(objetoTerminarServidor);
            oOS.close();
            servidor.close();
        } catch (IOException io) {
            System.err.println("Problema de IO");
        }

    }

}
