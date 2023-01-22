package src.file;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface FileInterface {
    public ArrayList<String> read(String pathName) throws FileNotFoundException;
}
