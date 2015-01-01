package coreservlets.model;

public class DailyDeal {

    private final String title, webSite;
    private final double price;

    public DailyDeal(String title, double price, String webSite) {
        this.title = title;
        this.price = price;
        this.webSite = webSite;
    }

    public String getTitle() {
        return (title);
    }

    public double getPrice() {
        return (price);
    }

    public String getWebSite() {
        return (webSite);
    }
}
