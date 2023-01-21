package src.fortune;

public class HappyFortuneService implements FortuneService{
    @Override
    public String getFortune() {
        String[] fortunes = {"Today is your lucky day", "Good luck", "You will get a raised after this"};
        int rand = (int) (Math.random() * (2 - 0 + 1) + 0);
        return fortunes[rand];
    }
}
