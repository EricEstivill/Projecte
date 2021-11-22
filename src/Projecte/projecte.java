/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projecte;

import static Projecte.projecte.connectarBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author DAM
 */ 
   

public class projecte {
    static Connection connectarBD = null;
    public static void main (String[] args) throws SQLException {
        boolean sortir=false;
        connectarBD();
        Scanner teclat = new Scanner (System.in);
        
            //12/11/21
        do{
           System.out.println("^^^^MENU GESTOR PRODUCTES^^^^");
           System.out.println("1.Manteniment de productes A/B/M/C");
           System.out.println("2.Actualitzar stocks");
           System.out.println("3.Generar comanda als proveïdors");
           System.out.println("4.Consultar comandes del dia");
           System.out.println("5.Sortir");
           System.out.println("\nTria una de les opcions");
           
           int opcio=teclat.nextInt();
           
           switch (opcio){
               case 1:
                    gestioProductes ();
                    break;
               case 2:
                    actualitzarStocks();
                    break;
               case 3:
                    generarComanda();
                    break;
               case 4:
                    consultarComandes();
                    break;
               case 5:
                   sortir=true;
                   break;
               default:
                   System.out.println("L'Opció no és vàlida");
            }
           
      
            System.out.println(("opció: ")+ opcio); 
          
         
        }   
        while (!sortir);
            desconnexioBD();
    }
          

    static void actualitzarStocks(){
        System.out.println("Actualitzar Stock");

    }
    static void generarComanda(){
        System.out.println("Generar comanda");
    }
    static void consultarComandes(){
        System.out.println("Consultar comanda");
    }
    
    static void gestioProductes () throws SQLException {
        Scanner teclat = new Scanner (System.in);
        boolean enrere=false;
        do{
           System.out.println("\n^^^^MENU GESTOR PRODUCTES^^^^");
           System.out.println("1.Llista Productes");
           System.out.println("2.Alta de Productes");
           System.out.println("3.Modificar Productes");
           System.out.println("4.Esborrar Productes");
           System.out.println("5.Enrere");
           System.out.println("\nTria una de les opcions");

            int opcio=teclat.nextInt();
            


           switch (opcio){
                case 1:
                    llistaProductes();
                    break;
                case 2:
                    altaProductes();
                    break;
                case 3:
                    modificarProductes();
                    break;
                case 4:
                    esborrarProductes();
                    break;
                case 5:
                   enrere=true;
                   break;
                default:
                   System.out.println("L'Opció no és vàlida");
            }
        } 
        while (!enrere);
                  
    } 

    public static void desconnexioBD() {
        System.out.println("Desconnectat de la BD");
    }

    

    public static void llistaProductes() throws SQLException {
        System.out.println("Llistem productes");
        String consulta ="SELECT * FROM productes ORDER BY Codi_id";
        PreparedStatement ps = connectarBD.prepareStatement(consulta);
        ResultSet rs=ps.executeQuery();
        
        while (rs.next()){
            System.out.println("Codi_id: " + rs.getInt("Codi_id"));
            System.out.println("Nom: " + rs.getString("Nom"));
            System.out.println("Stock: " + rs.getInt("Stock"));
            System.out.println("Proveidors: " + rs.getInt("Codi_prov"));
            System.out.println("Materials: " + rs.getString("Materials"));
        }
    }
    static void altaProductes() throws SQLException{
        Scanner teclat = new Scanner (System.in);
        String consulta = "INSERT INTO productes (Nom,Stock,Codi_prov,Materials) values(?,?,?,?)";
        System.out.println("Posa un nom");
        String Nom =teclat.nextLine();
        System.out.println("Posa un numero de stock");
        int Stock =teclat.nextInt();
        System.out.println("Posa un numero de Proveidors");
        int Codi_prov =teclat.nextInt();
        teclat.nextLine();
        System.out.println("Posa un Material");
        String Materials =teclat.nextLine();
        
   
        
        PreparedStatement sentencia = null;
 
        try {
            sentencia = connectarBD.prepareStatement(consulta);
            sentencia.setString(1, Nom);
            sentencia.setInt(2, Stock);
            sentencia.setInt(3, Codi_prov);
            sentencia.setString(4, Materials);
            sentencia.executeUpdate();
        } 
        catch (SQLException sqle) {
            sqle.printStackTrace();
        } 
        finally {
          //Nos aseguramos de cerrar los recursos abiertos
            if (sentencia != null)
            try {
                sentencia.close();
            } 
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    public static void modificarProductes() {
        Scanner teclat = new Scanner (System.in);
        String consulta = "UPDATE productes SET Nom = ?, Stock = ?,  Codi_prov = ?, Materials = ? WHERE Nom = ?";
        System.out.println("Busca un nom");
        String Nom_nou =teclat.nextLine();
        System.out.println("Modifica el nom");
        String Nom =teclat.nextLine();
        System.out.println("Modifica el stock");
        int Stock =teclat.nextInt();
        System.out.println("Modifica el proveidor");
        int Codi_prov =teclat.nextInt();
        teclat.nextLine();
        System.out.println("Modifica el Material");
        String Materials =teclat.nextLine();
        
        PreparedStatement sentencia = null;
         
        try {
            sentencia = connectarBD.prepareStatement(consulta);
            sentencia.setString(1, Nom);
            sentencia.setInt(2, Stock);
            sentencia.setInt(3, Codi_prov);
            sentencia.setString(4, Materials);
            sentencia.setString(5, Nom_nou);
            sentencia.executeUpdate();
        } 
        catch (SQLException sqle) {
            sqle.printStackTrace();
        } 
        finally {
          //Nos aseguramos de cerrar los recursos abiertos
            if (sentencia != null)
                try {
                    sentencia.close();
                } 
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
         
         
    }

    public static void esborrarProductes() {
        Scanner teclat = new Scanner (System.in);
        String consulta = "DELETE FROM productes WHERE Nom  = ?";
        System.out.println("Borra un Producte");
        String Nom =teclat.nextLine();

        
        PreparedStatement sentencia = null;
 
        try {
          sentencia = connectarBD.prepareStatement(consulta);
          sentencia.setString(1, Nom);
          sentencia.executeUpdate();

        } 
        catch (SQLException sqle) {
          sqle.printStackTrace();
        } 
        finally {
          //Nos aseguramos de cerrar los recursos abiertos
          if (sentencia != null)

            try {
              sentencia.close();
            } catch (SQLException sqle) {
              sqle.printStackTrace();
            }
        }
    }
    static void connectarBD(){

            String servidor="jdbc:mysql://localhost:3306/";
            String bbdd="projecte";
            String user="root";
            String password="";
            
            try{

                connectarBD = (Connection) DriverManager.getConnection(servidor + bbdd, user, password);
                
            }
            catch (SQLException ex) {
                ex.printStackTrace();

            }
    } 
            
}