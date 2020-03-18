/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afpa.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 80010-89-09
 */
public class ClientDAO extends dbconnexion {

    public ClientDAO()  {
    }

    public void Insert(Client c) {
        connexion();

        try {
            /*String url = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "");*/

            PreparedStatement stm = con.prepareStatement("INSERT INTO client (cli_nom, cli_prenom, cli_ville) VALUES (?, ?, ?)");

            stm.setString(1, c.getNom());
            stm.setString(2, c.getPrenom());
            stm.setString(3, c.getVille());

            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error while inserting entity 'client'");
            System.out.println(e.getMessage());
        }
        deconnexion();

    }

    public void Update(Client c) {
        connexion();

        try {
            PreparedStatement stm = con.prepareStatement("UPDATE client SET cli_nom=?, cli_prenom=?, cli_adresse=?, cli_ville=? WHERE cli_id= ?");

            stm.setString(1, c.getNom());
            stm.setString(2, c.getPrenom());
            stm.setString(3, c.getAdresse());
            stm.setString(4, c.getVille());
            stm.setInt(5, c.getId());

            stm.executeUpdate();

            stm.close();

        } catch (SQLException e) {
            System.out.println("Error while inserting entity 'client'");
            System.out.println(e.getMessage());
        }
        deconnexion();

    }

    /**
     *
     * @param id
     */
    public void Delete(int id) {
        connexion();

        try {
            String url = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "");

            PreparedStatement stm = con.prepareStatement("DELETE FROM client WHERE cli_id = ?");
            stm.setInt(1, id);

            stm.executeUpdate();

            stm.close();

        } catch (SQLException e) {
            System.out.println("Error while inserting entity 'client'");
            System.out.println(e.getMessage());
        }
        deconnexion();

    }

    public Client Find(int id) {
        Client cli = new Client();

        connexion();

        try {

            PreparedStatement stm = con.prepareStatement("SELECT * FROM client WHERE cli_id = ?");

            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            result.next();
            cli.setId(id);
            cli.setNom(result.getString("cli_nom"));
            cli.setPrenom(result.getString("cli_nom"));
            cli.setAdresse(result.getString("cli_adresse"));
            cli.setVille(result.getString("cli_ville"));

            //System.out.format("%s %s %s %s\n", prenom, nom, ville, adresse);
            stm.close();
            result.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        deconnexion();

        return cli;
    }

    public List<Client> List() {

        List<Client> resultat = new ArrayList<Client>();

        connexion();

        try {

            Statement stm = con.createStatement();

            ResultSet result = stm.executeQuery("SELECT * FROM client");

            while (result.next()) {
                Client c = new Client();
                c.setId(result.getInt("cli_id"));
                c.setNom(result.getString("cli_nom"));
                c.setPrenom(result.getString("cli_prenom"));
                c.setAdresse(result.getString("cli_adresse"));
                c.setVille(result.getString("cli_ville"));

                resultat.add(c);
            }

            stm.close();
            result.close();

        } catch (SQLException e) {
            System.out.println("Error while reading 'client'");
            System.out.println(e.getMessage());
        }
        deconnexion();

        return resultat;
    }
}
