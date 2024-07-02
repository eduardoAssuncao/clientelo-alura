package br.com.alura.clientelo.command;

import br.com.alura.clientelo.domain.Pedido;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class ProcessadorDeCsv implements ProcessamentoDeArquivo{

    @Override
    public Pedido[] processaArquivo(String nomeDoArquivo) {
        try {
            URL recursoCSV = getClass().getClassLoader().getResource(nomeDoArquivo);
            if (recursoCSV == null) {
                throw new RuntimeException(String.format("Arquivo %s não localizado!", nomeDoArquivo));
            }

            CsvToBean<Pedido> csvToBean = new CsvToBeanBuilder<Pedido>(new InputStreamReader(recursoCSV.openStream()))
                    .withType(Pedido.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Pedido> pedidosList = csvToBean.parse();
            Pedido[] pedidosArray = new Pedido[pedidosList.size()];
            pedidosArray = pedidosList.toArray(pedidosArray);

            return pedidosArray;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo CSV!", e);
        }
    }
}
