package mazegen;

public class Floor implements Pump{
    @Override
    public Boolean pumpable() {
        return false;
    }
}
