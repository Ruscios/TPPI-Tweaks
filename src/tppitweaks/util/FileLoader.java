package tppitweaks.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import tppitweaks.TPPITweaks;
import tppitweaks.lib.Reference;

public class FileLoader
{
	private static InputStream bookText, supportedMods;
	
	public static void init(File file, int attempt) throws IOException
	{
		File dir = new File(file.getParent() + "");
		if (!dir.exists())
			dir.mkdir();
		
		/* 
		 * From here down commented out as it was 
		 * deemed useless and counter-productive. 
		 * If we add versioning for user-added content,
		 * this becomes useful again 
		 */
		
		/*
		try
		{
			bookText = new FileInputStream(new File(file.getParent() + "/BookText.txt"));
		}
		catch(FileNotFoundException e)
		{
			Scanner scan = new Scanner(TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/BookText.txt"));
			
			FileWriter newBookText = new FileWriter(new File(file.getParent() + "/BookText.txt"));
			
			while (scan.hasNext())
			{
				newBookText.write(scan.nextLine() + "\n");
			}
			
			scan.close();
			newBookText.flush(); 
			newBookText.close();
			
			if (attempt < 1)
				init(file, 1);
			else
			{
				System.err.println("TPPI Tweaks - IO Error, books will be partly non-functional. \n");
				e.printStackTrace();
			}
		}
		
		try
		{
			supportedMods = new FileInputStream(new File(file.getParent() + "/SupportedMods.txt"));
		}
		catch(FileNotFoundException e)
		{
			Scanner scan = new Scanner(TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/SupportedMods.txt"));
			
			FileWriter newBookText = new FileWriter(new File(file.getParent() + "/SupportedMods.txt"));
			
			while (scan.hasNext())
			{
				newBookText.write(scan.nextLine() + "\n");
			}
			
			scan.close();
			newBookText.flush(); 
			newBookText.close();
			
			if (attempt < 3)
				init(file, 2);
			else
			{
				System.err.println("TPPI Tweaks - IO Error, books will be partly non-functional. \n");
				e.printStackTrace();
			}
		}
		*/
	}
	
	public static void disableTT()
	{
		for(File f : getTT()) {
			if(!f.getName().contains(".removed")) {
				f.renameTo(new File(f.getAbsolutePath()+".removed"));
			}
		}
	}
	
	public static void enableTT()
	{
		for(File f : getTT()) {
			if(f.getName().contains(".removed")) {
				f.renameTo(new File(f.getAbsolutePath().replace(".removed", "")));
			}
		}
		
	}
	
	private static ArrayList<File> getTT()
	{
		ArrayList<File> thaumicTinkererFiles = new ArrayList<File>();
		File modJar;
		
		modJar = new File(Reference.modsFolder, Reference.TTFilename);
		if(modJar.exists()) {
			thaumicTinkererFiles.add(modJar);
		}
				
		modJar = new File(Reference.modsFolder, Reference.KAMIFilename);
		if(modJar.exists()) {
			thaumicTinkererFiles.add(modJar);
		}
				
		return thaumicTinkererFiles;
	}
	
	public static void getThaumicTinkererFilenameState() {
		
		File modJar;
		
		modJar = new File(Reference.modsFolder, Reference.TTFilename).exists() ? new File(Reference.modsFolder, Reference.TTFilename) : new File(Reference.modsFolder, Reference.TTFilename + ".disabled");
		if(modJar.exists()) {
			Reference.TTFilename = modJar.getName();
		}
		
		modJar = new File(Reference.modsFolder, Reference.KAMIFilename).exists() ? new File(Reference.modsFolder, Reference.KAMIFilename) : new File(Reference.modsFolder, Reference.KAMIFilename + ".disabled");
		if(modJar.exists()) {
			Reference.KAMIFilename = modJar.getName();
		}
		
	}

	public static Object manuallyGetConfigValue(Map<String, Object> m, String string, Object type) {
		if (type instanceof Boolean)
		{
			File config = new File(((File) m.get("mcLocation")).getAbsolutePath() + "/config/TPPI/TPPITweaks.cfg");
			boolean noConfig = false;
			Scanner scan = null;
			
			try {
				scan = new Scanner(config);
			} catch (FileNotFoundException e) {
				noConfig = true;
			}	
			
			if (noConfig)
				return true;
			
			while (scan.hasNext())
			{
				String s = scan.next();
				
				if (s.contains(string))
				{
					if (s.contains("true"))
						return true;
					else
						return false;
				}
			}
		}
		return false;
	}
	
	public static InputStream getBookText()
	{
		bookText = TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/BookText.txt");
		return bookText;
	}
	
	public static InputStream getSupportedMods()
	{
		supportedMods = TPPITweaks.class.getResourceAsStream("/assets/tppitweaks/lang/SupportedMods.txt");
		return supportedMods;
	}
}
