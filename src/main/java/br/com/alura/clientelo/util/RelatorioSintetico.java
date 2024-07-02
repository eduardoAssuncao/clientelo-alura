package br.com.alura.clientelo.util;

import br.com.alura.clientelo.domain.Pedido;

import java.math.BigDecimal;

public class RelatorioSintetico {

    public Integer pedidoMaisBarato(Pedido pedidoMaisBarato, Pedido pedidoAtual) {
        return pedidoAtual.getPreco()
                .multiply(new BigDecimal(pedidoAtual.getQuantidade()))
                .compareTo(pedidoMaisBarato.getPreco().multiply(new BigDecimal(
                        pedidoMaisBarato.getQuantidade())));
    }

    public Integer pedidoMaisCaro(Pedido pedidoMaisCaro, Pedido pedidoAtual){
        return pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade()))
                .compareTo(pedidoMaisCaro.getPreco().
                        multiply(new BigDecimal(pedidoMaisCaro.getQuantidade())));
    }
}
