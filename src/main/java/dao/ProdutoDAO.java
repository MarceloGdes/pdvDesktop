package dao;

import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO extends GenericDAO<Produto> {
    @Override
    protected Produto construirObjeto(ResultSet rs) {
        Produto produto = null;

        try{
            produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setCategoria(rs.getString("categoria"));
            produto.setValor(rs.getBigDecimal("valor"));

        }catch (SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return produto;
    }

    @Override
    public boolean salvar(Produto produto) {
        String sql = "INSERT INTO public.\"produto\"(" +
                "\"id\", \"descricao\", \"valor\", \"categoria\") "+
                "VALUES (?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, produto.getId());
            ps.setString(2, produto.getDescricao());
            ps.setBigDecimal(3, produto.getValor());
            ps.setString(4, produto.getCategoria());

            ps.executeUpdate();
            ps.close();

            return true;

        }catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean atualizar(Produto produto) {
        String sql = "UPDATE public.\"produto\" " +
                "SET \"descricao\"=?, \"valor\"=?, \"categoria\"=? " +
                "WHERE \"id\"=?";

        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getDescricao());
            ps.setBigDecimal(2, produto.getValor());
            ps.setString(3, produto.getCategoria());
            ps.setInt(4, produto.getId());

            ps.executeUpdate();
            ps.close();

            return true;

        }catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
