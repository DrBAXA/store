package sombra.util;

public class PriceLimit {
    private int priceMin;
    private int priceMax;

    public PriceLimit() {
    }

    public PriceLimit(int priceMin, int priceMax) {
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public int getPriceMin() {

        return priceMin;
    }

    public void setPriceMin(int priceMin) {
        this.priceMin = priceMin;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(int priceMax) {
        this.priceMax = priceMax;
    }
}
