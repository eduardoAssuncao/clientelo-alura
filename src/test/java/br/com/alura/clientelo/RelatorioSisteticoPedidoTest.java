package br.com.alura.clientelo;

import br.com.alura.clientelo.command.ProcessadorDeCsv;
import br.com.alura.clientelo.domain.Pedido;
import br.com.alura.clientelo.util.RelatorioSintetico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class RelatorioSisteticoPedidoTest {

    @Test
    public void gerarRelatioComPedido(){
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico();
        Pedido[] pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

        int expectedPedidoRealizado = 16;
        int expectedProdutoVendido = 35;
        int expectedTotalCategorias = 5;
        BigDecimal expectedMontanteDeVendas = new BigDecimal("178374.49");
        Pedido pedidoBaratoTest = pedidos[8];
        Pedido pedidoCaroTest = pedidos[4];

        int totalDeProdutosVendidos = 0;
        int totalDePedidosRealizados = 0;
        BigDecimal montanteDeVendas = BigDecimal.ZERO;
        Pedido pedidoMaisBarato = null;
        Pedido pedidoMaisCaro = null;

        String[] categoriasProcessadas = new String[10];
        int totalDeCategorias = 0;

        for (int i = 0; i < pedidos.length; i++) {
            Pedido pedidoAtual = pedidos[i];

            if (pedidoAtual == null) {
                break;
            }

            if (pedidoMaisBarato == null || relatorioSintetico.pedidoMaisBarato(pedidoMaisBarato, pedidoAtual)  < 0) {
                pedidoMaisBarato = pedidoAtual;
            }

            if (pedidoMaisCaro == null || relatorioSintetico.pedidoMaisCaro(pedidoMaisCaro, pedidoAtual) > 0) {
                pedidoMaisCaro = pedidoAtual;
            }

            montanteDeVendas = montanteDeVendas.add(relatorioSintetico.montanteDeVenda(pedidoAtual));
            totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            totalDePedidosRealizados++;

            boolean jahProcessouCategoria = false;
            for (int j = 0; j < categoriasProcessadas.length; j++) {
                if (pedidoAtual.getCategoria().equalsIgnoreCase(categoriasProcessadas[j])) {
                    jahProcessouCategoria = true;
                }
            }

            if (!jahProcessouCategoria) {
                totalDeCategorias++;

                if (categoriasProcessadas[categoriasProcessadas.length - 1] != null) {
                    categoriasProcessadas = Arrays.copyOf(categoriasProcessadas, categoriasProcessadas.length * 2);
                } else {
                    for (int k = 0; k < categoriasProcessadas.length; k++) {
                        if (categoriasProcessadas[k] == null) {
                            categoriasProcessadas[k] = pedidoAtual.getCategoria();
                            break;
                        }
                    }
                }
            }
        }
        Assertions.assertEquals(expectedTotalCategorias, totalDeCategorias);
        Assertions.assertEquals(expectedPedidoRealizado, totalDePedidosRealizados);
        Assertions.assertEquals(expectedProdutoVendido, totalDeProdutosVendidos);
        Assertions.assertEquals(expectedTotalCategorias, totalDeCategorias);
        Assertions.assertEquals(expectedMontanteDeVendas, montanteDeVendas);
        Assertions.assertEquals(pedidoBaratoTest, pedidoMaisBarato);
        Assertions.assertEquals(pedidoCaroTest, pedidoMaisCaro);
    }

    @Test
    public void gerarRelatorioSemPedido(){
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico();
        Pedido[] pedidos = new Pedido[0];

        int expectedPedidoRealizado = 0;
        int expectedProdutoVendido = 0;
        int expectedTotalCategorias = 0;
        BigDecimal expectedMontanteDeVendas = new BigDecimal("0");
        Pedido pedidoBaratoTest = null;
        Pedido pedidoCaroTest = null;

        int totalDeProdutosVendidos = 0;
        int totalDePedidosRealizados = 0;
        BigDecimal montanteDeVendas = BigDecimal.ZERO;
        Pedido pedidoMaisBarato = null;
        Pedido pedidoMaisCaro = null;

        String[] categoriasProcessadas = new String[10];
        int totalDeCategorias = 0;

        for (int i = 0; i < pedidos.length; i++) {
            Pedido pedidoAtual = pedidos[i];

            if (pedidoAtual == null) {
                break;
            }

            if (pedidoMaisBarato == null || relatorioSintetico.pedidoMaisBarato(pedidoMaisBarato, pedidoAtual)  < 0) {
                pedidoMaisBarato = pedidoAtual;
            }

            if (pedidoMaisCaro == null || relatorioSintetico.pedidoMaisCaro(pedidoMaisCaro, pedidoAtual) > 0) {
                pedidoMaisCaro = pedidoAtual;
            }

            montanteDeVendas = montanteDeVendas.add(relatorioSintetico.montanteDeVenda(pedidoAtual));
            totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            totalDePedidosRealizados++;

            boolean jahProcessouCategoria = false;
            for (int j = 0; j < categoriasProcessadas.length; j++) {
                if (pedidoAtual.getCategoria().equalsIgnoreCase(categoriasProcessadas[j])) {
                    jahProcessouCategoria = true;
                }
            }

            if (!jahProcessouCategoria) {
                totalDeCategorias++;

                if (categoriasProcessadas[categoriasProcessadas.length - 1] != null) {
                    categoriasProcessadas = Arrays.copyOf(categoriasProcessadas, categoriasProcessadas.length * 2);
                } else {
                    for (int k = 0; k < categoriasProcessadas.length; k++) {
                        if (categoriasProcessadas[k] == null) {
                            categoriasProcessadas[k] = pedidoAtual.getCategoria();
                            break;
                        }
                    }
                }
            }
        }
        Assertions.assertEquals(expectedTotalCategorias, totalDeCategorias);
        Assertions.assertEquals(expectedPedidoRealizado, totalDePedidosRealizados);
        Assertions.assertEquals(expectedProdutoVendido, totalDeProdutosVendidos);
        Assertions.assertEquals(expectedTotalCategorias, totalDeCategorias);
        Assertions.assertEquals(expectedMontanteDeVendas, montanteDeVendas);
        Assertions.assertEquals(pedidoBaratoTest, pedidoMaisBarato);
        Assertions.assertEquals(pedidoCaroTest, pedidoMaisCaro);
    }
}
