/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SistemaGestaoAcademica.db;

import br.com.SistemaGestaoAcademica.web.DatabaseConnector;
import java.util.ArrayList;


/**
 *
 * @author a Jefferson Rodrigues
 */
public class User {
    private long id;
    private String login;
    private String name;
    private String role;
    private long passwordHash;   
    
    public User(){
    
    }

    public User(long id, String login, String name, String role, long passwordHash) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.role = role;
        this.passwordHash = passwordHash;
    }

    public long getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(long passwordHash) {
        this.passwordHash = passwordHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public static ArrayList<User> getUsers(){
        ArrayList<User> list = new ArrayList<>();
        ///TODO
        return list;
    }
    public static User validateUser(String login, String password) throws Exception{ //refazer este codigo
        String Query = "SELECT * FROM USERS WHERE login = ? AND passwordHash = ?";
        ArrayList<Object[]> list = DatabaseConnector.executeQuery(Query, new Object[]{login, password.hashCode()});
        if(list.size()>0){
            Object[] row = list.get(0);
            User u = new User((long)row[0],(String)row[1],(String)row[2],(String)row[3],(long)row[4]);
            return u;
        }else{
            return null;
        }
    }
 
}
