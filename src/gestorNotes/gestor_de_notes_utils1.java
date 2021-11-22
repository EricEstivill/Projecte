package gestorNotes;

public class gestor_de_notes_utils1 {
    //variable global: accessible des de tots els mètodes
                static double notes[] = {4.5,8.1,3.4,2.8,9.9,3.2,10};
                
     public static void main (String[] args){
            //04/11/2021
            visualitzarArrayNotes();
            minimArrayNotes();
            maximArrayNotes();
            mitjanaArrayNotes();
     }
            
            static void visualitzarArrayNotes(){
            //visualitzem valors de l'array
             System.out.println("\nNumero de notes " +notes.length);
            for (int i=0;i<notes.length;i++){
            System.out.print(notes[i] + " ");
        }
                System.out.println();
            }
            
            static void minimArrayNotes(){
            //calculem la nota minima
            double minim=notes[0];
            for (int i=0;i<notes.length;i++){
                if(notes[i]<minim){
                    minim=notes[i];
               }
              }
              System.out.println("\nminim: " + minim);
            }
            static void maximArrayNotes(){
            //calculem la nota maxima
            double maxim=notes[0];
            for (int i=0;i<notes.length;i++){
             if(notes[i]>maxim){
                    maxim=notes[i];
                }
            }
            System.out.println("maxim" + maxim);
            }
            static void mitjanaArrayNotes(){
            //calculem la nota mitjana
            double mitja=0, suma=0;
            for (int i=0;i<notes.length;i++){
                suma=suma+notes[i];
            }
                mitja=suma/notes.length;
                System.out.println("mitjana" + mitja);
    
    }
}


    

