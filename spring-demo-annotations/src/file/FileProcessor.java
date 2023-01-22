package src.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class FileProcessor implements FileInterface{
    Scanner scan;

    @Override
    public ArrayList<String> read(String pathName) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        // scan = new Scanner(new File("src/resource/fortunes.txt"));
        scan = new Scanner(new File("src/resources/" + pathName));

        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }

        this.scan.close();

        return lines;
    }
}
