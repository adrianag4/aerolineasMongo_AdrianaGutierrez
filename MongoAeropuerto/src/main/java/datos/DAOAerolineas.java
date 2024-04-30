package datos;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import interfaces.IAerolineasDAO;
import java.util.ArrayList;
import java.util.List;
import modelos.Aerolinea;
import org.bson.types.ObjectId;

/**
 *
 * @author adria
 */
public class DAOAerolineas implements IAerolineasDAO{
    
    private MongoCollection getCollection(){
        ConexionBD conexion = new ConexionBD();
        MongoDatabase database = conexion.crearConexion();
        MongoCollection collection = database.getCollection("airlines", Aerolinea.class);
        return collection;
    }

//    MongoClient mongoClient;
//    MongoDatabase database;
//    MongoCollection<Document> collection;

//    public DAOAerolineas() {
//        mongoClient = new MongoClient();
//        database = mongoClient.getDatabase("airport");
//        collection = database.getCollection("airlines");
//    }

//    public ArrayList<Aerolinea> obtenerAerolineas() {
//        ArrayList<Aerolinea> aerolineas = new ArrayList();
//        MongoCursor<Document> cursor = collection.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                Document d = cursor.next();
//                Aerolinea a = new Aerolinea(d.getObjectId("_id"), d.getString("name"), d.getString("country"),
//                        d.getString("currency"), d.getBoolean("lowcost") == null ? false : true);
//                aerolineas.add(a);
//                // System.out.println(cursor.next().toJson());
//            }
//        } finally {
//            cursor.close();
//        }
//        return aerolineas;
//    }

//    public void agregarAerolinea(Aerolinea aerolinea) {
//        Document d = new Document("name", aerolinea.getNombre())
//                .append("country", aerolinea.getPais())
//                .append("currency", aerolinea.getMoneda())
//                .append("lowcost", aerolinea.isEconomica()
//                );
//        collection.insertOne(d);
//    }

//    public void eliminarAerolinea(String id) {
//        collection.deleteOne(new Document("_id", new ObjectId(id)));
//    }

    @Override
    public boolean agregar(Aerolinea a) {
        try {
            this.getCollection().insertOne(a);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Aerolinea a) {
        try {
            UpdateResult result = this.getCollection().updateOne(eq("_id", a.getId()), 
                    combine(set("name",a.getNombre()),
                    set("country", a.getPais()), 
                    set("currency", a.getMoneda()),
                    set("lowcost", a.isEconomica())));
            return result.getModifiedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(Aerolinea a) {
        try {
            DeleteResult result = this.getCollection().deleteOne(eq("_id", a.getId()));
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Aerolinea consultar(Aerolinea a) {
        try {
            Aerolinea result = (Aerolinea) this.getCollection().find(eq("_id", a.getId())).first();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Aerolinea> consultarTodos() {
        List<Aerolinea> aerolineas = new ArrayList<>();
        try {
            MongoCursor<Aerolinea> cursor = this.getCollection().find().iterator();
            while(cursor.hasNext()){
                aerolineas.add(cursor.next());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aerolineas;
    }

}
