package gestorNotes;

import utils_de_arrays.utils_arrays_dobles_NOstatic;

public class gestor_de_notes_utils {
    //variable global: accessible des de tots els mètodes
                static double notes[] = {4.5,8.1,3.4,2.8,9.9,3.2,10};
                
     public static void main (String[] args){
            utils_arrays_dobles_NOstatic utilArray = new utils_arrays_dobles_NOstatic();
            utilArray.visualitzarArrayNotes(notes);
            utilArray.maximArrayNotes(notes);
            utilArray.minimArrayNotes(notes);
            utilArray.mitjanaArrayNotes(notes);
//04/11/2021 
     }
        
    }



    

