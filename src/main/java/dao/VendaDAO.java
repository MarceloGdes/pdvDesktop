package dao;

import model.Cliente;
import model.ItemVenda;
import model.Venda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO extends GenericDAO<Venda>{

    @Override
    protected Venda construirObjeto(ResultSet rs) {
        Venda venda = null;
        Cliente cliente = null;

        ClienteDAO clienteDAO = new ClienteDAO();
        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();

        try{
            venda = new Venda();

            venda.setId(rs.getInt("id"));
            venda.setData(rs.getDate("data"));
            venda.setObservacao(rs.getString("observacao"));
            venda.setTotal(rs.getBigDecimal("total"));

            cliente = clienteDAO.retornarPeloId(rs.getInt(
                    "cliente_id"), "cliente", "id");

            venda.setCliente(cliente);
            venda.setItensVenda(null); //Implementar


        }catch (SQLException ex){
            Logger.getLogger(VendaDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return venda;
    }

    @Override
    public boolean salvar(Venda venda) {
        String sql = "INSERT INTO public.\"venda\"(" +
                "\"id\", \"observacao\", \"data\", \"total\", \"cliente_id\") " +
                "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();

        try{
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, venda.getId());
            ps.setString(2, venda.getObservacao());
            ps.setDate(3, venda.getData());
            ps.setBigDecimal(4, venda.getTotal());
            ps.setInt(5, venda.getCliente().getId());

            ps.executeUpdate();

            for(ItemVenda itemVenda : venda.getItensVenda()){
                itemVenda.setVendaId(venda.getId());
                itemVendaDAO.salvar(itemVenda);
            }

            ps.close();
            return true;

        }catch (SQLException ex){
            Logger.getLogger(ItemVendaDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean atualizar(Venda venda) {
        String sql = "UPDATE public.\"venda\"" +
                "SET \"observacao\"=?, \"data\"=?, \"total\"=?, \"cliente_id\"=? " +
                "WHERE  \"id\"=?";

        String sqlListaItemVendas = "SELECT * FROM \"public.itemvenda\" WHERE \"venda_id\"=?";

        PreparedStatement ps = null;
        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();

        try{
            ps = conn.prepareStatement(sql);

            ps.setString(1, venda.getObservacao());
            ps.setDate(2, venda.getData());
            ps.setBigDecimal(3, venda.getTotal());
            ps.setInt(4, venda.getCliente().getId());

            ps.executeUpdate();

            //Selecionando os itens atuais dessa venda.
            List<ItemVenda> itensAtuais = itemVendaDAO.retornarLista(sqlListaItemVendas);

            List<Integer> idsMantidos = new ArrayList<>();

            for(ItemVenda itemVenda : venda.getItensVenda()){
                itemVenda.setVendaId(venda.getId());

                if(itemVenda.getId() == null){
                    //Salva um novo item para a venda, caso não tenha ID informado.
                    itemVendaDAO.salvar(itemVenda);

                }else {
                    //Atualiza o item, com base em seu ID.
                    itemVendaDAO.atualizar(itemVenda);

                    //Armazena o id mantido.
                    idsMantidos.add(itemVenda.getId());
                }
            }

            //Valida se existe itens armazenados no banco, que não foram enviados nesse update e exclui
            for(ItemVenda itemVendaBanco : itensAtuais){
                if(!idsMantidos.contains(itemVendaBanco.getId())){
                    itemVendaDAO.excluir(itemVendaBanco.getVendaId(), "itemvenda", "id");
                }
            }

            ps.close();
            return true;

        }catch (SQLException ex){
            Logger.getLogger(ItemVendaDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
