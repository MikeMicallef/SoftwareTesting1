package API;

public class AlertWebsite {
    protected int alertType;
    protected String heading;
    protected String description;
    protected String url;
    protected String imageUrl;
    protected String postedBy;
    protected int priceInCents;

    public AlertWebsite(int alertType, String heading, String description, String url, String imageUrl,String postedBy, int priceInCents) {
        this.alertType = alertType;
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.postedBy = postedBy;
        this.priceInCents = priceInCents;

    }
}
