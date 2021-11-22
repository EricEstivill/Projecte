package gestor_notes_utils;

import static utils_de_arrays.utils_arrays_dobles.maximArrayNotes;
import static utils_de_arrays.utils_arrays_dobles.minimArrayNotes;
import static utils_de_arrays.utils_arrays_dobles.mitjanaArrayNotes;
import static utils_de_arrays.utils_arrays_dobles.visualitzarArrayNotes;


public class gestor_notes_utils {
      public static void main (String[] args){
            //04/11/2021
           double notes[] = {4.5,8.1,3.4,2.8,9.9,3.2,10};

           visualitzarArrayNotes(notes);
           minimArrayNotes(notes);
           maximArrayNotes(notes);
           mitjanaArrayNotes(notes);
     }
                     
    
}
