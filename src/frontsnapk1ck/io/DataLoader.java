package frontsnapk1ck.io;

import java.util.List;

/**
 * <p>T: type of object being loaded </p>
 * <p>F: type of file being loaded</p>
 */
public abstract class DataLoader< T , F >  {

    /**
     * loads a single inherited type at a given file type
     * 
     * @param file the location of what is being loaded
     * @return the thing requested
     */
    public T load( F file )
    {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * loads all of the inherited types at a given file type 
     * 
     * @param file the location of the tings being loaded
     * @return a list of all the tings at that location
     */
    public List<T> loadAll( F file)
    {
        throw new RuntimeException("Not Implemented");
    }
    
}
