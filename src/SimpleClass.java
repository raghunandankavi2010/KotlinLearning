


import java.util.ArrayList;

public class SimpleClass {

    ArrayList<?> items;
    public void addList(ArrayList<? extends Object> items)
    {
        this.items = items;
    }
}
