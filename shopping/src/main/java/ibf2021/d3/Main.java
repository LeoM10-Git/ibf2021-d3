package ibf2021.d1;

import java.io.IOException;
import java.util.*;

public class Main {
    public static ArrayList<String> cartList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ShoppingCartDB user = new ShoppingCartDB();
        user.accessDataBase();
        user.accessDataFile();
        accessShoppingCart();
        user.writeDataFile();
//        user.accessDataFile();

    }

    public static void accessShoppingCart(){

            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to your shopping cart");
            boolean keepInput = true;
            String toAdd;
            int toDelete;
            String[] command = new String[]{"add", "delete", "list", "save"};
            List<String> commandArray = Arrays.asList(command);

            System.out.println("Please type add/delete for your shopping cart, type list to check, or save.");
            while (keepInput) {

                // toLowerCase() makes all input to lower case
                String userInput = input.next().toLowerCase();
                input.nextLine();

                //check if valid input do
                if (!commandArray.contains(userInput.trim())){
                    System.out.println("Your command is invalid, please enter correct command.");
                    System.out.println("Please type add/delete for your shopping cart, type list to check, or save.");
                }
                else{
                    // check input for list, print cart empty or have any items, use trim() to remove the space may be entered
                    if (userInput.trim().equals("list")) {
                        if (cartList.isEmpty()) {
                            System.out.println("Your cart is empty");
                        } else {
                            for (int i =0 ;i < cartList.size(); i++){
                                System.out.printf("%d. %s \n", i + 1,cartList.get(i));
                            }
                        }
                        System.out.println("Please type add/delete for your shopping cart, type list to check, or save.");
                    }

                    // check input for add
                    if (userInput.trim().equals("add")) {
                        System.out.println("Please add items");
                        String addItems = input.nextLine().toLowerCase();
                        String [] arrOfAdd = addItems.split(" ", 10);
                        for (int i = 0; i < arrOfAdd.length; i++){
                            toAdd = arrOfAdd[i].trim().replace(",", "");
                            // check if toAdd is already in cart
                            if (cartList.contains(toAdd)){
                                System.out.printf("%s is already in the cart \n", toAdd);
                            }
                            else{
                                cartList.add(toAdd);
                            }
                        }
                        System.out.println("Please type add/delete for your shopping cart, type list to check, or save.");
                    }

                    // check input for delete
                    if (userInput.trim().contains("delete")){
                        System.out.println("Please delete items");
                        String deleteItem = input.nextLine().toLowerCase();
                        toDelete = Integer.parseInt(deleteItem.trim());
                        // check if the index is out of the cart list
                        if (toDelete > cartList.size()){
                            System.out.println("Incorrect item index");
                        }

                        // else remove the correct item from cart list, print the message for item removed
                        else{
                            System.out.printf("%s is removed from cart \n", cartList.get(toDelete - 1));
                            cartList.remove(toDelete - 1);
                        }
                        System.out.println("Please type add/delete for your shopping cart, type list to check, or save.");
                    }

                    // check input for exit
                    if (userInput.trim().equals("save")){
                        System.out.println("Your cart list has been saved as .db file");
                        keepInput = false;
                    }
                }

                }
        }

}