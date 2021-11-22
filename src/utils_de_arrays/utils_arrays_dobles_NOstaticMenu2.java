package utils_de_arrays;

public class utils_arrays_dobles_NOstaticMenu2 {
     public void visualitzarArrayNotes(double [] notes){
            //visualitzem valors de l'array
             System.out.println("\nNumero de notes " +notes.length);
            for (int i=0;i<notes.length;i++){
            System.out.print(notes[i] + " ");
        }
                System.out.println();
            }
            
            public void minimArrayNotes(double [] notes){
            //calculem la nota minima
            double minim=notes[0];
            for (int i=0;i<notes.length;i++){
                if(notes[i]<minim){
                    minim=notes[i];
               }
              }
              System.out.println("\nminim: " + minim);
            }
            public void maximArrayNotes(double [] notes){
            //calculem la nota maxima
            double maxim=notes[0];
            for (int i=0;i<notes.length;i++){
             if(notes[i]>maxim){
                    maxim=notes[i];
                }
            }
            System.out.println("maxim" + maxim);
            }
            public void mitjanaArrayNotes(double [] notes){
            //calculem la nota mitjana
            double mitja=0, suma=0;
            for (int i=0;i<notes.length;i++){
                suma=suma+notes[i];
            }
                mitja=suma/notes.length;
                System.out.println("mitjana" + mitja);
    
    }
    
}
