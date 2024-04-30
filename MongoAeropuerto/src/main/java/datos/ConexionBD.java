package datos;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import interfaces.IConexionDB;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author adria
 */
public class ConexionBD implements IConexionDB{
    
    MongoClient mongoClient;
    MongoDatabase database;

    public ConexionBD() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
        mongoClient = (MongoClient) MongoClients.create(settings);
        database = mongoClient.getDatabase("airport");
        
    }

    @Override
    public MongoDatabase crearConexion() {
        return database;
    }
    
    
}
