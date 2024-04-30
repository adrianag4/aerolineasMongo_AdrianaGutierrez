package interfaces;

import com.mongodb.client.MongoDatabase;

/**
 *
 * @author adria
 */
public interface IConexionDB {

    public MongoDatabase crearConexion();

}
