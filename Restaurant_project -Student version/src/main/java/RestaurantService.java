import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        for(Restaurant restaurant:restaurants){
            if(restaurantName.equals(restaurant.getName())){
                return restaurant;
            }
        }
        throw new restaurantNotFoundException(restaurantName +" is not found");
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }
    public float totalOrder(ArrayList<String>items,Restaurant restaurant){
        float total_price=0f;
        Item item;
        for(String addedItem:items){
            item=restaurant.getItem(addedItem);
            total_price=item.getPrice()+total_price;
        }
        return total_price;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    
}
