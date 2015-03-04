package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class RatingBean {
  private int roomRating = 3, restaurantRating;

  public int getRoomRating() {
    return(roomRating);
  }

  public void setRoomRating(int roomRating) {
    this.roomRating = roomRating;
  }

  public int getRestaurantRating() {
    return(restaurantRating);
  }

  public void setRestaurantRating(int restaurantRating) {
    this.restaurantRating = restaurantRating;
  }
  
  public String processRatings() {
    return("show-ratings");
  }
}
