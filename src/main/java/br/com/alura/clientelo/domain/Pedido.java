package br.com.alura.clientelo.domain;

import br.com.alura.clientelo.util.RelatorioSintetico;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    @CsvBindByName(column = "Categoria")
    private String categoria;

    @CsvBindByName(column = "Produto")
    private String produto;

    @CsvBindByName(column = "Cliente")
    private String cliente;

    @CsvBindByName(column = "Preco")
    private BigDecimal preco;

    @CsvBindByName(column = "Quantidade")
    private int quantidade;

    @CsvDate("dd/MM/yyyy")
    @CsvBindByName(column = "Data")
    private LocalDate data;

    public Pedido(){}

    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isMaisBaratoQue(Pedido outroPedido) {
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico();
        return outroPedido == null || relatorioSintetico.pedidoMaisBarato(outroPedido, this) < 0;
    }

    public boolean isMaisCaroQue(Pedido outroPedido) {
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico();
        return outroPedido == null || relatorioSintetico.pedidoMaisCaro(outroPedido, this) > 0;
    }

    public BigDecimal getValorTotal() {
        return this.getPreco().multiply(new BigDecimal(this.getQuantidade()));
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
