import java.io.File;
import java.io.FileReader;

public class Driver {
    public static void main(String[] args) {
        File input = new File(args[0]);
        FileReader reader = new FileReader(input);
        Lexer lexer = new Lexer(reader);

        while (lexer.yylex() != null) {}
    }
}
