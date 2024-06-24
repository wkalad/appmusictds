package persistencia;

import java.util.HashMap;

public class PoolDAO {
	
    private static PoolDAO unicaInstancia;
    private HashMap<Integer, Object> pool;

    private PoolDAO() {
        pool = new HashMap<Integer, Object>();
    }

    public static PoolDAO getUnicaInstancia() {
        if (unicaInstancia == null) unicaInstancia = new PoolDAO();
        return unicaInstancia;
        
    }
    
    public Object getObjeto(int id) {
        return pool.get(id);
    }

    public void addObjeto(int id, Object objeto) {
        pool.put(id, objeto);
    }

    public boolean contains(int id) {
        return pool.containsKey(id);
    }
}
