package player;

public class HumanPlayer implements IPlayer{
    String name;
    char mark;

    public HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "HumanPlayer{" +
                "name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}
