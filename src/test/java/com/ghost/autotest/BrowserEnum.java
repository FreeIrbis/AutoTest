package com.ghost.autotest;


public enum BrowserEnum {

    GOOGLE("webdriver.chrome.driver", "D:\\Codding\\IdeasProjects\\Driver\\chromedriver.exe"),
    FIREFOX("webdriver.gecko.driver", "D:\\Codding\\IdeasProjects\\Driver\\geckodriver.exe");

    String nameDriver;
    String urlDriver;

    public String getNameDriver() {
        return nameDriver;
    }

    public String getUrlDriver() {
        return urlDriver;
    }

    BrowserEnum(String nameDriver ,String urlDriver){
        this.nameDriver = nameDriver;
        this.urlDriver = urlDriver;
    }

}
