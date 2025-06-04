/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.ClienteDTO;
import model.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Rafael
 */
public class ClienteService {
    private static String BASE_URL = "http://localhost:8080/clientes";
    private static int SUCESSO = 200;
    private static int CREATED = 201;

    public static List<Cliente> findAll(String nome) throws Exception {
        try {
            String urlString = nome == null || nome.isBlank() ? BASE_URL : BASE_URL + "?nome=" + nome;
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() != SUCESSO) {
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseMessage());
            }

            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = converterJsonString(resposta);
            Gson gson = new Gson();

            ClienteDTO[] clientesDto = gson.fromJson(json, ClienteDTO[].class);
            List<Cliente> listaClientes = Arrays.stream(clientesDto).map(dto -> new Cliente(dto)).collect(Collectors.toList());

            conexao.disconnect();
            return listaClientes;

        } catch (Exception e) {
            throw new Exception("Erro ao retornar clientes: " + e);
        }
    }

    public static Cliente insert(ClienteDTO dto) throws Exception {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);

            Gson gson = new Gson();
            String jsonInput = gson.toJson(dto);
            try (OutputStream os = conexao.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            if (conexao.getResponseCode() != CREATED) {
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseMessage());
            }

            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = converterJsonString(resposta);
            ClienteDTO clienteDto = gson.fromJson(json, ClienteDTO.class);
            Cliente cliente = new Cliente(clienteDto);

            conexao.disconnect();
            return cliente;
        } catch (Exception e) {
            throw new Exception("Erro ao inserir cliente: " + e);
        }
    }

    public static Cliente findById(Integer id) throws Exception {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() != SUCESSO) {
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseMessage());
            }

            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = converterJsonString(resposta);
            Gson gson = new Gson();
            ClienteDTO clienteDto = gson.fromJson(json, ClienteDTO.class);
            
            Cliente cliente = new Cliente(clienteDto);

            conexao.disconnect();
            return cliente;

        } catch (Exception e) {
            throw new Exception("Erro ao buscar cliente: " + e);
        }
    }

    public static Cliente update(Integer id, ClienteDTO dto) throws Exception {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("PUT");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);

            Gson gson = new Gson();
            String jsonInput = gson.toJson(dto);
            try (OutputStream os = conexao.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            if (conexao.getResponseCode() != SUCESSO) {
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseMessage());
            }

            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = converterJsonString(resposta);
            ClienteDTO clienteDto = gson.fromJson(json, ClienteDTO.class);

            conexao.disconnect();
            return new Cliente(clienteDto);

        } catch (Exception e) {
            throw new Exception("Erro ao atualizar cliente: " + e);
        }
    }

    public static void delete(Integer id) throws Exception {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("DELETE");

            if (conexao.getResponseCode() != SUCESSO) {
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseMessage());
            }

            conexao.disconnect();

        } catch (Exception e) {
            throw new Exception("Erro ao deletar cliente: " + e);
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
