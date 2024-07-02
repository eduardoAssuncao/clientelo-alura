package br.com.alura.clientelo.command;

import br.com.alura.clientelo.domain.Pedido;

public interface ProcessamentoDeArquivo {

    Pedido[] processaArquivo(String nomeDoArquivo);
}
