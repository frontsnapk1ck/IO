package frontsnapk1ck.io;

import java.util.List;

/**
 * @param T the type of the thing your are saving
 * @param F the file to which you are saving
 */
public abstract class SaverJSON <T , F > {

    public boolean save( F file , T save ) 
    {
        throw new RuntimeException("Unimplemented method `save()`");
    };
    
    public boolean saveAll( F folder , List<T> save )
    {
        throw new RuntimeException("Unimplemented method `saveAll()`");
    }
    
}
