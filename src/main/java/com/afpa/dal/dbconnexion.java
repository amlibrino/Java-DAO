/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afpa.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 80010-89-09
 */
public abstract class dbconnexion {

    private static final String login = "root";     // hmdf Votre loggin d'accès au serveur de BDD 
    private static final String password = "";    //  Le Password pour vous identifier auprès du serveur
    private static final String base = "hotel";    // hmdf La bdd avec laquelle vous voulez travailler 
    private static final String url = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC";
    //private static String url = "jdbc:mysql://localhost:3306/" + base + "?serverTimezone=UTC";
//static = 
    public static Connection con = null;

    public dbconnexion() {
    }

    public void connexion() {

        try {
             con = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("Error de connexion à la base");
            System.out.println(e.getMessage());
        }
    }

    public void deconnexion() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("connexion fermée");
            System.out.println(e.getMessage());
        }
    }   
    
}
