package sampleapp.dto;

public class UrlDetails {
    private String url;

    public UrlDetails() {
    }

    public UrlDetails(String url) {
        this.setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
