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
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.math.BigDecimal;
import model.ItemVenda;
import model.Produto;

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

            Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .setLenient() // Ignora campos desconhecidos
                .registerTypeAdapter(ItemVenda.class, new TypeAdapter<ItemVenda>() {
                    @Override
                    public void write(JsonWriter out, ItemVenda item) throws IOException {
                        out.beginObject();
                        out.name("id").value(item.getId());
                        out.name("produtoId").value(item.getProduto().getId());
                        out.name("quantidade").value(item.getQuantidade());
                        out.name("valorUnitario").value(item.getValorUnitario());
                        out.name("valorTotal").value(item.getValorTotal());
                        out.endObject();
                    }@Override
                    public ItemVenda read(JsonReader in) throws IOException {
                        in.beginObject();
                        ItemVenda item = new ItemVenda();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "id":
                                    item.setId(in.nextInt());
                                    break;
                                case "produtoId":
                                    Produto produto = new Produto();
                                    produto.setId(in.nextInt());
                                    item.setProduto(produto);
                                    break;
                                case "quantidade":
                                    item.setQuantidade(in.nextInt());
                                    break;
                                case "valorUnitario":
                                    item.setValorUnitario(new BigDecimal(in.nextString()));
                                    break;
                                case "valorTotal":
                                    item.setValorTotal(new BigDecimal(in.nextString()));
                                    break;
                                default:
                                    in.skipValue(); // Ignora campos desconhecidos
                            }
                        }
                        in.endObject();
                        return item;
                    }
                }).create();
            String jsonInput = gson.toJson(dto);
            System.out.println("JSON enviado: " + jsonInput);

            try (OutputStream os = conexao.getOutputStream()) {
                os.write(jsonInput.getBytes());
            }

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
                Venda venda = new Venda(vendaDto);
                return venda;
            } finally {
                conexao.disconnect();
            }
        } catch (Exception e) {
            throw new Exception("Erro ao inserir venda: " + e.getMessage());
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
