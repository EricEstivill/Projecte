package gestorNotes;

public class utils_arrays_dobles {
     static void visualitzarArrayNotes(double [] notes){
            //visualitzem valors de l'array
             System.out.println("\nNumero de notes " +notes.length);
            for (int i=0;i<notes.length;i++){
            System.out.print(notes[i] + " ");
        }
                System.out.println();
            }
            
            static void minimArrayNotes(double [] notes){
            //calculem la nota minima
            double minim=notes[0];
            for (int i=0;i<notes.length;i++){
                if(notes[i]<minim){
                    minim=notes[i];
               }
              }
              System.out.println("\nminim: " + minim);
            }
            static void maximArrayNotes(double [] notes){
            //calculem la nota maxima
            double maxim=notes[0];
            for (int i=0;i<notes.length;i++){
             if(notes[i]>maxim){
                    maxim=notes[i];
                }
            }
            System.out.println("maxim" + maxim);
            }
            static void mitjanaArrayNotes(double [] notes){
            //calculem la nota mitjana
            double mitja=0, suma=0;
            for (int i=0;i<notes.length;i++){
                suma=suma+notes[i];
            }
                mitja=suma/notes.length;
                System.out.println("mitjana" + mitja);
    
    }
    
}
