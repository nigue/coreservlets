package coreservlets;

import javax.faces.bean.*;

@ManagedBean
public class BidBean1 {

    private String userId = "";
    private String keyword = "";
    private String bidAmount;
    private double numericBidAmount = 0;
    private String bidDuration;
    private int numericBidDuration = 0;

    public String getUserId() {
        return (userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return (keyword);
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword.trim();
    }

    public String getBidAmount() {
        return (bidAmount);
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
        try {
            numericBidAmount = Double.parseDouble(bidAmount);
        } catch (NumberFormatException nfe) {
        }
    }

    public double getNumericBidAmount() {
        return (numericBidAmount);
    }

    public String getBidDuration() {
        return (bidDuration);
    }

    public void setBidDuration(String bidDuration) {
        this.bidDuration = bidDuration;
        try {
            numericBidDuration = Integer.parseInt(bidDuration);
        } catch (NumberFormatException nfe) {
        }
    }

    public int getNumericBidDuration() {
        return (numericBidDuration);
    }

    private void doSomeBusinessLogicWithBid() {
        // Update database with bid, etc.
    }

    public String doBid() {
        doSomeBusinessLogicWithBid();
        return ("show-bid-1");
    }
}
