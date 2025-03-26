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


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conectaDAO DAO = new conectaDAO();
        Connection conn = DAO.connectDB();
        if (conn == null) {            
                System.out.println("Não foi possível estabelecer a conexão.");
                return;
            }        
        try {
            
            prep = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES(?,?,?)");
                    prep.setString(1,produto.getNome());
                    prep.setInt(2,produto.getValor());
                    prep.setString(3,produto.getStatus());
                    int status = prep.executeUpdate();
                    System.out.println("Conexao realizada com sucesso");
        } catch(SQLException ex){
            System.out.println("Erro ao conectar:"+ ex.getMessage());
        }           
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

