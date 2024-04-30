package interfaces;

import java.util.List;
import modelos.Aerolinea;

/**
 *
 * @author adria
 */
public interface IAerolineasDAO {
    
    public boolean agregar(Aerolinea a);
    
    public boolean actualizar(Aerolinea a);
    
    public boolean eliminar(Aerolinea a);
    
    public Aerolinea consultar(Aerolinea a);
    
    public List<Aerolinea> consultarTodos();
}
