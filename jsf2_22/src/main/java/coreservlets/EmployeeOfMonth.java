package coreservlets;

import coreservlets.data.Nameable;
import javax.faces.bean.ManagedBean;

/** Represents a vote for an employee of the month. */

@ManagedBean
public class EmployeeOfMonth implements Nameable {
  private String firstName, lastName;
  private String nominationReason;
  
  @Override
  public String getFirstName() {
    return(firstName);
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return(lastName);
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getNominationReason() {
    return(nominationReason);
  }
  
  public void setNominationReason(String nominationReason) {
    this.nominationReason = nominationReason;
  }

  public String recordVote() {
    storeVoteSomehow();
    return("show-employee-vote");
  }

  private void storeVoteSomehow() {
    // Nothing to see here. Move along.
  }
}
