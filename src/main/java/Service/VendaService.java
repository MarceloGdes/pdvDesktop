/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import com.google.gson.Gson;
import dto.VendaDto;
import exceptions.ApiRequestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.ItemVenda;
import model.Produto;
import model.Cliente;

import model.Venda;

/**
 *
 * @author rgmac
 */
public class VendaService {

    private static String BASE_URL = "http://localhost:8080/vendas";
    private static int SUCESSO = 200;
    private static int CREATED = 201;
    private static int NOT_FOUND = 404;
    private static int BAD_REQUEST = 400;

    public static Venda insert(VendaDto dto) throws Exception {
    try {
        URL url = new URL(BASE_URL);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("POST");
        conexao.setRequestProperty("Content-Type", "application/json");
        conexao.setDoOutput(true);

        // Constrói um GsonBuilder
        GsonBuilder gsonBuilder = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .setLenient();

        // Adiciona o adapter de ItemVenda
        gsonBuilder.registerTypeAdapter(ItemVenda.class, new TypeAdapter<ItemVenda>() {
            @Override
            public void write(JsonWriter out, ItemVenda item) throws IOException {
                out.beginObject();
                out.name("id").value(item.getId());
                out.name("produtoId").value(item.getProduto().getId());
                out.name("quantidade").value(item.getQuantidade());
                out.name("valorUnitario").value(item.getValorUnitario());
                out.name("valorTotal").value(item.getValorTotal());
                out.endObject();
            }

            @Override
            public ItemVenda read(JsonReader in) throws IOException {
                in.beginObject();
                ItemVenda item = new ItemVenda();
                Produto produto = new Produto();
                while (in.hasNext()) {
                    String name = in.nextName();
                    switch (name) {
                        case "id": item.setId(in.nextInt()); break;
                        case "produtoId": produto.setId(in.nextInt()); item.setProduto(produto); break;
                        case "quantidade": item.setQuantidade(in.nextInt()); break;
                        case "valorUnitario": item.setValorUnitario(new BigDecimal(in.nextString())); break;
                        case "valorTotal": item.setValorTotal(new BigDecimal(in.nextString())); break;
                        case "produto":
                            in.beginObject();
                            while (in.hasNext()) {
                                String field = in.nextName();
                                switch (field) {
                                    case "id": produto.setId(in.nextInt()); break;
                                    case "descricao": produto.setDescricao(in.nextString()); break;
                                    case "valor": produto.setValor(new BigDecimal(in.nextString())); break;
                                    case "categoria": produto.setCategoria(in.nextString()); break;
                                    default: in.skipValue();
                                }
                            }
                            in.endObject();
                            item.setProduto(produto);
                            break;
                        default: in.skipValue();
                    }
                }
                in.endObject();
                return item;
            }
        });

        // Adiciona o adapter de VendaDto depois
        gsonBuilder.registerTypeAdapter(VendaDto.class, new TypeAdapter<VendaDto>() {
            @Override
            public void write(JsonWriter out, VendaDto dto) throws IOException {
                out.beginObject();
                out.name("observacao").value(dto.getObservacao());
                out.name("data").value(dto.getData() != null ? new SimpleDateFormat("yyyy-MM-dd").format(dto.getData()) : null);
                out.name("total").value(dto.getTotal());
                out.name("clienteId").value(dto.getClienteId());

                out.name("itensVenda");
                out.beginArray();
                for (ItemVenda item : dto.getItensVenda()) {
                    gsonBuilder.create().getAdapter(ItemVenda.class).write(out, item);
                }
                out.endArray();

                out.endObject();
            }

            @Override
            public VendaDto read(JsonReader in) throws IOException {
                in.beginObject();
                VendaDto dto = new VendaDto();
                Cliente cliente = new Cliente();
                while (in.hasNext()) {
                    String name = in.nextName();
                    switch (name) {
                        case "id": dto.setId(in.nextInt()); break;
                        case "observacao": dto.setObservacao(in.nextString()); break;
                        case "data": dto.setData(Date.valueOf(in.nextString().substring(0, 10))); break;
                        case "total": dto.setTotal(new BigDecimal(in.nextString())); break;
                        case "clienteId": dto.setClienteId(in.nextInt()); break;
                        case "cliente":
                            in.beginObject();
                            while (in.hasNext()) {
                                String field = in.nextName();
                                switch (field) {
                                    case "id": cliente.setId(in.nextInt()); dto.setClienteId(cliente.getId()); break;
                                    case "nome": cliente.setNome(in.nextString()); break;
                                    case "telefone": cliente.setTelefone(in.nextString()); break;
                                    case "email": cliente.setEmail(in.nextString()); break;
                                    default: in.skipValue();
                                }
                            }
                            in.endObject();
                            ClienteDAO clienteDAO = new ClienteDAO();
                            if (clienteDAO.retornarPeloId(cliente.getId(), "cliente", "id") == null) {
                                clienteDAO.salvar(cliente);
                            }
                            break;
                        case "itensVenda":
                            dto.setItensVenda(gsonBuilder.create().fromJson(in, new TypeToken<List<ItemVenda>>() {}.getType()));
                            break;
                        default: in.skipValue();
                    }
                }
                in.endObject();
                return dto;
            }
        });

        Gson gson = gsonBuilder.create();

        // Envia o JSON
        String jsonInput = gson.toJson(dto);
        System.out.println("JSON enviado: " + jsonInput);
        try (OutputStream os = conexao.getOutputStream()) {
            os.write(jsonInput.getBytes());
        }

        // Verifica resposta
        if (conexao.getResponseCode() != CREATED) {
            String errorResponse = "";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getErrorStream()))) {
                errorResponse = converterJsonString(br);
            }
            throw new RuntimeException("Erro ao conectar: Código " + conexao.getResponseCode() + ", Resposta: " + errorResponse);
        }

        try (BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
            String json = converterJsonString(resposta);
            System.out.println("JSON recebido: " + json);
            VendaDto vendaDto = gson.fromJson(json, VendaDto.class);

            // Monta objeto Venda
            Venda venda = new Venda();
            venda.setId(vendaDto.getId());
            venda.setObservacao(vendaDto.getObservacao());
            venda.setData(vendaDto.getData());
            venda.setTotal(vendaDto.getTotal());

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.retornarPeloId(vendaDto.getClienteId(), "cliente", "id");
            if (cliente == null) {
                cliente = new Cliente();
                cliente.setId(vendaDto.getClienteId());
                clienteDAO.salvar(cliente);
            }
            venda.setCliente(cliente);

            ProdutoDAO produtoDAO = new ProdutoDAO();
            List<ItemVenda> itensVenda = new ArrayList<>();
            for (ItemVenda item : vendaDto.getItensVenda()) {
                Produto produto = produtoDAO.retornarPeloId(item.getProduto().getId(), "produto", "id");
                if (produto == null) {
                    produto = item.getProduto();
                    produtoDAO.salvar(produto);
                }
                item.setProduto(produto);
                item.setVendaId(venda.getId());
                itensVenda.add(item);
            }
            venda.setItensVenda(itensVenda);

            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.salvar(venda);

            return venda;
        } finally {
            conexao.disconnect();
        }
    } catch (Exception e) {
        throw new Exception("Erro ao inserir venda: " + e.getMessage(), e);
    }
}


    public static String converterJsonString(BufferedReader bufferedReader) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        String resposta;
        while ((resposta = bufferedReader.readLine()) != null) {
            jsonString.append(resposta);
        }
        return jsonString.toString();
    }

}
