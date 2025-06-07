/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import com.google.gson.Gson;
import dto.ProdutoDto;
import exceptions.ApiRequestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.Produto;

/**
 *
 * @author Rafael
 */
public class ProdutoService {
    private static String BASE_URL = "http://localhost:8080/produtos";
    private static int SUCESSO = 200;
    private static int CREATED = 201;
    private static int NOT_FOUND = 404;
    private static int BAD_REQUEST = 400;
    
    public static List<Produto> findAll(String nome) throws ApiRequestException, Exception{
        try{
            String urlString = nome == null || nome.isBlank() 
                    ? BASE_URL 
                    : BASE_URL + "?descricao=" + URLEncoder.encode(nome, "UTF-8");;
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            
            if(conexao.getResponseCode() != SUCESSO){
                if(conexao.getResponseCode() == NOT_FOUND){
                    throw new ApiRequestException(NOT_FOUND, "Nenhum produto encontrado");
                }
                
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseMessage());
            }
            
            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = converterJsonString(resposta);
            Gson gson = new Gson();
            
            ProdutoDto[] produtosDto = gson.fromJson(json, ProdutoDto[].class);
            List<Produto> listaProdutos = Arrays.stream(produtosDto).map(dto -> new Produto(dto)).collect(Collectors.toList());
            
            conexao.disconnect();
            return listaProdutos;
            
        }catch(ApiRequestException e){
            throw e;
        }catch(Exception e){
            throw new Exception("Erro ao consultar produtos: " + e);
        }
    }
    
    public static Produto insert(ProdutoDto dto) throws Exception{
        try{
            URL url = new URL(BASE_URL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);
            
            Gson gson = new Gson();
            String jsonInput = gson.toJson(dto);
            
            try(OutputStream output = conexao.getOutputStream()){
                output.write(jsonInput.getBytes());
                output.flush();
            }
            
            if(conexao.getResponseCode() != CREATED){
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseMessage());
            }
            
            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = converterJsonString(resposta);
            ProdutoDto produtoDto = gson.fromJson((json), ProdutoDto.class);
            Produto produto = new Produto(produtoDto);
            
            conexao.disconnect();
            
            return produto;
        }catch(Exception e){
            throw new Exception("Erro ao inserir produto: " + e);
        }
    }
    
    public static Produto findById(Integer id) throws Exception{
        try{
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() != SUCESSO) {
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseMessage());
            }

            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = converterJsonString(resposta);
            Gson gson = new Gson();
            ProdutoDto produtoDto = gson.fromJson(json, ProdutoDto.class);
            Produto produto = new Produto(produtoDto);
            
            conexao.disconnect();
            return produto;
        }catch(Exception e){
            throw new Exception("Erro ao buscar produto: " + e);
        }
    }
    
    public static Produto update(Integer id, ProdutoDto dto) throws Exception {
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
            ProdutoDto produtoDto = gson.fromJson(json, ProdutoDto.class);
            Produto produto = new Produto(produtoDto);
            
            conexao.disconnect();
            return produto;

        } catch (Exception e) {
            throw new Exception("Erro ao atualizar produto: " + e);
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
            throw new Exception("Erro ao deletar produto: " + e);
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
