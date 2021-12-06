package ibf2021.d3;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ShoppingCartDB extends Main {
    static String username;


    public void accessDataBase() throws IOException {
        // create path if not exits
        Path database = Paths.get("shopping/cartdb");
        File file = database.toFile();


        if (!Files.isDirectory(database)){
            Files.createDirectories(database);
        }


        Scanner user = new Scanner(System.in);
        System.out.print("Please type \"users\" to check the user list: ");
        while (!user.nextLine().equalsIgnoreCase("users")){
            System.out.print("Please type \"users\" to check the user list: ");
        }

        File[] listOfFiles = file.listFiles();
        assert listOfFiles != null;
        if (listOfFiles.length == 0) {
            System.out.print("There is no user yet, please create: ");
        }
        else{
            for (int i=1; i<=listOfFiles.length; i++){
                String filename = listOfFiles[i-1].getName();
                String filenameWithoutExtension = filename.substring(0, filename.lastIndexOf("."));
                System.out.println(i + ". " + filenameWithoutExtension);
            }
            System.out.print("please login user, or create new user: ");
        }
        username = user.nextLine();
    }



    public void accessDataFile() throws IOException {
        String filename = String.format("shopping/cartdb/%s.db", username.toLowerCase());
        Path datafile = Paths.get(filename);
        try{
            Files.createFile(datafile);
            System.out.printf("Hi %s, your cart is empty.\n", username.toLowerCase());
            }
        catch(FileAlreadyExistsException e){
        if (datafile.toFile().length() == 0) {
            System.out.printf("Hi %s, your cart is empty.\n", username.toLowerCase());
        }
        else {
            System.out.printf("Hi %s, your cart contains the following items:\n", username.toLowerCase());
            FileInputStream fis = new FileInputStream(filename);
            Scanner scanner = new Scanner(fis);
            int i = 1;
            while (scanner.hasNextLine()){
                    String item = scanner.nextLine(); //initialize the item as the line read
                    cartList.add(item); //empty cart at beginning, read from database
                    System.out.println(i + ". " + item );
                    i++;
                }
            }
        }
    }



    public void writeDataFile() throws IOException {
        String filename = String.format("shopping/cartdb/%s.db", username.toLowerCase());
        FileWriter writer = new FileWriter(filename);
        for (String s : cartList) {
            writer.write(s + "\n");
            writer.flush();
        }
        writer.close();
    }

}
