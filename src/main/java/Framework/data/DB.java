/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author lorenzo
 */
public class DB {
    
    private static DataSource dataSource;
    private static InitialContext ctx;
    
    private static void init() {
        try {
          ctx = new InitialContext();
          Context envContext  = (Context)ctx.lookup("java:comp/env");
          dataSource = (DataSource)envContext.lookup("jdbc/webdb");
        } catch (NamingException e) {
            e.printStackTrace();
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, "ERRORE INIZIALIZZAZIONE DATABASE", ex);
        }
    }
    
    
    public static Connection getConnection() throws DataLayerException {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            throw new DataLayerException("ERRORE CONNESSIONE DATABASE", ex);
        }
    }
}
