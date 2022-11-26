/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fajhri.apotek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fajhri
 * 
 * Create model for Database
 */
public class DB {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet result;
    
    public DB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/apotek", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * Register a new user
     * 
     * @param namaLengkap
     * @param username
     * @param password
     * @return result of register user.
     */
    public ResultSet registerNewUser(String namaLengkap, String username, String password) {
        try {
            String sql = "INSERT INTO pelanggan (nama_lengkap, username, password) VALUES (?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, namaLengkap);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            result = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Get User by passing username
     * @param username
     */
    public ResultSet getUserByUsername(String username) {
        try {
            String sql = "SELECT * FROM pelanggan WHERE username=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            
            result = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    /**
     * Select or show all data `drug`
     */
    public ResultSet showAllDrug() {
        try {
            String sql = "SELECT * FROM obat";
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    /**
     * Add transaction to drug
     * @param kodeObat
     * @param idPelanggan
     * @param totalBayar
     * @param totalKembalian
     * @param totalHarga
     * @param totalBarang
     */
    public void addTransactionDrug(String kodeObat, Integer idPelanggan, String totalBayar, String totalKembalian, String totalHarga, Integer totalBarang) {
        try {
            String sql = "INSERT INTO transaksi (kode_obat, id_pelanggan, total_bayar, total_kembalian, total_harga, total_barang) VALUES (?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kodeObat);
            pstmt.setInt(2, idPelanggan);
            pstmt.setString(3, totalBayar);
            pstmt.setString(4, totalKembalian);
            pstmt.setString(5, totalHarga);
            pstmt.setInt(6, totalBarang);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * get Drug by code
     * @param kode
     * @return 
     */
    public ResultSet getDrugByCode(String kode) {
        try {
            String sql = "SELECT * FROM obat WHERE kode=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kode);
            result = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Show all history of transactions
     * @param idPelanggan
     * @return 
     */
    public ResultSet getAllHistoryOfTransaction(Integer idPelanggan) {
        try {
            String sql = "SELECT * FROM transaksi WHERE id_pelanggan=?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPelanggan);
            result = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
