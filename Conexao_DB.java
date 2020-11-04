/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chilyfacts.com;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author pc
 */
public class Conexao_DB 
{
    public static void main (String[] args)
    {
        Conexao_DB obj_Conexao_DB=new Conexao_DB();
        System.out.println(obj_Conexao_DB.get_connection());
    }
    public Connection get_connection()
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10374420?useTimezone=true&serverTimezone=UTC","sql10374420","bgf8GjdzSe");
        }
        catch (Exception e)
                {
                    System.out.println(e);
                }
        return connection;
    }
}
