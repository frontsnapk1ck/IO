package frontsnapk1ck.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {

    /**
     * appends information to the end of a specified file
     * 
     * @param path the path of the file that is being appended to
     * @param save the information that is bing appended to the file
     */
    public static void saveAppend (String path , String save)
    {
        saveAppend(new File(path), save);
    }

    /**
     * appends information to the end of a specified file
     * 
     * @see FileReader#read(File)
     * @param path the path of the file that is being appended to
     * @param save the information that is bing appended to the file
     */
    public static void saveAppend(File file, String save) 
    {
        try {
            String[] arr = FileReader.read(file);

            String old = "";

            for (String string : arr) 
                old += string + "\n";

            FileWriter fw = new FileWriter(file);
            fw.write(old);
            fw.append(save);

            fw.close();

        } catch (IOException e) 
        {
            String className = e.getClass().getSimpleName();
            String fN = file.getAbsolutePath();
            String message = String.format(
                "An %s error occurred while appending to file %s" , 
                className , 
                fN
            );
            System.err.println(message);
            e.printStackTrace();
        }
    }

        /**
     * takes in data and saves that data to a file that it also given
     * this method returns what was previously in that file 
     * 
     * @param file the path of the file in which the new data is to
     *              be overwritten
     * @param save the data that is going to be saved in the new file
     *              split by newlines
     * @return the contents of the file before being overwritten
     */
    public static void saveOverwrite (File path , String save)
    {
        saveOverwrite(path, save , "\n");
    }

    /**
     * takes in data and saves that data to a file that it also given
     * this method returns what was previously in that file 
     * 
     * @param file the path of the file in which the new data is to
     *              be overwritten
     * @param save the data that is going to be saved in the new file
     * @param delimiter the token the string is to be split with
     * @return the contents of the file before being overwritten
     */
    public static void saveOverwrite (File path , String save, String delimiter)
    {
        String[] arr = save.split(delimiter);
        saveOverwrite(path, arr);
    }

    /**
     * takes in data and saves that data to a file that it also given
     * this method returns what was previously in that file 
     * 
     * @param file the path of the file in which the new data is to
     *              be overwritten
     * @param save the data that is going to be saved in the new file
     *              split by newlines
     * @return the contents of the file before being overwritten
     */
    public static void saveOverwrite (String path , String save)
    {
        saveOverwrite(new File(path), save , "\n");
    }

    /**
     * takes in data and saves that data to a file that it also given
     * this method returns what was previously in that file 
     * 
     * @param file the path of the file in which the new data is to
     *              be overwritten
     * @param save the data that is going to be saved in the new file
     * @param delimiter the token the string is to be split with
     * @return the contents of the file before being overwritten
     */
    public static void saveOverwrite (String path , String save, String delimiter)
    {
        String[] arr = save.split(delimiter);
        saveOverwrite(new File(path), arr);
    }

    /**
     * takes in data and saves that data to a file that it also given
     * this method returns what was previously in that file 
     * 
     * @param file the path of the file in which the new data is to
     *              be overwritten
     * @param save the data that is going to be saved in the new file
     * @return the contents of the file before being overwritten
     */
    public static void saveOverwrite (String path , String[] save)
    {
        saveOverwrite(new File(path), save);
    }

    /**
     * takes in data and saves that data to a file that it also given
     * this method returns what was previously in that file 
     * 
     * @see FileReader#read(File)
     * @param file the file in which the new data is to be overwritten
     * @param save the data that is going to be saved in the new file
     * @return the contents of the file before being overwritten
     */
    public static String[] saveOverwrite(File file, String[] save) 
    {
        try 
        {
            String[] prev = null;
            if (file.exists())
                prev = FileReader.read(file);
            FileWriter fw = new FileWriter(file);
            fw.write("");
            
            for (String string : save) 
                fw.append(string + "\n");

            fw.close();
            return prev;
        } catch (IOException e) 
        {
            String className = e.getClass().getSimpleName();
            String fN = file.getAbsolutePath();
            String message = String.format(
                "An %s error occurred while overwriting to file %s" ,
                className ,
                fN
            );
            System.err.println(message);
            e.printStackTrace();
            return null;
        }
    }
    

    /**
     * saves a new file at a given path
     * 
     * @param path the path at which the new file is to be saved
     * @return {@code true} if the file was saved or {@code false} if 
     *          the file was not saved
     */
    public static boolean saveNewFile (String path)
    {
        try 
        {
            return saveNewFile(new File(path));
        }
        catch (Exception e) 
        {
            return false;
        }
    }

    /**
     * saves a new file at a given path
     * 
     * @param file the path at which the new file is to be saved
     * @return {@code true} if the file was saved or {@code false} if 
     *          the file was not saved
     */
    public static boolean saveNewFile(File file) 
    {
        try {
            if (file.createNewFile())
                return true;
            else
                return false;
        } catch (IOException e) {
           System.err.println("an error has occurred");
           e.printStackTrace();
        }
        return false;
    }

    /**
     * <p>takes in a predefined value that is to be saved at a given path 
     * that is passed into the method</p>
     * 
     * @param path the path at which you would like to save the new file
     * @param save the value you would like to save at the new file
     */
    public static void saveNewFile(String path, String save) 
    {
        saveNewFile(path , new String[]{save});
	}

    /**
     * takes in a predefined value that is to be saved at a given path 
     * that is passed into the method
     * 
     * @param path the path at which you would like to save the new file
     * @param save the value you would like to save at the new file
     */
    public static void saveNewFile(String path, String[] save) 
    {
        saveNewFile(new File(path) , save);
	}

    /**
     * takes in a predefined value that is to be saved at a given path 
     * that is passed into the method
     * 
     * @param file the new file in which you would like to save the data
     * @param save the value you would like to save at the new file
     */
    public static void saveNewFile(File file, String[] save) 
    {
        saveNewFile(file);
        saveOverwrite(file, save);
    }

    /**
     * <p>recursively deletes file at a given starting point. </p>
     * <p>be very careful with this method as it will delete everything</p>
     * 
     * @param path the path of the folder with the files that need 
     *              to be deleted
     */
    public static void deleteFiles(String path) 
    {
        deleteFiles(new File(path));
    }

    /**
     * <p>recursively deletes file at a given starting point. </p>
     * <p>be very careful with this method as it will delete everything</p>
     * 
     * @param file the folder with the files that need to be deleted
     */
    public static void deleteFiles(File file) 
    {
        File[] sub = file.listFiles();
        for (File f2 : sub) 
        {
            if (!f2.delete())
                deleteFiles(f2.getPath());
        }
        file.delete();
    }

    /**
     * deletes a file at a given path
     * 
     * @param path the path of the file that you are deleting
     * @return {@code true} if the file was deleted or {@code false} if 
     *          the file was not deleted
     */
    public static boolean deleteFile(String path) 
    {
        return deleteFile(new File(path));
    }

    /**
     * deletes a file at a given path
     * 
     * @param path the path of the file that you are deleting
     * @return {@code true} if the file was deleted or {@code false} if 
     *          the file was not deleted
     */

    public static boolean deleteFile(File file) 
    {
        return file.delete();
    }

    /**
     * takes the value of one file and then saves it to the other file
     * overwriting any previous information that was there
     * 
     * @param from the path of the file that you are copying from
     * @param to the path of the file that you are copying to
     * @return the information that was previously in the {@code to} file
     */
    public static String[] copyFrom(String from, String to) 
    {
        return copyFrom(new File(from), new File (to));
	}

    /**
     * takes the value of one file and then saves it to the other file
     * overwriting any previous information that was there
     * 
     * @see FileReader#read(File)
     * @param from the file that you are copying from
     * @param to the file that you are copying to
     * @return the information that was previously in the {@code to} file
     */
    private static String[] copyFrom(File from, File to) 
    {
        String[] bak = FileReader.read(to);
        String[] out = FileReader.read(from);
        saveOverwrite(to, out);
        return bak;
    }

    /**
     * saves a ner folder at the specified path
     * 
     * @param path the path at which you would like to save a new folder
     * @return {@code true} if the folder was saved or {@code false} if 
     *          the folder was not saved
     */
    public static boolean saveNewFolder(String path) 
    {
        try 
        {
            return saveNewFolder(new File(path));
        }
        catch (Exception e) 
        {
            return false;
        }
    }

    /**
     * saves a ner folder at the specified path
     * 
     * @param file the file at which you would like to save a new folder
     * @return {@code true} if the folder was saved or {@code false} if 
     *          the folder was not saved
     */
    public static boolean saveNewFolder(File file) 
    {
        try {
            if (file.mkdir())
                return true;
            else
                return false;
        } catch (SecurityException e) {
           System.err.println("an error has occurred\nsomething about perms\n\n");
           e.printStackTrace();
        }
        return false;
    }

    /**
     * takes a given file and completely erases all of the information 
     * in that file. returns the output of the {@link FileReader#read(File)}
     * method
     * 
     * @see Saver#clear(File)
     * @see FileReader#read(File)
     * @param path the path of the file in which you would like to 
     *              completely eras
     * @return a string array of the content that was in file
     */
    public static String[] clear(String path) 
    {
        return clear(new File(path));
	}

    /**
     * takes a given file and completely erases all of the information 
     * in that file. returns the output of the {@link FileReader#read(File)}
     * method
     * 
     * @see FileReader#read(File)
     * @param path the path of the file in which you would like to 
     *              completely eras
     * @return a string array of the content that was in file
     */
    public static String[] clear(File file) 
    {
        String[] contents = FileReader.read(file);
        saveOverwrite( file , new String[]{} );
        return contents;
	}



}
