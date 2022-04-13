package frontsnapk1ck.io;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

import javax.imageio.ImageIO;

public class Serializer {

    @SuppressWarnings("unchecked")
    public static <K> K deserialize(String data)
    {
        byte[] dataBytes = Base64.getDecoder().decode(data);
        try (
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dataBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ) {
            Object in = objectInputStream.readObject();
            return (K) in;
        } catch (IOException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String serializeB64(Serializable obj)
    {
        try
        {
            byte[] arr = toByteArray(obj);
            return Base64.getEncoder().encodeToString(arr);
        }
        catch (Exception e)
        {
            return "UNABLE TO SERIALIZE";
        }
    }

    public static <T extends Serializable> byte[] toByteArray( T obj) 
    {
        try (
            ByteArrayOutputStream bStream = new ByteArrayOutputStream ();
            ObjectOutputStream oStream = new ObjectOutputStream( bStream )
        ) {
            oStream.writeObject ( obj );
            byte[] byteVal = bStream.toByteArray();
            return byteVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <K> K deserializeB64(String data)
    {
        byte[] dataBytes = Base64.getDecoder().decode(data);
        try (
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dataBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ) {
            Object in = objectInputStream.readObject();
            return (K) in;
        } catch (IOException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String serializeImage(RenderedImage e) 
    {
        try ( 
            ByteArrayOutputStream os = new ByteArrayOutputStream();
        ) {

            ImageIO.write((RenderedImage) e, "jpg", os);
            byte[] data = os.toByteArray();
            String b64 = Base64.getEncoder().encodeToString(data);
            return b64;
        } catch ( IOException ex)
        {
            ex.printStackTrace();
        }
        return "UNABLE TO SERIALIZE";
    }

    public static Object readObjectFromFile(File file) 
    {
        return readObjectFromFile(file , Object.class);
    }

    @SuppressWarnings("unchecked")
    public static  <T> T readObjectFromFile(File file, Class<T> type) 
    {
        try (
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
        ){
            Object object = oos.readObject();
            if (object == null) return null;
            if ( object.getClass().isAssignableFrom(type))
                return (T)object;

        } catch (IOException | ClassNotFoundException e) 
        {
        } 
        return null;
    }

    public static <T> boolean saveValueToFile(File file, T save)
    {
        try (
            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis)
        ) {
            oos.writeObject( save );

            oos.flush();
            oos.close();
            return true;
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return false;
    }

    
    
}
