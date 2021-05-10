package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {

        PersonaDAO personaDAO = new PersonaDAO();

        //Insertando un nuevo objeto de tipo Persona
        Persona personaNueva = new Persona("Pakan", "Locococo", "PLoco@mail.com", "778787878");
        personaDAO.insertar(personaNueva);
        
        //Elimina registro según id
//        Persona personaEliminar = new Persona(8);
//        personaDAO.eliminar(personaEliminar);

        //Actualiza registro existente según id
//        Persona personaActualizar = new Persona(5, "Hei hei", "Obregon", "HHObregon@mail.com", "5588774411");
//        personaDAO.actualizar(personaActualizar);
        

        List<Persona> personas = personaDAO.seleccionar();

//        for(Persona persona: personas){
//            System.out.println("persona = " + persona);
//        }
        personas.forEach(persona -> {
            System.out.println("persona = " + persona);
        });

    }
}
