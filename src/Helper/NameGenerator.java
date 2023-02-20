package Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NameGenerator 
{
	private Random randomizer;
	private File firstFile;
	private File lastFile;
	private File nickFile;
	private boolean enabledNicknames;
	private static int NICKNAME_CHANCE = 5;
	public NameGenerator(File firstFile, File lastFile, File nickFile)
	{
		//Assign values if the exist
		if(firstFile.exists() && lastFile.exists())
		{
			this.firstFile = firstFile;
			this.lastFile = lastFile;
			this.nickFile = nickFile;
			randomizer = new Random();
			enabledNicknames = false;
		}
		else
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\n----------NAME GENERATOR----------");
		}
	}
	
	public String getRandomName()
	{
		try {
			//Init
			Scanner fnScanner = new Scanner(firstFile);
			Scanner lnScanner = new Scanner(lastFile);
			ArrayList<String> firstNames = new ArrayList<String>();
			ArrayList<String> lastNames = new ArrayList<String>();
			String result;
			int fnCount = 0;
			int lnCount = 0;
			
			//Add to array list and count
			while(fnScanner.hasNext())
			{
				firstNames.add(fnScanner.nextLine());
				fnCount++;
			}
			while(lnScanner.hasNext())
			{
				lastNames.add(lnScanner.nextLine());
				lnCount++;
			}
			
			//Get random name
			int fnRandom = randomizer.nextInt(fnCount);
			int lnRandom = randomizer.nextInt(lnCount);
			
			//Close scanner
			fnScanner.close();
			lnScanner.close();
			
			//Nicknames enabled
			if(enabledNicknames && randomizer.nextInt(100)<NICKNAME_CHANCE)
			{
				ArrayList<String> nickNames = new ArrayList<String>();
				Scanner nickScanner = new Scanner(nickFile);
				int nickCount = 0;
				while(nickScanner.hasNext())
				{
					nickNames.add(nickScanner.nextLine());
					nickCount++;
				}
				
				int nickRandom = randomizer.nextInt(nickCount);
				//Nickname return
				result = firstNames.get(fnRandom) + " \"The " + nickNames.get(nickRandom) + "\" " + lastNames.get(lnRandom);
				nickScanner.close();
			}
			else
			{
				//No nickname return
				result = firstNames.get(fnRandom) + " " + lastNames.get(lnRandom);
			}
			return result;
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\nError Info:\n" + e + "\n----------NAME GENERATOR----------\n");
			return null;
		}
		catch(NullPointerException ex)
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\nError Info:\n" + ex + "\n----------NAME GENERATOR----------\n");
			return null;
		}
	}
	
	public String getFirstName()
	{
		ArrayList<String> Names = new ArrayList<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(firstFile);
		
			int nickCount = 0;
			while(scanner.hasNext())
			{
				Names.add(scanner.nextLine());
				nickCount++;
			}
			//Random value
			int random = randomizer.nextInt(nickCount);
		
			//Nickname return
			String result = Names.get(random);
			scanner.close();
			return result;
		} 
		catch (FileNotFoundException e)
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\nError Info:\n" + e + "\n----------NAME GENERATOR----------\n");
			return null;
		}
		catch(NullPointerException ex)
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\nError Info:\n" + ex + "\n----------NAME GENERATOR----------\n");
			return null;
		}
	}
	
	public String getLastName()
	{
		ArrayList<String> Names = new ArrayList<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(lastFile);
		
			int nickCount = 0;
			while(scanner.hasNext())
			{
				Names.add(scanner.nextLine());
				nickCount++;
			}
			//Random value
			int random = randomizer.nextInt(nickCount);
		
			//Nickname return
			String result = Names.get(random);
			scanner.close();
			return result;
		} 
		catch (FileNotFoundException e)
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\nError Info:\n" + e + "\n----------NAME GENERATOR----------\n");
			return null;
		}
		catch(NullPointerException ex)
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\nError Info:\n" + ex + "\n----------NAME GENERATOR----------\n");
			return null;
		}
	}
	
	public String getNickName()
	{
		ArrayList<String> Names = new ArrayList<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(nickFile);
		
			int nickCount = 0;
			while(scanner.hasNext())
			{
				Names.add(scanner.nextLine());
				nickCount++;
			}
			//Random value
			int random = randomizer.nextInt(nickCount);
		
			//Nickname return
			String result = "\"" + Names.get(random) + "\"";
			scanner.close();
			return result;
		} 
		catch (FileNotFoundException e)
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\nError Info:\n" + e + "\n----------NAME GENERATOR----------\n");
			return null;
		}
		catch(NullPointerException ex)
		{
			System.err.println("----------NAME GENERATOR----------\nFILE(S) PROVIDED DO(ES) NOT EXIST\nError Info:\n" + ex + "\n----------NAME GENERATOR----------\n");
			return null;
		}
	}
	
	public void nicknameProperty(boolean bValue) 
	{
		this.enabledNicknames = bValue;
	}
}
