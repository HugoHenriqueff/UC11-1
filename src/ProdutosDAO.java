/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) {

        conectaDAO DAO = new conectaDAO();
        Connection conn = DAO.connectDB();
        if (conn == null) {
            System.out.println("Não foi possível estabelecer a conexão.");
            return false;
        }
        try {

            prep = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES(?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            int status = prep.executeUpdate();
            if (status > 0) { 
            System.out.println("Cadastro realizado com sucesso");
            return true; 
        } else {
            System.out.println("Falha ao cadastrar o produto");
            return false; 
        }
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar:" + ex.getMessage());
            return false;
        }

    }

    public List<ProdutosDTO> listarProdutos() {
        conectaDAO DAO = new conectaDAO();
        Connection conn = DAO.connectDB();
        if (conn == null) {
            System.out.println("Não foi possível estabelecer a conexão.");
        }

        String sql = "SELECT * FROM produtos";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<ProdutosDTO> listagem = new ArrayList<>();
            while (rs.next()) {
                ProdutosDTO Pro = new ProdutosDTO();

                Pro.setId(rs.getInt("id"));
                Pro.setNome(rs.getString("nome"));
                Pro.setValor(rs.getInt("valor"));
                Pro.setStatus(rs.getString("status"));
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Valor: " + rs.getInt("valor"));
                System.out.println("Status: " + rs.getString("status"));
                listagem.add(Pro);

            }
            return listagem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
