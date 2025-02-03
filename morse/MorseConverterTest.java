package morse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MorseConverterTest {

    MorseConverter converter = new MorseConverter(); // Skapar instans av MorseConverter

    @Test
    public void testConvertToMorse() { // Testar om convertToMotse korrekt konverterar text till morsekod
        assertEquals(".-", converter.convertToMorse("A")); //
        assertEquals("-...", converter.convertToMorse("b"));
        assertEquals(".... . .-.. .-.. ---", converter.convertToMorse("HELLO"));
    }

    @Test
    public void testConvertToText() { // Testar om convertToText korrekt konverterar morsekod till text
        assertEquals("A", converter.convertToText(".-"));
        assertEquals("B", converter.convertToText("-..."));
        assertEquals("HELLO", converter.convertToText(".... . .-.. .-.. ---"));
    }

    @Test
    public void testToMorseWithSpaces() { // Testar om convertToMorse hanterar mellanslag mellan ord korrekt
        assertEquals(".-   -...", converter.convertToMorse("A B")); // Testar ett mellanslag mellan två bokstäver
        assertEquals(".... . .-.. .-.. ---   .-- --- .-. .-.. -..", converter.convertToMorse("HELLO WORLD")); // Testar mellanslag mellan ord
    }

    @Test
    public void testIsValidText() { // Testar om isValidText kontrollerar giltig text på ett korrekt sätt (ej siffror)
        assertTrue(converter.isValidInput("HELLO", false)); // Giltig text
        assertFalse(converter.isValidInput("HELLO123", false)); // Ogiltig text
    }

    @Test
    public void testIsValidMorse() { // Testar om isValidInput kontrollerar giltig morsekod på ett korrekt sätt
        assertTrue(converter.isValidInput(".... . .-.. .-.. ---", true)); // Giltig morsekod
        assertFalse(converter.isValidInput(".... . .-.. .-.. --- 123", true)); // Ogiltig morsekod
    }

    @Test
    public void testEmptyString() { // Testar om emptyString hanterar tomma strängar korrekt
        assertFalse(converter.isValidInput("", true)); // Testar tom sträng för morsekod
        assertFalse(converter.isValidInput("", false)); // Testar tom sträng för text
    }
}
