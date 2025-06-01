/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

/**
 *
 * @author Marcelo
 */
public class ClienteDAO extends GenericDao<Cliente> {

    @Override
    protected Cliente construirObjeto(ResultSet rs) {
        Cliente cliente = null;
        
        try{
           cliente = new Cliente();
           cliente.setId(rs.getInt("id"));
           cliente.setEmail("nome");
           cliente.setEmail("email");
           cliente.setTelefone("telefone");
            
        }catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return cliente;
    }

    @Override
    public boolean salvar(Cliente cliente) {
        String sql = "INSERT INTO public.\"cliente\"(" +
            "\"id\", \"nome\", \"telefone\", \"email\") " +
            "VALUES (?, ?, ?, ?)";
        
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            
            ps.executeUpdate();
            ps.close();
            
            return true;   
            
        }catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE public.\"cliente\" " +
            "SET \"nome\"=?, \"telefone\"=?, \"email\"=? " +
            "WHERE \"id\"=?";
        
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEmail());
            ps.setInt(4, cliente.getId());

            ps.executeUpdate();
            ps.close();

            return true;

        }catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
}
