package morse;

import java.util.Scanner;

public class MorseConverterMain {

    private static final Scanner scan = new Scanner(System.in); // Scanner-instans för att läsa användarinmatning
    private static final MorseConverter converter = new MorseConverter(); // Instans av MorseConverter för att utföra konverteringar


    public static void main(String[] args) {
        try {
            while (true) { // Loop som körs tills användaren väljer att avsluta programmet
                showMenu(); // Anropar metod som visar menyn för användaren
                int choice = getUserChoice(); // Anropar metod som läser in och returnerar användarens menyval och sparar i variabeln choice

                switch (choice) { // Switch-sats för att hantera användarens menyval
                    case 1: // Om användaren väljer menyval 1
                        handleTextToMorse(); // Anropar metod för att läsa in text och skicka till MorseConverter för konvertering samt skriva ut resultatet i morsekod
                        break;
                    case 2: //Om användaren väljer menyval 2
                        handleMorseToText(); // Anropar metod för att läsa in morsekod och skicka till MorseConverter för konvertering samt skriva ut resultatet i text
                        break;
                    case 3: // Om användaren väljer menyval 3
                        System.out.println("Programmet avslutas...");
                        return; // Avslutar main
                    default: // Om användaren matar in ogiltigt menyval
                        System.out.println("Ogiltigt val! Ange 1, 2 eller 3...\n");
                        continue; // Börjar om loopen
                }

                if (askToContinue()) { // Om användaren vill fortsätta (true)
                    continue; // Fortsätter loopen och går tillbaka till menyn
                }
                else { // Om användaren inte vill fortsätta (false)
                    System.out.println("Programmet avslutas...");
                    break; // Bryter loopen och programmet kommer avslutas
                }
            }
        }
        catch (Exception exc) {
            System.out.println("Ett oväntat fel har inträffat..." + exc.getMessage());
        }
        finally {
            scan.close(); // Stänger scanner oavsett hur programmet avslutas
        }
    }

    private static void showMenu() { // Metod som visar menyn för användaren
        System.out.println("Välj ett alternativ: 1/2/3");
        System.out.println("1: Konvertera text till morsekod");
        System.out.println("2: Konvertera morsekod till text");
        System.out.println("3: Avsluta programmet");
    }

    private static int getUserChoice() { // Metod som läser in och returnerar användarens menyval
        while (true) {
            try {
                return Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException exc) {
                System.out.println("Ogiltigt val! Ange siffra 1, 2 eller 3...\n");
            }
        }
    }

    private static void handleTextToMorse() { // Metod för att läsa in text och skriva ut morsekod
        while (true) {
            try {
                System.out.println("Ange text som ska konverteras till morsekod: ");
                String text = scan.nextLine(); // Läser in användarens text

                if (text.isEmpty()) { // Kontrollerar om inmatningen är tom
                    throw new IllegalArgumentException("OBS! Inmatning tom... Försök igen!");
                }
                if (converter.isValidInput(text, false)) { // Anropar metod för att kontrollera om texten är giltig
                    System.out.println("Morsekod: " + converter.convertToMorse(text)); // Skriver ut morsekod efter att ha anropat metod i MorseConverter som konverterar texten
                    break;
                }
                else {
                    throw new IllegalArgumentException("Ogiltig inmatning... Använd endast bokstäver och mellanslag... Försök igen! \n");
                }
            }
            catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }

    private static void handleMorseToText() { // Metod för att läsa in morsekod och skriva ut text
        while (true) {
            try {
                System.out.println("Ange morsekod som du vill konvertera till text: ");
                String morseInput = scan.nextLine(); // Läser in morsekod från användaren

                if (morseInput.isEmpty()) { // Kontrollerar om inmatningen är tom
                    throw new IllegalArgumentException("OBS! Inmatning tom... Försök igen!");
                }

                morseInput = morseInput.trim().replaceAll(" {2,}", "   "); // Ersätter två eller fler mellanslag med tre (morseord separeras med tre mellanslag)

                if (converter.isValidInput(morseInput, true)) { // Anropar metod för att kontrollerar om morsekoden är giltig
                    System.out.println("Text: " + converter.convertToText(morseInput)); // Skriver ut text efter att ha anropat metod i MorseConverter som konverterar morsekoden
                    break;
                }
                else {
                    throw new IllegalArgumentException("Ogiltig inmatning... Skriv endast morsekod (punkt, bindestreck eller mellanslag). Försök igen! \n");
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean askToContinue() { // Metod som frågar om användaren vill fortsätta programmet på nytt
        while (true) {
            System.out.println("\nVill du konvertera ny text/morsekod? (j/n)");
            String answer = scan.nextLine();

            try {
                if (answer.equalsIgnoreCase("j")) { // Om svaret är ja returneras true
                    return true;
                }
                else if (answer.equalsIgnoreCase("n")) { // Om svaret är nej returneras false
                    return false;
                }
                else {
                    throw new IllegalArgumentException("Ogiltig inmatning... Ange 'j' för 'ja' eller 'n' för 'nej'.\n");
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}




