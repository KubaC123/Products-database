package Products;

import java.util.ArrayList;

public class Order 
{
    /**
     * List of ordered products IDs.
     */
    private ArrayList<Integer> IDs;
    
    /**
     * List of ordered products names.
     */
    private ArrayList<String> names;
    
    /**
     * List of ordered products amounts.
     */
    private ArrayList<Integer> amounts;
    
    public Order()
    {
        IDs = new ArrayList<>();
        names = new ArrayList<>();
        amounts = new ArrayList<>();
    }
    
    public void addProduct (int id, String name, int amount)
    {
        IDs.add(id);
        names.add(name);
        amounts.add(amount);
    }
    
    public void deleteProduct(int index)
    {
        IDs.remove(index);
        names.remove(index);
        amounts.remove(index);
    }
    
    public int getSize()
    {
        return IDs.size();
    }
    
    public int getID(int index)
    {
        return IDs.get(index);
    }
    
    public String getName(int index)
    {
        return names.get(index);
    }
    
    public int getAmount(int index)
    {
        return amounts.get(index);
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < getSize(); i++)
        {
            result += getID(i) + " " + getName(i) + " " + getAmount(i) + "\n";
        }
        return result;
    }
    
}
