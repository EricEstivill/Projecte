package gestio_temperatures;

import static utils_de_arrays.utils_arrays_dobles.maximArrayNotes;
import static utils_de_arrays.utils_arrays_dobles.minimArrayNotes;
import static utils_de_arrays.utils_arrays_dobles.mitjanaArrayNotes;
import static utils_de_arrays.utils_arrays_dobles.visualitzarArrayNotes;

public class gestor_de_temperatures {
    public static void main (String[] args){
        //temperatures a Tàrrega dels ultims 7 dies a les 10
        double[] temperatures={13.6, 15.4, 11.6, 13.6, 16.8};
        
        //Estudi de les temperatures de Tarrega
        
         visualitzarArrayNotes(temperatures);
         minimArrayNotes(temperatures);
         maximArrayNotes(temperatures);
         mitjanaArrayNotes(temperatures);
        
    }
    
}
