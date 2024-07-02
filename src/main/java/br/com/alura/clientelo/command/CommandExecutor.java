package br.com.alura.clientelo.command;

import br.com.alura.clientelo.domain.Pedido;

public class CommandExecutor {

    public Pedido[] executeCommand(ProcessamentoDeArquivo processamentoDeArquivo, String arquivo){
        return processamentoDeArquivo.processaArquivo(arquivo);
    }

}
