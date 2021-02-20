import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        LocalTime now=getCurrentTime();
        int openingHour= openingTime.getHour()==0 ?  24  : openingTime.getHour();
        int closingHour= closingTime.getHour()==0 ?  24  : closingTime.getHour();
        int currentHour= now.getHour()  ==0 ?  24  : now.getHour();
        if(currentHour==openingHour){
            if(now.getMinute() > openingTime.getMinute()) return true;
        }
        if(currentHour==closingHour){
            if(now.getMinute() < closingTime.getMinute()) return true;
        }
        if(openingHour<closingHour) {

            if ((currentHour > openingHour) && (currentHour < closingHour)) {
                return true;
            }
        }
            if(openingHour>closingHour){

                if((currentHour>=1 && currentHour<closingHour) || (currentHour>openingHour && currentHour<=24)){
                    return true;
                }
        }
        return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
