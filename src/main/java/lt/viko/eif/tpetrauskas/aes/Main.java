package lt.viko.eif.tpetrauskas.aes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while(true) {
            showMenu();
            selectFromMenu();
        }

        // teksta, key
        // mode pasirinkimas
        // encode/decode
        // default issaugoti faile
        // galimybe nuskaityti faila

        // sifruoti failo turini, ne faila
    }

    public static void showMenu() {
        System.out.println();
        System.out.println("************* MENU *************");
        System.out.println("1. Uzkoduoti/atkoduoti.");
        System.out.println("2. Nuskaityti teksta is failo.");
        System.out.println("********************************");
    }

    public static void selectFromMenu() {
        System.out.println("Pasirinkite menu punkta: ");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();
        switch (selection) {
            case 1: selection = 1;
                showEncodeDecodeOption();
                break;
            case 2: selection = 2;
                showFileScanOption();
                break;
            default:
                System.out.println("Tokio menu pasirinkimo nera. Iveskite kita skaiciu.");
                break;
        }
    }

    public static void showEncodeDecodeOption() {
        Scanner input = new Scanner(System.in);

        System.out.println("Iveskite teksta: ");
        String originalString = input.nextLine();

        System.out.println("Iveskite rakta: ");
        String secretKey = input.nextLine();

        showAESModesMenu();
        String mode = selectAESMode();

        showEncodeOrDecodeMenu();

        selectEncodeOrDecode(originalString, secretKey, mode);
    }

    public static void showFileScanOption() {
        System.out.println("Nuskaitymas is failo.");
    }

    public static void showAESModesMenu() {
        System.out.println("********* AES MODES *********");
        System.out.println("1. ECB");
        System.out.println("2. CBC");
        System.out.println("*****************************");
    }

    public static String selectAESMode() {
        String mode = "";
        Scanner input = new Scanner(System.in);

        System.out.println("Pasirinkite AES sifravimo mode: ");
        int selection = input.nextInt();
        switch (selection) {
            case 1: selection = 1;
                // ECB
                mode = "";
                break;
            case 2: selection = 2;
                // CBC
                //mode = "";
                break;
            default:
                System.out.println("Tokio menu pasirinkimo nera. Iveskite kita skaiciu.");
                break;
        }
        return mode;
    }

    public static void showEncodeOrDecodeMenu() {
        System.out.println("*** UZKODAVIMAS / ATKODAVIMAS ***");
        System.out.println("1. Uzkoduoti");
        System.out.println("2. Atkoduoti");
        System.out.println("********************************");
    }

    public static void selectEncodeOrDecode(String originalString, String secretKey, String mode) {
        Scanner input = new Scanner(System.in);

        String encryptedString = AES.encrypt(originalString, secretKey, "mode");
        String decryptedString = AES.decrypt(encryptedString, secretKey);

        System.out.println("Pasirinkite menu punkta: ");
        int selection = input.nextInt();
        switch (selection) {
            case 1: selection = 1;
                // ENCODE
                System.out.println("Uzkoduotas tekstas: " + encryptedString);
                // save to file
                break;
            case 2: selection = 2;
                // DECODE
                System.out.println("Uzkoduotas tekstas: " + encryptedString);
                System.out.println("Atkoduotas tekstas: " + decryptedString);
                // save to file
                break;
            default:
                System.out.println("Tokio menu pasirinkimo nera. Iveskite kita skaiciu.");
                break;
        }
    }
}
