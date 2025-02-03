package morse;

import java.util.HashMap;

public class MorseConverter {

    private final HashMap<Character, String> letterToMorse; // Hashmap för bokstäver till morsekod
    private final HashMap<String, Character> morseToLetter; // Hashmap för morsekod till bokstäver

    public MorseConverter() { // Konstruktor för att initialisera Hashmaps
        letterToMorse = new HashMap<>(); // Skapar hashmap för att mappa bokstäver till morsekod
        morseToLetter = new HashMap<>(); // Skapar hashmap för att mappa morsekod till bokstäver

        initializeHashmaps(); // Anropar metod som fyller hashmaps
    }

    private void initializeHashmaps() { // Metod som fyller hashmaps med bokstäver/morsekod med hjälp av arrays
        String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."}; // Array med morsekod
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); // Array med tecken från A-Z

        for (int i = 0; i < letters.length; i++) { // Loopar igenom alla bokstäver och morsekod och lägger till dem i respektive hashmap
            letterToMorse.put(letters[i], morseCode[i]); // Fyller hashmappen letterToMorse: bokstaven som nyckel och morsekod som värde
            morseToLetter.put(morseCode[i], letters[i]); // Fyller hashmappen morseToLetter: morsekod som nyckel och bokstav som värde
        }
    }


    public String convertToMorse(String text) { // Metod som konverterar text till morsekod
        String output = ""; // Variabel för att lagra resultatet i morsekod
        text = text.toUpperCase(); // Omvandlar texten till stora bokstäver

        for (int i = 0; i < text.length(); i++) { // Loopar igenom alla tecken i texten
            char c = text.charAt(i); // Hämtar aktuellt tecken från texten
            if (c == ' ') {
                output += "  "; // Om tecknet är ett mellanslag, så läggs två mellanslag till för att separera ord
            }
            else {
                String morse = letterToMorse.get(c); // Hämtar motsvarande morsekod för bokstaven
                if (morse != null) {
                    output += morse + " "; // Om morsekoden finns, läggs den till samt ett mellanslag
                }
                else { // Om ingen giltig morsekod hittades för bokstaven
                    throw new IllegalArgumentException(("Ogiltig inmatning... " + c));
                }
            }
        }
        return output.trim(); // Returnerar resultatet utan extra mellanslag i slutet
    }

    public String convertToText(String morseInput) { // Metod som konverterar morsekod till text
        String output = ""; // Variabel för att lagra resultatet i text
        String[] morseWords = morseInput.split("   "); // Array som delar upp morsekoden i morseord, där ord är separerade med tre mellanslag

        for (int i = 0; i < morseWords.length; i++) { // Loopar igenom alla morseord
            String word = morseWords[i]; // Hämtar det aktuella ordet i morsekod

            String[] morseLetters = word.split(" "); // Array som delar upp morseordet i individuella morsebokstäver, separerade med ett mellanslag
            for (int j = 0; j < morseLetters.length; j++) {
                String morse = morseLetters[j]; // hämtar den aktuella morsebokstaven

                Character letter = morseToLetter.get(morse); // Hämtar motsvarande bokstav från morseToLetter
                if (letter != null) {
                    output += letter; // Om en bokstav matchade läggs den till i resultatet
                }
                else { // Om ingen bokstav matchade
                    throw new IllegalArgumentException("Ogiltig morsekod..." + morse);
                }
            }
            output += " "; // Lägger till ett mellanslag mellan ord
        }
        return output.trim(); // Returnerar resultatet utan extra mellanslag i slutet
    }

    public boolean isValidInput(String input, boolean isMorse) { // Metod för att kontrollera om inmatningen är giltig (isMorse anger om inmatningen är morsekod eller text)
        if (input.trim().isEmpty()) { // Kontrollerar om inmatningen är tom eller endast innehåller mellanslag
            return false;
        }
        for (int i = 0; i < input.length(); i++) { // Loopar igenom varje tecken i inmatningen
            char c = input.charAt(i); // Hämtar tecknet vid index i och lagrar i variabeln c

            if (isMorse) { // Om inmatningen är morse
                if (!(c == '.' || c == '-' || Character.isSpaceChar(c))) { // Kontrollerar att inmatningen endast innehåller punkt/bindestreck/mellanslag
                    return false; // Om ogiltiga tecken för morse hittas, returneras false
                }
            }
            else { // Om inmatningen är text
                if (!(Character.isLetter(c) || Character.isSpaceChar(c))) { // Kontrollerar att inmatningen endast innehåller bokstäver/mellanslag
                    return false; // Om ogiltiga tecken i texten hittas, returneras false
                }
            }
        }
        return true; // Om hela inmatningen är giltig returneras true
    }
}
