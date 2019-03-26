/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SistemaGestaoAcademica.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author a Jefferson Rodrigues
 */
public class DatabaseConnector { // modificar os drivers de conexao para mysql
    private static final String DATABASE_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/webparking";
    private static final String DATABASE_USER = "parking";
    private static final String DATABASE_PASS = "parking";
    
    public static void executeCommand(String SQL, Object[] parameters) throws Exception{
        Class.forName(DATABASE_DRIVER);
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
        PreparedStatement stmt = con.prepareStatement(SQL);
        for(int i=0; i<parameters.length; i++){
            stmt.setObject(i+1, parameters[i]);
        }
        stmt.execute();
        stmt.close();
        con.close();
    }
    public static ArrayList<Object[]> executeQuery(String SQL, Object[] parameters) throws Exception{
        Class.forName(DATABASE_DRIVER);
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
        PreparedStatement stmt = con.prepareStatement(SQL);
        for(int i=0; i<parameters.length; i++){
            stmt.setObject(i+1, parameters[i]);
        }
        ResultSet rs = stmt.executeQuery();
        ArrayList<Object[]> list = new ArrayList<>();
        while(rs.next()){
            Object[] row = new Object[rs.getMetaData().getColumnCount()];
            for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
                row[i-1] = rs.getObject(i);
            }
            list.add(row);
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
}
