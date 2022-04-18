package lt.viko.eif.tpetrauskas.aes;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        while(true) {
            //showMenu();
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

    public static void selectFromMenu() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        Scanner input = new Scanner(System.in);
        while(true) {
            showMenu();
            System.out.println("Pasirinkite menu punkta: ");
            try {
                int selection = input.nextInt();
                switch (selection) {
                    case 1:
                        showEncodeDecodeOption();
                        break;
                    case 2:
                        showFileScanOption();
                        break;
                    default:
                        break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Tokio menu pasirinkimo nera. Iveskite kita skaiciu.\n");
                input.nextLine();
            }
        }
    }

    public static void showEncodeDecodeOption() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        Scanner input = new Scanner(System.in);

        System.out.println("Iveskite teksta: ");
        String originalString = input.nextLine();

        System.out.println("Iveskite rakta: ");
        String secretKey = input.nextLine();

        showAESModesMenu();
        String algorithm = selectAESAlgorithm();

        showEncodeOrDecodeMenu();

        selectEncodeOrDecode(originalString, secretKey, algorithm);
    }

    public static void showFileScanOption() {
        System.out.println("Nuskaitymas is failo.");
    }

    public static void showAESModesMenu() {
        System.out.println("********* AES ALGORITHMS *********");
        System.out.println("1. ECB");
        System.out.println("2. CBC");
        System.out.println("*****************************");
    }

    public static String selectAESAlgorithm() {
        String algorithm = "";
        Scanner input = new Scanner(System.in);

        System.out.println("Pasirinkite AES sifravimo algoritma: ");
        int selection = input.nextInt();
        switch (selection) {
            case 1:
                // ECB
                algorithm = "ECB";
                break;
            case 2:
                // CBC
                algorithm = "CBC";
                break;
            default:
                System.out.println("Tokio menu pasirinkimo nera. Iveskite kita skaiciu.");
                break;
        }
        return algorithm;
    }

    public static void showEncodeOrDecodeMenu() {
        System.out.println("*** UZKODAVIMAS / ATKODAVIMAS ***");
        System.out.println("1. Uzkoduoti");
        System.out.println("2. Atkoduoti");
        System.out.println("********************************");
    }

    public static void selectEncodeOrDecode(String originalString, String secretKey, String algorithm)
            throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        if(algorithm.equals("ECB")) {
            String encryptedString = ECB.encrypt(originalString, secretKey);
            String decryptedString = ECB.decrypt(encryptedString, secretKey);
            printEncodeOrDecode(originalString, encryptedString, decryptedString);
        }
        if(algorithm.equals("CBC")) {
            IvParameterSpec ivParameterSpec = CBC.generateIv();
            String encryptedString = CBC.encrypt(originalString, secretKey, ivParameterSpec);
            String decryptedString = CBC.decrypt(encryptedString, secretKey, ivParameterSpec);
            printEncodeOrDecode(originalString, encryptedString, decryptedString);
        }
    }

    public static void printEncodeOrDecode(String originalString, String encryptedString, String decryptedString) {
        Scanner input = new Scanner(System.in);
        System.out.println("Pasirinkite menu punkta: ");
        int selection = input.nextInt();
        switch (selection) {
            case 1:
                // ENCODE
                System.out.println("Originalus tekstas: " + originalString);
                System.out.println("Uzkoduotas tekstas: " + encryptedString);
                // save to file
                break;
            case 2:
                // DECODE
                System.out.println("Originalus tekstas: " + originalString);
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
