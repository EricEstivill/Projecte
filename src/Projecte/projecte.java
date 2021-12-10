/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projecte;

import static Projecte.projecte.connectarBD;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author DAM
 */ 
   

public class projecte {
    static int Codi_id;
    static int Preu;
    static String Materials;
    static String Nom;
    static int Stock;
    static String Proveidor;
    static int Codi_pro;
    static int Codi_prov;
    public static String PATHCOMANDES2 = "files/COMANDES/";
    public static String DADESEMPRESA = "files/DADESEMPRESA/";
    public static String PATHCOMANDES = "files/COMANDES/proveidor.txt";
    public static String PATHPENDENTS = "files/ENTRADES PENDENTS/";
    public static String PATHPROCESADES = "files/ENTRADES PROCESADES/";
    static Connection connectarBD = null;
    private static int MAXSTOCK;
    public static String [] Proveidor_Array= new String[100];
    int [] Productes_Array = new int[100];
    public static void main (String[] args) throws SQLException, IOException {
       
        boolean sortir=false;
        connectarBD();
        Scanner teclat = new Scanner (System.in);
        
            //12/11/21
        do{
           System.out.println("^^^^MENU GESTOR PRODUCTES^^^^");
           System.out.println("1.Manteniment de productes A/B/M/C");
           System.out.println("2.Actualitzar stocks");
           System.out.println("3.Generar comanda als proveïdors");
           System.out.println("4.Consultar comandes");
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
                    genComands2();
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
           
      
            System.out.println(("\nHa escollit l'opció: ")+ opcio); 
          
         
        }   
        while (!sortir);
            desconnexioBD();
    }
          

    static void actualitzarStocks() throws IOException, SQLException{
        
        System.out.println("Actualitzar Stock");
        File file = new File(PATHPENDENTS);
        
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for(int i=0;i<files.length;i++){
                System.out.println("fitxer: " + files[i]);
                visualitzarActualitzarFitxer(files[i]);
                moureFitxerAProcessat(files[i]);
                
            }
        } else {
            System.out.println("No es un directori");
        }

    }
    


    
    static void gestioProductes () throws SQLException {
        Scanner teclat = new Scanner (System.in);
        boolean enrere=false;
        do{
           System.out.println("\n^^^^MENU GESTOR PRODUCTES^^^^");
           System.out.println("1.Llista Productes");
           System.out.println("2.Llistar producte determinat");
           System.out.println("3.Alta de Productes");
           System.out.println("4.Modificar Productes");
           System.out.println("5.Esborrar Productes");
           System.out.println("S.Enrere");
           System.out.println("\nTria una de les opcions");

            int opcio=teclat.nextInt();
            


           switch (opcio){
                case 1:
                    llistaProductes();
                    break;
                case 2:
                    mostrarProducteDeterminat();
                    break;
                case 3:
                    altaProductes();
                    break;
                case 4:
                    modificarProductes();
                    break;
                case 5:
                    esborrarProductes();
                    break;
                case 6:
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
            System.out.println("Id: " + rs.getInt("Codi_id") + " Preu:  " + rs.getString("preu") + " Materials:  " + rs.getString("Materials")
                            + " Nom:  " + rs.getString("Nom") + " Stock:  " + rs.getString("Stock"));

        }
    }
    
    static void mostrarProducteDeterminat() throws SQLException {
        boolean enrere=false;
        
        Scanner teclat = new Scanner (System.in);
        Scanner st = new Scanner(System.in);
        do {

            System.out.println("com vols identificar el producte");
            System.out.println("1. Per ID");
            System.out.println("2. Per NOM");
            System.out.println("s. sortir");
            String sa = st.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
                case '1':
                    System.out.println("Ficar el ID del producte:");
                    Codi_id = teclat.nextInt();
                    System.out.println(Codi_id);
                    String consulta = ("select * from productes where Codi_id= " + Codi_id + ";");
                    PreparedStatement ps = connectarBD.prepareStatement(consulta);
                    ps.executeQuery();
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(
                                "Id: " + rs.getInt("Codi_id") + " Preu: " + rs.getInt("preu") + " Preu: " + " Materials: "
                                        + rs.getString("Materials") + " Nom: " + rs.getString("nom") + " Stock: "
                                        + rs.getInt("Stock"));
                    }

                    enrere = false;
                    break;
                case '2':
                    System.out.println("Ficar el NOM del producte");
                    teclat.nextLine();
                    Nom = teclat.nextLine();
                    String consulta1 = String.format("select * from productes where Nom=" + "\"%s\"" + ";", Nom);
                    System.out.println(consulta1);
                    PreparedStatement ps1 = connectarBD.prepareStatement(consulta1);
                    ps1.executeQuery();
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        System.out.println("id: " + rs1.getInt("Codi_id") + " Preu " + rs1.getInt("preu") + " Materials "
                                + rs1.getString("Materials") + " nom " + rs1.getString("Nom") + " Stock "
                                + rs1.getInt("Stock"));
                    }

                    enrere = false;
                    break;

                case 's':
                    enrere = true;
                    break;
                case 'S':
                    enrere = true;
                    break;
            }

        } while (!enrere);

    }
    static void altaProductes() throws SQLException{
        Scanner teclat = new Scanner (System.in);
        String consulta = "INSERT INTO productes (Nom,Stock,Codi_prov,Materials,Descr,Preu) values(?,?,?,?,?,?)";
        System.out.println("Posa un nom");
        Nom =teclat.nextLine();
        System.out.println("Posa un numero de stock");
        Stock =teclat.nextInt();
        System.out.println("Posa un numero de Proveidors");
        int Codi_prov =teclat.nextInt();
        teclat.nextLine();
        System.out.println("Posa un Material");
        Materials =teclat.nextLine();
        System.out.println("Posa una Descripcio");
        String Descr =teclat.nextLine();
        System.out.println("Posa un Preu");
        Preu =teclat.nextInt();
        
   
        
        PreparedStatement sentencia = null;
 
        try {
            sentencia = connectarBD.prepareStatement(consulta);
            sentencia.setString(1, Nom);
            sentencia.setInt(2, Stock);
            sentencia.setInt(3, Codi_prov);
            sentencia.setString(4, Materials);
            sentencia.setString(5, Descr);
            sentencia.setInt(6, Preu);
            if (sentencia.executeUpdate() != 0) {
            System.out.println("Ha insertat " + "Nom: " + Nom + " Preu: " + Preu + " Materials: " + Materials
                    + " quantitat: " + Stock);
        } else {
            System.out.println("No s'ha insertat");
        }
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

    static void modificarProductes() throws SQLException {
        
        boolean enrere=false;
        Scanner teclat = new Scanner (System.in);
        Scanner st = new Scanner(System.in);

        do {

            System.out.println("com vols identificar el producte que vols modificar");
            System.out.println("1. Per ID");
            System.out.println("2. Per NOM");
            System.out.println("s. sortir");
            String sa = st.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
                case '1':
                    System.out.println("Ficar el ID del producte:");
                    Codi_id = teclat.nextInt();
                    String consulta = ("select * from productes where Codi_id= " + Codi_id + ";");
                    PreparedStatement ps = connectarBD.prepareStatement(consulta);
                    ps.executeQuery();
                    ResultSet rs = ps.executeQuery();

                    // id = rs.getInt("id");
                    // preu = rs.getInt("preu");
                    // codiCat = rs.getInt("codi_cat");
                    // nom = rs.getString("nom");
                    // quantitat = rs.getInt("quantitat");

                    // PERQUE SI FICO LES VARIABLES A FORA EM GENERA UN ERROR ? I HE DE FICAR DINS
                    // DEL WHILE

                    while (rs.next()) {
                        Codi_id = rs.getInt("Codi_id");
                        Preu = rs.getInt("Preu");
                        Materials = rs.getString("Materials");
                        Nom = rs.getString("Nom");
                        Stock = rs.getInt("Stock");
                        System.out.println(
                                "id: " + Codi_id + " Preu " + Preu + " euros" + " Materials " + Materials + " nom " + Nom
                                        + " Stock " + Stock);
                    }
                    enrere = false;
                    modificarProductes2();
                    break;
                case '2':
                    System.out.println("Ficar el NOM del producte");
                    teclat.nextLine();
                    Nom = teclat.nextLine();
                    String consulta1 = String.format("select * from productes where Nom=" + "\"%s\"" + ";", Nom);
                    PreparedStatement ps1 = connectarBD.prepareStatement(consulta1);
                    ps1.executeQuery();
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        Codi_id = rs1.getInt("Codi_id");
                        Preu = rs1.getInt("Preu");
                        Materials = rs1.getString("Materials");
                        Nom = rs1.getString("Nom");
                        Stock = rs1.getInt("Stock");
                        System.out.println(
                                "Codi_id: " + Codi_id + " Preu " + Preu + " euros" + " Materials " + Materials + " Nom " + Nom
                                        + " Stock " + Stock);
                    }
                    // String insNomProd = "nom =\"%s\"".formatted(nomProd);
                    // insNomProd = String.format("nom = \"%s\" ", nomProd);
                    // String insNomProd = "nom =" + "\"nomProd"\"";
                    // System.out.println(insNomProd);
                    enrere = false;
                    modificarProductes2();
                    break;

                case 's':
                    enrere = true;
                    break;
                case 'S':
                    enrere = true;
                    break;
            }

        } while (!enrere);
    }

    static void modificarProductes2() throws SQLException {
        boolean enrere=false;
        Scanner teclat = new Scanner (System.in);
        Scanner st = new Scanner(System.in);
        do {

            System.out.println("Que vols modifica ?");
            System.out.println("1. Nom");
            System.out.println("2. Preu");
            System.out.println("3. Material");
            System.out.println("4. La quantitat");
            System.out.println("s. Sortir");

            String sa = st.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
                case '1':
                    System.out.println("fica el nom de producte");
                    teclat.nextLine();
                    Nom = teclat.nextLine();

                    break;
                case '2':
                    System.out.println("fica el preu de producte");
                    Preu = teclat.nextInt();

                    break;
                case '3':
                    System.out.println("Materials ");
                    Materials = teclat.nextLine();
                    break;
                case '4':
                    System.out.println("Stock: ");
                    Stock = teclat.nextInt();
                    break;
                case 's':
                    enrere = true;
                    break;
                case 'S':
                    enrere = true;
                    break;
            }
            String consulta = "Update productes Set Nom = ?,Preu = ?,Materials = ?, Stock = ? where Codi_id = ?;";
            PreparedStatement ps = connectarBD.prepareStatement(consulta);
            
            ps.setString(1, Nom);
            ps.setInt(2, Preu);
            ps.setString(3, Materials);
            ps.setInt(4, Stock);
            ps.setInt(5, Codi_id);

            if (ps.executeUpdate() != 0) {
                System.out.println(" ");
            } else {
                System.out.println("no insertat");
            }

        } while (!enrere);

    }

    static void esborrarProductes() throws SQLException {
        boolean enrere=false;
        Scanner teclat = new Scanner (System.in);
        Scanner st = new Scanner(System.in);
        do {

            System.out.println("Com vols identificar el producte per esborrar?");
            System.out.println("1. ID");
            System.out.println("2. NOM");
            System.out.println("s. Sortir");

            String sa = st.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
                case '1':
                    System.out.println("Posar la ID");
                    Codi_id = teclat.nextInt();
                    String consulta = String.format("select * from productes where Codi_id=" + Codi_id);
                    PreparedStatement ps2 = connectarBD.prepareStatement(consulta);
                    ps2.executeQuery();
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Codi_id = rs2.getInt("Codi_id");
                        Nom = rs2.getString("Nom");
                    }
                    enrere = false;
                    break;
                case '2':
                    System.out.println("Posar el nom del producte");

                    
                    Nom = teclat.nextLine();
                    String consulta1 = String.format("select * from productes where Nom=" + "\"%s\"" + ";", Nom);
                    PreparedStatement ps1 = connectarBD.prepareStatement(consulta1);
                    ps1.executeQuery();
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        Codi_id = rs1.getInt("Codi_id");
                        Nom = rs1.getString("Nom");
                    }
                    enrere = false;
                    break;
                case 's':
                    enrere = true;
                    break;
                case 'S':
                    enrere = true;
                    break;
            }

            System.out.println("El producte amb ID: " + Codi_id + " i amb el Nom :" + Nom + " esta eliminat");

            try {
                
                PreparedStatement ps = connectarBD.prepareStatement("DELETE FROM productes WHERE Codi_id = ? " + ";");
                ps.setInt(1, Codi_id);
                ps.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }

        } while (!enrere);

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

        static void visualitzarActualitzarFitxer(File file) throws FileNotFoundException, IOException, SQLException {
       
        FileReader reader = new FileReader (file);
        
        BufferedReader buffer = new BufferedReader(reader);
        
               
        String linea;
        while((linea=buffer.readLine()) !=null){
            System.out.println(linea);
            int stock = linea.indexOf(":");

            Codi_id = Integer.parseInt(linea.substring(0, stock));
            Stock = Integer.parseInt(linea.substring(stock + 1));

            String update = "UPDATE productes SET Stock=Stock+? WHERE Codi_id=?";
            PreparedStatement actualitzar = connectarBD.prepareStatement(update);
            actualitzar.setInt(1, Stock);
            actualitzar.setInt(2, Codi_id);

            actualitzar.executeUpdate();
        }
        buffer.close();
        reader.close();
        
        
    }

    static void moureFitxerAProcessat(File file) throws IOException {
        FileSystem sistemaFitxers=FileSystems.getDefault();
        Path origen=sistemaFitxers.getPath(PATHPENDENTS + file.getName());
        Path desti=sistemaFitxers.getPath(PATHPROCESADES +file.getName());
        
        Files.move(origen,desti, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("S'ha mogut a procesats el fitxer: " + file.getName());
      
        
        
    }
    
    
    
    
     static void genComands2() throws SQLException, IOException{
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        String consulta = "SELECT prod.Codi_id, prod.Nom, prod.Stock, prov.Nom FROM productes prod, proveidor prov WHERE prod.Codi_prov=prov.Codi_pro AND prod.Stock<=80 ORDER by prov.Codi_pro";
        PreparedStatement ps = connectarBD.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        
        if (rs.next()){
            
            Codi_id = rs.getInt(1);
            Nom = rs.getString(2);
            Stock = rs.getInt(3);
            Proveidor = rs.getString(4);
            pw = escriureCapçaleraComanda(Proveidor);
            //int contadorProveidors=0;
            //int contadorProductes=0;
                   
            //Proveidor_Array[contadorProveidors]=rs.getString(Proveidor);

            do{
                if(!Proveidor.equals(rs.getString(4))){
                    Proveidor=rs.getString(4);
                   pw.close();

                   pw = escriureCapçaleraComanda(Proveidor);
                }
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));
                pw.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

               
            }while(rs.next());
            pw.close();
        }
        
    }

    static PrintWriter escriureCapçaleraComanda(String Proveidor) throws IOException{
            
            FileWriter fw = new FileWriter ("files/COMANDES/" +Proveidor+LocalDate.now() + ".txt");
            BufferedWriter bf = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bf);
//escrivim dades empresa
                pw.println("^^^^^Electroimp^^^^^");
                pw.println("");
                pw.println("____________________________________");
                pw.println("Codi client: 245710");
                pw.println("Telèfon: 973537843");
                pw.println("Correu: electro@gmail.com");
                pw.println("____________________________________");
                pw.println("Codi\tNom\t\tStock\tProveidor");
//escrivim linia descriptiva
            return pw;

    }
    
    static void consultarComandes() throws SQLException{
        boolean enrere=false;
        Scanner teclat = new Scanner (System.in);
        File carpeta=new File(PATHCOMANDES2);
        File[] comandes=carpeta.listFiles();
        String consulta = "SELECT prod.Stock, prov.Nom FROM productes prod, proveidor prov WHERE prod.Codi_prov=prov.Codi_pro ORDER BY codi_pro AND Stock";
        PreparedStatement ps = connectarBD.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
           Stock = rs.getInt(1);
           Proveidor = rs.getString(2);
        }
        
        if (comandes == null || comandes.length == 0){
            System.out.println("No hi han comandes creades");
            return;
        }else{  
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for(int i=0; i<comandes.length; i++){
               
                File comanda = comandes[i];
                System.out.println(String.format("%s (%s) - %s",
                comanda.getName(),
                comanda.isDirectory() ? "Carpeta" : "Arxiu" ,
                sdf.format(comanda.lastModified())
                ));
                
            }
            System.out.println("\nHi han " + comandes.length + " comandes creades");
        }
        }
               
        
        
    }
