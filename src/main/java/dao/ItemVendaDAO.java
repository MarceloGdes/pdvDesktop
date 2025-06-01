package dao;

import model.ItemVenda;
import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemVendaDAO extends GenericDAO<ItemVenda> {
    @Override
    protected ItemVenda construirObjeto(ResultSet rs) {
        ItemVenda itemVenda = null;
        Produto produto = null;

        ProdutoDAO produtoDAO = new ProdutoDAO();

        try{
            itemVenda = new ItemVenda();

            itemVenda.setId(rs.getInt("id"));
            itemVenda.setQuantidade(rs.getInt("quantidade"));
            itemVenda.setValorUnitario(rs.getBigDecimal("valor_unitario"));
            itemVenda.setValorTotal(rs.getBigDecimal("valor_total"));
            itemVenda.setVendaId(rs.getInt("venda_id"));

            produto = produtoDAO.retornarPeloId(rs.getInt("id"), "produto", "id");

            itemVenda.setProduto(produto);

        }catch (SQLException ex){
            Logger.getLogger(ItemVendaDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return itemVenda;
    }

    @Override
    public boolean salvar(ItemVenda itemVenda) {
        String sql = "INSERT INTO public.\"itemvenda\"(" +
                "\"quantidade\", \"valor_unitario\", \"valor_total\", \"venda_id\", \"produto_id\") " +
                "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);

            ps.setInt(1, itemVenda.getQuantidade());
            ps.setBigDecimal(2, itemVenda.getValorUnitario());
            ps.setBigDecimal(3, itemVenda.getValorTotal());
            ps.setInt(4, itemVenda.getVendaId());
            ps.setInt(5, itemVenda.getProduto().getId());

            ps.executeUpdate();
            ps.close();

            return true;

        }catch (SQLException ex){
            Logger.getLogger(ItemVendaDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean atualizar(ItemVenda itemVenda) {
        String sql = "UPDATE public.\"itemvenda\"" +
                "\"quantidade\"=?, \"valor_unitario\"=?, \"valor_total\"=?, \"venda_id\"=?, \"produto_id\"=? " +
                "WHERE \"id\"=?;";

        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);

            ps.setInt(1, itemVenda.getQuantidade());
            ps.setBigDecimal(2, itemVenda.getValorUnitario());
            ps.setBigDecimal(3, itemVenda.getValorTotal());
            ps.setInt(4, itemVenda.getVendaId());
            ps.setInt(5, itemVenda.getProduto().getId());
            ps.setInt(6, itemVenda.getId());

            ps.executeUpdate();
            ps.close();

            return true;

        }catch (SQLException ex){
            Logger.getLogger(ItemVendaDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
