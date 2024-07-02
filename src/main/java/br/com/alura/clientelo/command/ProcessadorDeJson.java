package br.com.alura.clientelo.command;

import br.com.alura.clientelo.domain.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProcessadorDeJson implements ProcessamentoDeArquivo{

    @Override
    public Pedido[] processaArquivo(String nomeDoArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(formatter);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, localDateDeserializer);
        objectMapper.registerModule(javaTimeModule);

        try {
            URL recursoJSON = getClass().getClassLoader().getResource(nomeDoArquivo);
            if (recursoJSON == null) {
                throw new RuntimeException(String.format("Arquivo %s não localizado!", nomeDoArquivo));
            }

            InputStream inputStream = recursoJSON.openStream();

            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, Pedido.class);

            List<Pedido> pedidosList = objectMapper.readValue(inputStream, listType);

            Pedido[] pedidosArray = new Pedido[pedidosList.size()];
            return pedidosList.toArray(pedidosArray);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo JSON!", e);
        }
    }
}
