package zad1;

public class OffersDescriptions {
    private int id;
    private String language;
    private String country;
    private String dateLeaving;
    private String dateArriving;
    private String place;
    private String price;
    private String currency;

    public OffersDescriptions(int id, String language, String country, String dateLeaving, String dateArriving, String place, String price, String currency) {
        this.id = id;
        this.language = language;
        this.country = country;
        this.dateLeaving = dateLeaving;
        this.dateArriving = dateArriving;
        this.place = place;
        this.price = price;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateLeaving() {
        return dateLeaving;
    }

    public void setDateLeaving(String dateLeaving) {
        this.dateLeaving = dateLeaving;
    }

    public String getDateArriving() {
        return dateArriving;
    }

    public void setDateArriving(String dateArriving) {
        this.dateArriving = dateArriving;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "OffersDescriptions{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", dateLeaving='" + dateLeaving + '\'' +
                ", dateArriving='" + dateArriving + '\'' +
                ", place='" + place + '\'' +
                ", price='" + price + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
