package src.fortune;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import src.file.FileProcessor;

@Component
public class RandomFortuneService implements FortuneService {

    private FileProcessor fileProcessor;
    private ArrayList<String> fortunes = new ArrayList<>();

    @Autowired
    public RandomFortuneService(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @PostConstruct
    public void loadFortune() throws FileNotFoundException{
        this.fortunes = this.fileProcessor.read("fortunes.txt");
        
    }

    @Override
    public String getFortune() {
        int rand = (int) (Math.random() * (this.fortunes.size() - 0 + 1) + 0);
        return fortunes.get(rand);
    }
}
