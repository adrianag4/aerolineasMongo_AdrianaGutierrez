package com.mycompany.mongoaeropuertoo;

import datos.Aerolineas;
import datos.DAOAerolineas;
import java.util.ArrayList;
import modelos.Aerolinea;
import org.bson.types.ObjectId;

/**
 *
 * @author Adriana 
 */
public class MongoAeropuertoo {

    public static void main(String[] args) {
        
        Aerolineas aerolineas = new Aerolineas();
        ArrayList<Aerolinea> lista = aerolineas.getAllAirlines();
        
        Aerolinea insertar = new Aerolinea(new ObjectId(),"Air Canada","CAN","CAD", false);
        
        Aerolinea actualizar = new Aerolinea(new ObjectId("6621cbc470ea5f5a8550f125"),"American Airlines","USA","USD", false);
        
        Aerolinea eliminar = new Aerolinea(new ObjectId("6621cb5c70ea5f5a8550f124"));
        
        DAOAerolineas dao = new DAOAerolineas();
       // dao.agregar(insertar);
       //dao.actualizar(actualizar);
//       boolean eli = dao.eliminar(eliminar);
//       
//       if(eli){
//           System.out.println("Aerolinea eliminada");
//       }else{
//           System.out.println("La aerolinea no ha sido eliminada o no existe el id.");
//       }

        Aerolinea busqueda = dao.consultar(actualizar);
        System.out.println(busqueda.toString());
        
        if(busqueda == null){
            System.out.println("No se encontró ningún resultado");
        }else{
            System.out.println(busqueda.toString());
        }
        
        dao.consultarTodos();
//        DAOAerolineas a = new DAOAerolineas();
//        ArrayList<Aerolinea> lista = a.obtenerAerolineas();
//        Aerolinea aerolinea = new Aerolinea("Viva Aerobus", "MX", "MXN", true); 
//        
//        //Mostrar lista de aerolineas
//        for (Aerolinea arg : lista) {
//            System.out.println(arg.toString());
//        }
//        
//        //Agregar aerolinea
//        a.agregarAerolinea(aerolinea);
//        System.out.println("Aerolinea agregada: \n" + aerolinea.toString());
//        
//        //Eliminar aerolinea
//        String eliminar = "6621cb5c70ea5f5a8550f123";
//        a.eliminarAerolinea(eliminar);
//        System.out.println("Se eliminó la aerolinea con el id: " +eliminar);
    }
}
