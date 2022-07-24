import com.yeahx4.jamza.util.ColorText;
import com.yeahx4.jamza.util.Console;

public class ColorTextTest {
    public static void main(String[] args) {
        String text = ColorText.convertColoredText("yeah &ghaha \\&this");
        Console.printc(text);
    }
}
