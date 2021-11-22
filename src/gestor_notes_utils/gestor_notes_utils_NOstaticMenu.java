package gestor_notes_utils;

import utils_de_arrays.utils_arrays_dobles_NOstatic;

import java.util.Scanner;

public class gestor_notes_utils_NOstaticMenu {
      public static void main (String[] args){
          Scanner teclat = new Scanner (System.in);
          boolean sortir=false;
            //04/11/2021
            do{
           double notes[] = {4.5,8.1,3.4,2.8,9.9,3.2,10};
           System.out.println("^^^^MENU GESTOR NOTES^^^^");
           System.out.println("1.Visualitzar Notes");
           System.out.println("2.Visualitzar nota maxima");
           System.out.println("3.Visualitzar nota minima");
           System.out.println("4.Visualitzar la nota mitjana");
           System.out.println("5.Sortir");
           System.out.println("\nTria una de les opcions");
           
           int opcio=teclat.nextInt();
           
           switch (opcio){
               case 1:
                   utils_de_arrays.utils_arrays_dobles.visualitzarArrayNotes(notes);
                   break;
               case 2:
                   utils_de_arrays.utils_arrays_dobles.maximArrayNotes(notes);
                   break;
               case 3:
                   utils_de_arrays.utils_arrays_dobles.minimArrayNotes(notes);
                   break;
               case 4:
                   utils_de_arrays.utils_arrays_dobles.mitjanaArrayNotes(notes);
                   break;
               case 5:
                   sortir=true;
                   break;
               default:
                   System.out.println("L'Opció no és vàlida");
            }
           
      
          System.out.println(("opció: ")+ opcio); 
          teclat.nextInt();
          utils_de_arrays.utils_arrays_dobles.visualitzarArrayNotes(notes);
          utils_de_arrays.utils_arrays_dobles.minimArrayNotes(notes);
          utils_de_arrays.utils_arrays_dobles.maximArrayNotes(notes);
          utils_de_arrays.utils_arrays_dobles.mitjanaArrayNotes(notes);
      } while (!sortir);
            
     }
      
}
                     
    

