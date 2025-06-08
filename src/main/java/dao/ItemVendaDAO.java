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
        ItemVenda item = new ItemVenda();
        try {
            item.setId(rs.getInt("id"));
            item.setQuantidade(rs.getInt("quantidade"));
            item.setValorUnitario(rs.getBigDecimal("valor_unitario"));
            item.setValorTotal(rs.getBigDecimal("valor_total"));
            item.setVendaId(rs.getInt("venda_id"));
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.retornarPeloId(rs.getInt("produto_id"), "produto", "id");
            item.setProduto(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    @Override
    public boolean salvar(ItemVenda item) {
        String sql = "INSERT INTO public.\"itemvenda\" (quantidade, valor_unitario, valor_total, venda_id, produto_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getQuantidade());
            ps.setBigDecimal(2, item.getValorUnitario());
            ps.setBigDecimal(3, item.getValorTotal());
            ps.setInt(4, item.getVendaId());
            ps.setInt(5, item.getProduto().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
