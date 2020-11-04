/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chilyfacts.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pc
 */
@ManagedBean
@RequestScoped
public class Categoria {

    private String nome_categoria;
    private String categoria_id;

    public void setCategoria_id(String categoria_id)
    {
        this.categoria_id = categoria_id;
    }
    public String getCategoria_id()
    {
        return categoria_id;
    }
    public void setNome_categoria(String nome_categoria)
    {
        this.nome_categoria = nome_categoria;
    }
    public String getNome_categoria()
    {
        return nome_categoria;
    }
    
    public ArrayList<Categoria> get_all_categorias() throws SQLException
    {
        ArrayList<Categoria> lista_de_categorias=new ArrayList<Categoria>();
        try
        {
            Connection connection=null;
            Conexao_DB obj_Conexao_DB=new Conexao_DB();
            connection=obj_Conexao_DB.get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("select * from categorias");
            while(rs.next())
                    {
                        Categoria obj_Categoria=new Categoria();
                        obj_Categoria.setNome_categoria(rs.getString("nome_categoria"));
                        obj_Categoria.setCategoria_id(rs.getString("categoria_id"));
                        lista_de_categorias.add(obj_Categoria);
                    }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return lista_de_categorias;
    }
    public void add_categoria() throws SQLException
    {
        try
        {
            Connection connection=null;
            Conexao_DB obj_Conexao_DB=new Conexao_DB();
            connection=obj_Conexao_DB.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into categorias(nome_categoria) value('"+nome_categoria+"')");
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    private final Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    public String editar_categoria() throws SQLException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String campo_id = params.get("action");
        try
        {
            Connection connection=null;
            Conexao_DB obj_Conexao_DB=new Conexao_DB();
            connection=obj_Conexao_DB.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from categorias where categoria_id="+campo_id);
            Categoria obj_Categoria=new Categoria();
            rs.next();
            
            
            obj_Categoria.setNome_categoria(rs.getString("nome_categoria"));
            obj_Categoria.setCategoria_id(rs.getString("categoria_id"));
            
            sessionMap.put("editarcategoria",obj_Categoria);
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return "edit.xhtml?faces-redirect=true";
    }
    public String atualizar_categoria() throws SQLException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String id_atual = params.get("id_atual");
        try
        {
            Connection connection=null;
            Conexao_DB obj_Conexao_DB=new Conexao_DB();
            connection=obj_Conexao_DB.get_connection();
            PreparedStatement ps = connection.prepareStatement("update categorias set nome_categoria=? where categoria_id=?");
            ps.setString(1,nome_categoria);
            ps.setString(2,id_atual);
            System.out.println(ps);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return "index.xhtml?faces-redirect=true";
    }
    public String apagar_categoria() throws SQLException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String campo_id = params.get("action");
        try
        {
            Connection connection=null;
            Conexao_DB obj_Conexao_DB=new Conexao_DB();
            connection=obj_Conexao_DB.get_connection();
            PreparedStatement ps = connection.prepareStatement("delete from categorias where categoria_id=?");
            ps.setString(1,campo_id);
            System.out.println(ps);
            ps.executeUpdate();
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return "index.xhtml?faces-redirect=true";
    }
    public Categoria() {
    }
    
}
