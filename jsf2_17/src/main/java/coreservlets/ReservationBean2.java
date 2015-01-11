package coreservlets;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class ReservationBean2 extends ReservationBean1 {
  private Integer roomNumber = 2;
  
  public Integer getRoomNumber() {
    return(roomNumber);
  }

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }
  
  /** Due to unsanitary conditions, the health department shut down all 
   *  rooms except 2 and 7. 
   */
  public void validateRoomNumber(FacesContext context,
                                 UIComponent componentToValidate,
                                 Object value)
          throws ValidatorException {
    int roomNumber = (Integer)value;
    if ((roomNumber != 2) && (roomNumber != 7)) {
      String messageText = 
        String.format("Room %s temporarily unavailable", roomNumber);
      FacesMessage errorMessage = new FacesMessage(messageText);
      errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(errorMessage);
    }
  }
  
  @Override
  public String register() {
    String result = super.register();
    if (result != null) {
      result = "show-dates-2";
    }
    return(result);
  }
}
