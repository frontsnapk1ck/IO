package frontsnapk1ck.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class StreamInputUtil {
	
	private static InputStream inputStream = System.in;
	private static OutputStream outputStream = System.out;
	private static Scanner scanner = new Scanner( inputStream );

	//=================================================
	//				Streams
	//=================================================

	public static void useInputStream( InputStream stream)
	{
		synchronized ( scanner )
		{
			inputStream = stream;
			scanner = new Scanner(inputStream);
		}
	}

	public static InputStream getInputStream( )
	{
		return inputStream;
	}

	public static void useOutputStream( OutputStream stream)
	{
		synchronized ( outputStream )
		{
			outputStream = stream;
		}
	}

	public static OutputStream getOutputStream() 
	{
		return outputStream;
	}
	
	//=================================================
	//				String
	//=================================================

	public static String getLetters ()
	{
		return handleGettingUserLetters( "" , "Invalid String");
	}

	public  static String getLetters ( String prompt )
	{
		return handleGettingUserLetters(prompt, "Invalid String");
	}

	public static  String getLetters (String prompt , String error)
	{
		return handleGettingUserLetters(prompt, error);
	}

	/**
	 * 
	 * @param prompt the message to be displayed to the user upon every attempt to input an integer
	 * @param error the message to be shown to the user upon every failed input attempt
	 * @return the string the user input if it is only letters
	 */
	private static String handleGettingUserLetters( String prompt , String error )
	{
        try {
            byte[] promptArr = Serializer.toByteArray(prompt);
            byte[] errorArr = null;
            outputStream.write(promptArr);
            outputStream.flush();
            String in = scanner.nextLine();
            while (!onlyLetters(in))
            {
                if ( errorArr == null)
                    errorArr = Serializer.toByteArray(error);
                outputStream.write(errorArr);
                outputStream.write(promptArr);
                outputStream.flush();
                in = scanner.nextLine();
            }
            return in;
        }
        catch (IOException e) 
        {
            throw new RuntimeException(e);
        }
	}

	//=======================================================
	//			Int
	//======================================================

	public static int getUserInt()
	{
		return handleGettingUserInt("", "Invalid Number");
	}

	public static int getUserInt(String prompt) 
	{
		return handleGettingUserInt(prompt, "Invalid Number");
	}

	public static int getUserInt(String prompt , String error)
	{
		return handleGettingUserInt(prompt, error);
	}

	/**
	 * @param prompt the message to be displayed to the user upon every attempt to input an integer
	 * @param error the message to be shown to the user upon every failed input attempt
	 * @return the {@link Integer} the user inputted
	 */
	private static int handleGettingUserInt(String prompt , String error)
	{
        try {
            byte[] promptArr = Serializer.toByteArray(prompt);
            byte[] errorArr = null;
            outputStream.write(promptArr);
            outputStream.flush();
            String 	in = scanner.nextLine();
            while (!validNum(in))
            {
                if ( errorArr == null)
                    errorArr = Serializer.toByteArray(error);
                outputStream.write(errorArr);
                outputStream.write(promptArr);
                outputStream.flush();
                in = scanner.nextLine();
            }
            return Integer.parseInt(in);
        }
        catch (IOException e) 
        {
            throw new RuntimeException(e);
        }
	}

	//=======================================================
	//			CHAR
	//======================================================

	/**
	 * runs a program that gets a letter and doesn't do so until a valid character is provided
	 * @return single uppercase letter
	 */
	public static char getUserChar()
	{
		return handleGettingUserChar( "", "Invalid Char" );
	}
	
	public static char getUserChar(String prompt)
	{
		return handleGettingUserChar( prompt, "Invalid Char" );
	}
	
	public static char getUserChar(String prompt, String error)
	{
		return handleGettingUserChar( prompt, error );
	}
	
	/**
	 * 
	 * @param prompt the message to be shown to the user on every attempt
	 * @param error the message to be shown to the user upon every failed input attempt
	 * @return the {@link Char} the user inputted 
	 */
	private static char handleGettingUserChar(String prompt, String error)
	{
        try {
            byte[] promptArr = Serializer.toByteArray(prompt);
            byte[] errorArr = null;
            outputStream.write(promptArr);
            outputStream.flush();
            String in = scanner.nextLine();
            in = in.toUpperCase();

            boolean valid = onlyLetters(in) && in.length() <=1 && in.length() != 0;
            while (!valid)
            {
                if ( errorArr == null)
                    errorArr = Serializer.toByteArray(error);
                outputStream.write(errorArr);
                outputStream.write(promptArr);
                outputStream.flush();
                in = scanner.nextLine();
                in = in.toUpperCase();
                valid =	onlyLetters(in) 	&& in.length() <=1 && in.length() != 0;
            }
            return in.charAt(0);
        }
        catch (IOException e) 
        {
            throw new RuntimeException(e);
        }
	}

	//====================================================
	//			Utility
	//====================================================

	/** 
	 * waits until the user presses the Enter key
	 */
	public static void next() 
	{
		scanner.nextLine();
	}

	/**
	 * shuts down this class by closing the {@link Scanner} then setting it to <code> Null </code>
	 */
	public static void shutdown()
	{
		if ( scanner == null )
			return;
		scanner.close();
		scanner = null;
	}

	/**
	 * resets this class by preforming a <code>shutdown</code> the {@link Scanner} and then recreating it
	 */
	public static void reset() 
	{
		shutdown();
		scanner = new Scanner(System.in);
	}

	//===========================================
	//		Checkers
	//===========================================

	/**
	 * 
	 * @param temp the string to be checked against
	 * @return if the string passed can be parsed to an {@link Integer}
	 */
	private static boolean validNum(String temp) 
	{
		try {
			Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param s {@link String} to be checked
	 * @return if the {@link String} is only letters
	 */
	private static boolean onlyLetters(String s)
	{
		int numLetters = -1;
		for (int i = 0; i < s.length(); i++)
		{
			if (Character.isLetter(s.charAt(i)))
				numLetters ++;
		}
		return numLetters == s.length() -1;
	}
}
