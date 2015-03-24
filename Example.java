package edu.sturtevant.example;
/**
 * Created by chrissturtevant0 on 3/12/15.
 */
public class Example {
    String name;
    String alt; //alternative
    String bar; //bar
    String fri; //friday
    String hun; //hungry
    String pat; //patrons
    String price; //price
    String rain; //rain
    String res; //reservation
    String type; //type
    String est; //estimated wait time
    String wait; //target wait time


    public Example(String name, String alt, String bar, String fri, String hun, String pat, String price, String rain, String res, String type, String est, String wait) {
        this.name = name;
        this.alt = alt;
        this.bar = bar;
        this.fri = fri;
        this.hun = hun;
        this.pat = pat;
        this.price = price;
        this.rain = rain;
        this.res = res;
        this.type = type;
        this.est = est;
        this.wait = wait;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAlt() {
        return alt;
    }

    public String getBar() {
        return bar;
    }

    public String getFri() {
        return fri;
    }

    public String getHun() {
        return hun;
    }

    public String getPat() {
        return pat;
    }

    public String getPrice() {
        return price;
    }

    public String getRain() {
        return rain;
    }

    public String getRes() {
        return res;
    }

    public String getType() {
        return type;
    }

    public String getEst() {
        return est;
    }

    public String getWait() {
        return wait;
    }



    //Override the toString method
    @Override
    public String toString() {
        return String.format(name + "\t\t" + alt + "\t\t" + bar + "\t\t" + fri + "\t\t" +
                hun + "\t\t" + pat + "\t\t" + price + "\t\t" + rain + "\t\t" + res + "\t\t" +
                type + "\t\t" + est + "\t\t" + wait );
    }
    public void printExample(){
        System.out.println("Name: "+ name );
        System.out.println("Alt: " + alt );
        System.out.println("Bar: " + bar );
        System.out.println("Fri: " + fri );
        System.out.println("Hun: " + hun );
        System.out.println("Pat: " + pat );
        System.out.println("Price: " + price );
        System.out.println("Rain: " + rain );
        System.out.println("Res: " + res );
        System.out.println("Type: " + type );
        System.out.println("Est: " + est );
        System.out.println("Wait: " + wait );
    }
}
