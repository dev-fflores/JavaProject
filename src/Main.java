
import java.time.LocalDate;
import java.util.*;

public class Main {
    static LocalDate date = LocalDate.now();
    static Scanner in = new Scanner(System.in);
    static String optionSubMenu;
    static Client client;
    static boolean isCodeValid = false;
    static int contador_fac = 0;
    static Clothing[] stock = {
            new Clothing("M0001", "blue jean", "L", 18.99f),
            new Clothing("M0002", "blue jean shorts", "M", 15.25f),
            new Clothing("M0003", "orange t-shirt", "L", 11.99f),
            new Clothing("M0004", "green t-shirt", "XL", 13.99f),
            new Clothing("M0005", "black jacket", "L", 29.5f),
            new Clothing("M0006", "orange short", "M", 9.99f),
            new Clothing("M0007", "underwears set (5 uni)", "L", 20.99f),
            new Clothing("M0008", "socks set (3 uni)", "M", 15.5f),
            new Clothing("M0009", "bronw belt", "XL", 11.8f),
            new Clothing("M0010", "black belt", "L", 10.5f),
    };


    static List<Clothing> shoppingCart = new ArrayList<>();

    //**************************************************************************//
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String optionMenu;
        boolean isExitPressed = false;

        while(!isExitPressed){

            System.out.println("Please, Select an option: \n");
            System.out.println("1) Storage SubMenu");
            System.out.println("2) Enter Customer: ");
            System.out.println("3) Enter Sale: ");
            System.out.println("4) View Sale Information: ");
            System.out.println("5) Exit \n");
            optionMenu = in.nextLine();

            switch (optionMenu) {
                case "1":
                    ShowStorageSubMenu();
                    break;
                case "2":
                    if (!Objects.equals(client, null)){
                        continue;
                    }
                    ShowRegisterClientMenu();
                    break;
                case "3":
                    ShowEnterSale();
                    break;
                case "4":
                    //show information of list  y usuario
                    if(Objects.equals(client, null)){
                        continue;
                    }
                    ShowSaleInformation(client, shoppingCart);

                    client = null;
                    shoppingCart.clear();

                    break;
                case "5":
                    isExitPressed = true;
                    System.out.println("Gracias por visita");
                    break;
                default:
                    System.out.println("Ingrese una opción correcta");
            }
        }

    }
    static void ShowStorageSubMenu(){
        while(!Objects.equals(optionSubMenu, "3")){
            System.out.println("Please, Select an option: \n");
            System.out.println("1) Search by description");
            System.out.println("2) Search by code ");
            System.out.println("3) Exit \n");

            optionSubMenu = in.nextLine();

            switch (optionSubMenu){
                case "1":
                    System.out.println("Type the Description: ");
                    String description_product = in.nextLine().toLowerCase();
                    searchByDescription(description_product);
                    break;
                case "2":

                    System.out.println("Type the Code: ");
                    String code_product = in.nextLine().toUpperCase();
                    searchByCode(code_product);
                    break;
                default:
                    System.out.println("Ingrese una opción valida");
            }
        }
    }
    static void ShowRegisterClientMenu(){

        client = new Client();
        String nameClient, numPhone;
        String genderClient, typeClient;
        int ageClient;
        boolean isNameVerified, isNumberVerified, isAgeVerified, isGenderVerified, isTypeVerified;

        System.out.println("Enter Customer: ");

        do{
            System.out.println("Enter Customer's Phone Number: ");
            numPhone = in.nextLine();
            client.setNumberPhone(numPhone);

            if(numPhone.length() == 10){
                isNumberVerified = true;
            }else{
                //Mensaje si no ingresó un número o superó la cantidad de caracteres
                System.out.println("The Number Must have 10 digits");
                isNumberVerified = false;
            }

        }while (isNumberVerified == false);


        do{
            System.out.println("Enter Customer's Name: ");
            nameClient = in.nextLine();
            client.setName(nameClient);

            if(nameClient.length() > 2 && nameClient.length() < 25){
                isNameVerified = true;
            }else{
                System.out.println("Name length must be between 2 and 25 character");
                isNameVerified = false;
            }

        }while (isNameVerified == false);

        do{
            System.out.println("Enter Customer's Age: ");
            ageClient = in.nextInt();
            client.setAge(ageClient);

            if(ageClient > 0 && ageClient < 99){
                isAgeVerified = true;
            }else{
                System.out.println("Age Must Have Two Positives Digits");
                isAgeVerified = false;
            }

        }while (isAgeVerified == false);

        do{
            System.out.println("Enter Customer's Gender: ");
            genderClient = in.next().toUpperCase();
            client.setGender(genderClient);

            if(Objects.equals(genderClient, "F") || Objects.equals(genderClient, "M") || Objects.equals(genderClient, "X")){
                isGenderVerified = true;
            }else
            {
                System.out.println("You Must Type F if is Female, M if is Male, X if is different to F and M");
                isGenderVerified = false;
            }

        }while (isGenderVerified == false);

        do{
            System.out.println("Enter Customer's Type: ");
            typeClient = in.next().toUpperCase();
            client.setTypeClient(typeClient);

            if(Objects.equals(typeClient, "F") || Objects.equals(typeClient, "V") || Objects.equals(typeClient, "N")){
                isTypeVerified = true;
            }else{
                System.out.println("You Must Type F, V or N");
                isTypeVerified = false;
            }

        }while (!isTypeVerified);

        System.out.println("Custumer has been successfully created!!!\n");
    }

    static void ShowEnterSale(){

        String optionEnterSale;

        do{
            System.out.println("Please, Select an option: \n");
            System.out.println("1) Ingresar codigo");
            System.out.println("2) Salir");
            optionEnterSale = in.nextLine();

            if(Objects.equals(optionEnterSale, "1")){

                do{
                    System.out.println("Ingrese el código del producto");
                    String code = in.nextLine().toUpperCase();

                    addClothingToCart(code);

                }while (isCodeValid == false);

            }else{
                System.out.println("Ingresa una opción válida");
            }


        }while(!Objects.equals(optionEnterSale, "2"));

    }

    static void ShowSaleInformation(Client client, List<Clothing> clothings){
        final int IVA = 19;

        System.out.printf("#######################################################%n");
        System.out.printf("# %-10s #          SALE              # N° %06d #%n", date.toString(), contador_fac);
        System.out.printf("#######################################################%n");

        System.out.printf("# %-20s %-18s %-11s #%n", "Nombre", "N. telefónico", "T. cliente");
        System.out.printf("# %-20s %-18s %-11s #%n", client.getName(), client.getNumberPhone(), client.getTypeClient());
        System.out.printf("#######################################################%n");

        System.out.printf("# %-7s %-23s %-6s %-12s #%n", "Codigo", "Desc.", "Talla", "Precio");

        for (int i = 0; i < clothings.size(); i++) {
            System.out.printf("# %-7s %-23s %-6s %-12.2f #%n", clothings.get(i).getCode(), clothings.get(i).getDescription(), clothings.get(i).getSize(), clothings.get(i).getPrice());
        }
        System.out.printf("#######################################################%n");

        float subTotal = 0;
        for (int i = 0; i < clothings.size(); i++) {
            subTotal += clothings.get(i).getPrice();
        }

        double amount = subTotal;

        int discountPercentage = 0;

        if (Objects.equals(client.getTypeClient(), "F")) { //if the client is frequently gets 8% discount
            discountPercentage = 8;
            amount = (int)(amount * 0.92);
        } else if (Objects.equals(client.getTypeClient(), "V")) { //if the client is vip gets 12% discount
            discountPercentage = 12;
            amount = (int)(amount * 0.88);
        }
        if (client.getAge() >= 65) { //if the client is 65 years or old gets 4% discount
            discountPercentage += 4;
            amount = (int)(amount * 0.96);
        }
        //IVA
        amount = (amount * 1.19f);

        System.out.printf("# Subtotal :                           %8.2f       #%n", subTotal);
        System.out.printf("# IGV      :                             %-8d     #%n", IVA);
        System.out.printf("# Descuento:                             %-8d     #%n", discountPercentage);
        System.out.printf("#######################################################%n");
        System.out.printf("# Total    :                           $ %-8.2f     #%n", amount);
        System.out.printf("#######################################################%n");
        contador_fac++;

    }
    static void searchByDescription(String product_description){
        boolean productoEncontrado = false;
        for (int i = 0; i < stock.length; i++) {
            if(Objects.equals(product_description, stock[i].getDescription())){

                //clothingSold.add(clothingMenArray[i]);

                System.out.println("Product Found");
                System.out.println("The Code is: " + stock[i].getCode());
                System.out.println("The Size is: " + stock[i].getSize());
                System.out.println("The Price is: " + (float)stock[i].getPrice());
                productoEncontrado = true;
                return;
            }
        }

        if (!productoEncontrado){
            System.out.println("Product Not Found, Please Check The Code Again");
        }
    }
    static void searchByCode(String code_product){
        boolean productoEncontrado = false;
        for (int i = 0; i < stock.length; i++) {
            if(Objects.equals(code_product, stock[i].getCode())){
                //clothingSold.add(stock[i]);
                System.out.println("Product Found");
                System.out.println("The description is: " + stock[i].getDescription());
                System.out.println("The Size is: " + stock[i].getSize());
                System.out.println("The Price is: " + (float)stock[i].getPrice());
                productoEncontrado = true;
                return;
            }
        }
        if (productoEncontrado == false){
            System.out.println("Product Not Found, Please Check The Code Again");
        }
    }

    static void addClothingToCart(String code){
        isCodeValid = false;
        for (int i = 0; i < stock.length; i++) {
            if(Objects.equals(code, stock[i].getCode())){
                isCodeValid = true;
                shoppingCart.add(stock[i]);
                return;
            }
        }
        if (!isCodeValid){
            System.out.println("Product Not Found, Please Check The Code Again");
        }
    }

}
