package com;

import java.util.List;
import java.util.Scanner;

//import com.FileHandleManipulations;
//import com.Manipulation;
//import com.Option;
public class FileHandleManipulations {

		public static void handleWelcomeScreenInput() {
			boolean running = true;
			Scanner sc = new Scanner(System.in);
			do {
				try {
					Option.displayMenu();
					int input = sc.nextInt();

					switch (input) {
					case 1:
						Manipulation.displayAllFiles("main");
						break;
					case 2:
						FileHandleManipulations.handleFileMenuOptions();
						break;
					case 3:
						System.out.println("Program exited successfully.");
						running = false;
						sc.close();
						System.exit(0);
						break;
					default:
						System.out.println("Please select a valid option from above.");
					}
				} catch (Exception e) {
					System.out.println(e.getClass().getName());
					handleWelcomeScreenInput();
				} 
			} while (running == true);
		}
		
		public static void handleFileMenuOptions() {
			boolean running = true;
			Scanner sc = new Scanner(System.in);
			do {
				try {
					Option.displayFileMenuOptions();
					Manipulation.createMainFolderIfNotPresent("main");
					
					int input = sc.nextInt();
					switch (input) {
					case 1:
						// File Add
						System.out.println("Enter the name of the file to be added to the \"main\" folder");
						String fileToAdd = sc.next();
						
						Manipulation.createFile(fileToAdd, sc);
						
						break;
					case 2:
						// File/Folder delete
						System.out.println("Enter the name of the file to be deleted from \"main\" folder");
						String fileToDelete = sc.next();
						
						Manipulation.createMainFolderIfNotPresent("main");
						List<String> filesToDelete = Manipulation.displayFileLocations(fileToDelete, "main");
						
						String deletionPrompt = "\nSelect index of which file to delete?"
								+ "\n(Enter 0 if you want to delete all elements)";
						System.out.println(deletionPrompt);
					
						int idx = sc.nextInt();
						
						if (idx != 0) {
							Manipulation.deleteFileRecursively(filesToDelete.get(idx - 1));
						} else {
							
							// If idx == 0, delete all files displayed for the name
							for (String path : filesToDelete) {
								Manipulation.deleteFileRecursively(path);
							}
						}
						

						break;
					case 3:
						// File/Folder Search
						System.out.println("Enter the name of the file to be searched from \"main\" folder");
						String fileName = sc.next();
						
						Manipulation.createMainFolderIfNotPresent("main");
						Manipulation.displayFileLocations(fileName, "main");

						
						break;
					case 4:
						// Go to Previous menu
						return;
					case 5:
						// Exit
						System.out.println("Program exited successfully.");
						running = false;
						sc.close();
						System.exit(0);
					default:
						System.out.println("Please select a valid option from above.");
					}
				} catch (Exception e) {
					System.out.println(e.getClass().getName());
					handleFileMenuOptions();
				}
			} while (running == true);
		}

	

}
