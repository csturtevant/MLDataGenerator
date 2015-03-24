package edu.sturtevant.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MidtermDTandILP {


    private ArrayList<ArrayList<String>> clients = new ArrayList<ArrayList<String>>();
    private ArrayList<String> mI = null;
    private ArrayList<Example> exampleArrayList = new ArrayList<Example>();

    public static void main(String[] args) {
        int choice;
        Scanner scan = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
        boolean repeat;
        MidtermDTandILP obj = new MidtermDTandILP();
        obj.selection();




        do {
            choice = 0;
            repeat = true;

            System.out.println("Please input the number corresponding to the function you want to run.\n" +
                    "1) Print out all examples.\n" +
                    "2) Output Specific Example.\n" +
                    "3) Split using a decision tree.\n" +
                    "4) Split using inductive logic programming.\n" +
                    "5) Exit.");
            choice = scan.nextInt();


            switch (choice) {
                case 1:
                    obj.outputClients();
                    break;
                case 2:
                    System.out.println("Please enter the name of the example " +
                            "you would like to print in the form of, X1 to X12");
                    String nameOfObject = scanString.next();
                    obj.outputRequestedExample(nameOfObject);
                    break;
                case 3:
                    obj.dTSplitChoice();
                    break;
                case 4:
                    obj.iLPSplitChoice();
                    break;
                case 5:
                    System.exit(0);
                    repeat = false;
                    break;
                default:
                    System.out.println("INCORRECT INPUT");
                    break;
            }
        } while (repeat == true);
    }

    /* Right now this is getting the data from the CSV file
     * and putting it into the collection "clients"
     */
    public void selection() {

        String csvFile = "/Users/chrissturtevant0/Google Drive/Programming Projects/College/Machine Learning/MidtermDecisionTree/Midterm1CSV.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] wordsArray;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator

                if (!(line.equals(",,,,"))) {
                    wordsArray = line.split(cvsSplitBy);
                    mI = new ArrayList<String>();
                    for (String each : wordsArray) {
                        mI.add(each);
                    }
                    clients.add(mI);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /*
         * This for loop adds the example objects into an array list.
         */
        for (int i = 0; i < clients.size()  ; i++) {
            mI = clients.get(i);
            exampleArrayList.add(new Example(mI.get(0), mI.get(1),mI.get(2),mI.get(3),mI.get(4),mI.get(5),mI.get(6),mI.get(7),mI.get(8),mI.get(9),mI.get(10),mI.get(11)));
            //System.out.println(exampleArrayList.get(i));
        }
    }

    /* This method outputs the data from "clients"
       * in a readable table-like format.
       * To get the first element of the first row, think of it as a coordinate of an excel sheet.
       * mI = clients.get(1); First row
         System.out.println( mI.get(0)); First element of that row
       */
    public void outputClients() {

        for (int i = 0; i < clients.size(); i++) {
            mI = clients.get(i);
            for (int j = 0; j < mI.size(); j++) {
                System.out.print(mI.get(j) + "\t\t");
            }
            System.out.println('\n');
        }
    }


    /*
     * This method outputs the requested example class in it's entirety
     */
    public void outputRequestedExample (String nameOfExample){
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getName().equals(nameOfExample)){
                System.out.println(exampleArrayList.get(i));
            }
        }
    }
    public void dTSplitChoice(){
        int dtSplitChoice = 0;
        Scanner dtScannner = new Scanner(System.in);
        System.out.println("Please input the number corresponding to the category you would like to apply logic to.\n" +
                "1) Alternative.\n" +
                "2) Bar.\n" +
                "3) Friday.\n" +
                "4) Hungry.\n" +
                "5) Patrons.\n" +
                "6) Price.\n" +
                "7) Rain.\n" +
                "8) Reservation.\n" +
                "9) Type.\n" +
                "10) Estimated Wait Time.\n" +
                "11) Target Wait.\n" +
                "12) Exit.");
        dtSplitChoice = dtScannner.nextInt();


        switch (dtSplitChoice) {
            case 1:
                dtSplit_Alternative();
                break;
            case 2:
                dtSplit_Bar();
                break;
            case 3:
                dtSplit_Fri();
                break;
            case 4:
                dtSplit_Hun();
                break;
            case 5:
                dtSplit_Pat();
                break;
            case 6:
                dtSplit_Price();
                break;
            case 7:
                dtSplit_Rain();
                break;
            case 8:
                dtSplit_Res();
                break;
            case 9:
                dtSplit_Type();
                break;
            case 10:
                dtSplit_Est();
                break;
            case 11:
                dtSplit_Wait();
                break;
            case 12:
                System.exit(0);
                //repeat = false;
                break;
            default:
                System.out.println("INCORRECT INPUT");
                break;
        }
    }
    /*
    This method splits on Alternative
     */
    public void dtSplit_Alternative (){
        ArrayList<Example> trueAlternative = new ArrayList<Example>();
        ArrayList<Example> falseAlternative = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getAlt().equals("T")){
                trueAlternative.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getAlt().equals("F")){
                falseAlternative.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have alternatives are listed below.");
        for (int i = 0; i < trueAlternative.size() ; i++) {
            System.out.print(trueAlternative.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that do not have alternatives are listed below.");
        for (int i = 0; i < falseAlternative.size() ; i++) {
            System.out.print(falseAlternative.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Bar
     */
    public void dtSplit_Bar (){
        ArrayList<Example> trueBar = new ArrayList<Example>();
        ArrayList<Example> falseBar = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getBar().equals("T")){
                trueBar.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getBar().equals("F")){
                falseBar.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have bars are listed below.");
        for (int i = 0; i < trueBar.size() ; i++) {
            System.out.print(trueBar.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that do not have bars are listed below.");
        for (int i = 0; i < falseBar.size() ; i++) {
            System.out.print(falseBar.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Friday
     */
    public void dtSplit_Fri (){
        ArrayList<Example> trueFri = new ArrayList<Example>();
        ArrayList<Example> falseFri = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getFri().equals("T")){
                trueFri.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getFri().equals("F")){
                falseFri.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have are on Friday are listed below.");
        for (int i = 0; i < trueFri.size() ; i++) {
            System.out.print(trueFri.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that are not on Friday are listed below.");
        for (int i = 0; i < falseFri.size() ; i++) {
            System.out.print(falseFri.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Hungry
     */
    public void dtSplit_Hun (){
        ArrayList<Example> trueHun = new ArrayList<Example>();
        ArrayList<Example> falseHun = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getHun().equals("T")){
                trueHun.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getHun().equals("F")){
                falseHun.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have hungry targets are listed below.");
        for (int i = 0; i < trueHun.size() ; i++) {
            System.out.print(trueHun.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that do not have hungry targets are listed below.");
        for (int i = 0; i < falseHun.size() ; i++) {
            System.out.print(falseHun.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Patrons
     */
    public void dtSplit_Pat (){
        ArrayList<Example> noPat = new ArrayList<Example>();
        ArrayList<Example> somePat = new ArrayList<Example>();
        ArrayList<Example> fullPat = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getPat().equals("None")){
                noPat.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getPat().equals("Some")){
                somePat.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getPat().equals("Full")){
                fullPat.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have no patrons are listed below.");
        for (int i = 0; i < noPat.size() ; i++) {
            System.out.print(noPat.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have some patrons are listed below.");
        for (int i = 0; i < somePat.size() ; i++) {
            System.out.print(somePat.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that are full of patrons are listed below.");
        for (int i = 0; i < fullPat.size() ; i++) {
            System.out.print(fullPat.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Price
     */
    public void dtSplit_Price (){
        ArrayList<Example> lowPrice = new ArrayList<Example>();
        ArrayList<Example> midPrice = new ArrayList<Example>();
        ArrayList<Example> highPrice = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getPrice().equals("$")){
                lowPrice.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getPrice().equals("$$")){
                midPrice.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getPrice().equals("$$$")){
                highPrice.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have low prices are listed below.");
        for (int i = 0; i < lowPrice.size() ; i++) {
            System.out.print(lowPrice.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have mid tier prices are listed below.");
        for (int i = 0; i < midPrice.size() ; i++) {
            System.out.print(midPrice.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have high prices are listed below.");
        for (int i = 0; i < highPrice.size() ; i++) {
            System.out.print(highPrice.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Rain
     */
    public void dtSplit_Rain (){
        ArrayList<Example> trueRain = new ArrayList<Example>();
        ArrayList<Example> falseRain = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getRain().equals("T")){
                trueRain.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getRain().equals("F")){
                falseRain.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples where it is raining are listed below.");
        for (int i = 0; i < trueRain.size() ; i++) {
            System.out.print(trueRain.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples where it is not raining are listed below.");
        for (int i = 0; i < falseRain.size() ; i++) {
            System.out.print(falseRain.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Res
     */
    public void dtSplit_Res (){
        ArrayList<Example> trueRes = new ArrayList<Example>();
        ArrayList<Example> falseRes = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getRes().equals("T")){
                trueRes.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getRes().equals("F")){
                falseRes.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples where the target has a reservation are listed below.");
        for (int i = 0; i < trueRes.size() ; i++) {
            System.out.print(trueRes.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples where the target does not have a reservation are listed below.");
        for (int i = 0; i < falseRes.size() ; i++) {
            System.out.print(falseRes.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Type of restaurant
     */
    public void dtSplit_Type (){
        ArrayList<Example> frenchType = new ArrayList<Example>();
        ArrayList<Example> thaiType = new ArrayList<Example>();
        ArrayList<Example> burgerType = new ArrayList<Example>();
        ArrayList<Example> italianType = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getType().equals("French")){
                frenchType.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getType().equals("Thai")){
                thaiType.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getType().equals("Burger")){
                burgerType.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getType().equals("Italian")){
                italianType.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that serve French are listed below.");
        for (int i = 0; i < frenchType.size() ; i++) {
            System.out.print(frenchType.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that serve Thai are listed below.");
        for (int i = 0; i < thaiType.size() ; i++) {
            System.out.print(thaiType.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that serve Burgers are listed below.");
        for (int i = 0; i < burgerType.size() ; i++) {
            System.out.print(burgerType.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that serve Italian are listed below.");
        for (int i = 0; i < italianType.size() ; i++) {
            System.out.print(italianType.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Est of restaurant
     */
    public void dtSplit_Est (){
        ArrayList<Example> zeroEst = new ArrayList<Example>();
        ArrayList<Example> tenEst = new ArrayList<Example>();
        ArrayList<Example> thirtyEst = new ArrayList<Example>();
        ArrayList<Example> sixtyEst = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getEst().equals("0-10")){
                zeroEst.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getEst().equals("10-30")){
                tenEst.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getEst().equals("30-60")){
                thirtyEst.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getEst().equals(">60")){
                sixtyEst.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have a wait time of 0-10 minutes are listed below.");
        for (int i = 0; i < zeroEst.size() ; i++) {
            System.out.print(zeroEst.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have a wait time of 10-30 minutes are listed below.");
        for (int i = 0; i < tenEst.size() ; i++) {
            System.out.print(tenEst.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have a wait time of 30-60 minutes are listed below.");
        for (int i = 0; i < thirtyEst.size() ; i++) {
            System.out.print(thirtyEst.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have a wait time of >60 minutes are listed below.");
        for (int i = 0; i < sixtyEst.size() ; i++) {
            System.out.print(sixtyEst.get(i).getName() + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Wait
     */
    public void dtSplit_Wait (){
        ArrayList<Example> trueWait = new ArrayList<Example>();
        ArrayList<Example> falseWait = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getWait().equals("T")){
                trueWait.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getWait().equals("F")){
                falseWait.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples where the target waits are listed below.");
        for (int i = 0; i < trueWait.size() ; i++) {
            System.out.print(trueWait.get(i).getName() + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples where the target does not wait are listed below.");
        for (int i = 0; i < falseWait.size() ; i++) {
            System.out.print(falseWait.get(i).getName() + " ");
        }
        System.out.println("\n");
    }


    public void iLPSplitChoice(){
        int ilpSplitChoice = 0;
        Scanner ilpScannner = new Scanner(System.in);
        System.out.println("Please input the number corresponding to the category you would like to apply inductive logic programming to.\n" +
                "1) Alternative.\n" +
                "2) Bar.\n" +
                "3) Friday.\n" +
                "4) Hungry.\n" +
                "5) Patrons.\n" +
                "6) Price.\n" +
                "7) Rain.\n" +
                "8) Reservation.\n" +
                "9) Type.\n" +
                "10) Estimated Wait Time.\n" +
                "11) Target Wait.\n" +
                "12) Exit.");
        ilpSplitChoice = ilpScannner.nextInt();


        switch (ilpSplitChoice) {
            case 1:
                ilpSplit_Alternative();
                break;
            case 2:
                ilpSplit_Bar();
                break;
            case 3:
                ilpSplit_Fri();
                break;
            case 4:
                ilpSplit_Hun();
                break;
            case 5:
                ilpSplit_Pat();
                break;
            case 6:
                ilpSplit_Price();
                break;
            case 7:
                ilpSplit_Rain();
                break;
            case 8:
                ilpSplit_Res();
                break;
            case 9:
                ilpSplit_Type();
                break;
            case 10:
                ilpSplit_Est();
                break;
            case 11:
                ilpSplit_Wait();
                break;
            case 12:
                System.exit(0);
                //repeat = false;
                break;
            default:
                System.out.println("INCORRECT INPUT");
                break;
        }
    }
    /*
    This method splits on Alternative
     */
    public void ilpSplit_Alternative (){
        ArrayList<Example> trueAlternative = new ArrayList<Example>();
        ArrayList<Example> falseAlternative = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getAlt().equals("T")){
                trueAlternative.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getAlt().equals("F")){
                falseAlternative.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have alternatives are listed below.");
        for (int i = 0; i < trueAlternative.size() ; i++) {
            System.out.print("Alternate_nearby_rest(" + trueAlternative.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that do not have alternatives are listed below.");
        for (int i = 0; i < falseAlternative.size() ; i++) {
            System.out.print("~Alternate_nearby_rest(" + falseAlternative.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Bar
     */
    public void ilpSplit_Bar (){
        ArrayList<Example> trueBar = new ArrayList<Example>();
        ArrayList<Example> falseBar = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getBar().equals("T")){
                trueBar.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getBar().equals("F")){
                falseBar.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have bars are listed below.");
        for (int i = 0; i < trueBar.size() ; i++) {
            System.out.print("Bar_nearby_rest(" + trueBar.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that do not have bars are listed below.");
        for (int i = 0; i < falseBar.size() ; i++) {
            System.out.print("~Bar_nearby_rest(" + falseBar.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Friday
     */
    public void ilpSplit_Fri (){
        ArrayList<Example> trueFri = new ArrayList<Example>();
        ArrayList<Example> falseFri = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getFri().equals("T")){
                trueFri.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getFri().equals("F")){
                falseFri.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have are on Friday are listed below.");
        for (int i = 0; i < trueFri.size() ; i++) {
            System.out.print("Friday_at_rest(" + trueFri.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that are not on Friday are listed below.");
        for (int i = 0; i < falseFri.size() ; i++) {
            System.out.print("~Friday_at_rest(" + falseFri.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Hungry
     */
    public void ilpSplit_Hun (){
        ArrayList<Example> trueHun = new ArrayList<Example>();
        ArrayList<Example> falseHun = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getHun().equals("T")){
                trueHun.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getHun().equals("F")){
                falseHun.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have hungry targets are listed below.");
        for (int i = 0; i < trueHun.size() ; i++) {
            System.out.print("Hungry_at_rest(" + trueHun.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that do not have hungry targets are listed below.");
        for (int i = 0; i < falseHun.size() ; i++) {
            System.out.print("~Hungry_at_rest(" + falseHun.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Patrons
     */
    public void ilpSplit_Pat (){
        ArrayList<Example> noPat = new ArrayList<Example>();
        ArrayList<Example> somePat = new ArrayList<Example>();
        ArrayList<Example> fullPat = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getPat().equals("None")){
                noPat.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getPat().equals("Some")){
                somePat.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getPat().equals("Full")){
                fullPat.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have no patrons are listed below.");
        for (int i = 0; i < noPat.size() ; i++) {

        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have some patrons are listed below.");
        for (int i = 0; i < somePat.size() ; i++) {
            System.out.print("Some_patrons_at_rest(" + somePat.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that are full of patrons are listed below.");
        for (int i = 0; i < fullPat.size() ; i++) {
            System.out.print("Full_patrons_at_rest(" + fullPat.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Price
     */
    public void ilpSplit_Price (){
        ArrayList<Example> lowPrice = new ArrayList<Example>();
        ArrayList<Example> midPrice = new ArrayList<Example>();
        ArrayList<Example> highPrice = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getPrice().equals("$")){
                lowPrice.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getPrice().equals("$$")){
                midPrice.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getPrice().equals("$$$")){
                highPrice.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have low prices are listed below.");
        for (int i = 0; i < lowPrice.size() ; i++) {
            System.out.print("Low_price_at_rest(" + lowPrice.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have mid tier prices are listed below.");
        for (int i = 0; i < midPrice.size() ; i++) {
            System.out.print("Mid_price_at_rest(" + midPrice.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have high prices are listed below.");
        for (int i = 0; i < highPrice.size() ; i++) {
            System.out.print("High_price_at_rest(" + highPrice.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Rain
     */
    public void ilpSplit_Rain (){
        ArrayList<Example> trueRain = new ArrayList<Example>();
        ArrayList<Example> falseRain = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getRain().equals("T")){
                trueRain.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getRain().equals("F")){
                falseRain.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples where it is raining are listed below.");
        for (int i = 0; i < trueRain.size() ; i++) {
            System.out.print("Rain_at_rest(" + trueRain.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples where it is not raining are listed below.");
        for (int i = 0; i < falseRain.size() ; i++) {
            System.out.print("~Rain_at_rest(" + falseRain.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Res
     */
    public void ilpSplit_Res (){
        ArrayList<Example> trueRes = new ArrayList<Example>();
        ArrayList<Example> falseRes = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getRes().equals("T")){
                trueRes.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getRes().equals("F")){
                falseRes.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples where the target has a reservation are listed below.");
        for (int i = 0; i < trueRes.size() ; i++) {
            System.out.print("Res_at_rest(" + trueRes.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples where the target does not have a reservation are listed below.");
        for (int i = 0; i < falseRes.size() ; i++) {
            System.out.print("~Res_at_rest(" + falseRes.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Type of restaurant
     */
    public void ilpSplit_Type (){
        ArrayList<Example> frenchType = new ArrayList<Example>();
        ArrayList<Example> thaiType = new ArrayList<Example>();
        ArrayList<Example> burgerType = new ArrayList<Example>();
        ArrayList<Example> italianType = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getType().equals("French")){
                frenchType.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getType().equals("Thai")){
                thaiType.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getType().equals("Burger")){
                burgerType.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getType().equals("Italian")){
                italianType.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that serve French are listed below.");
        for (int i = 0; i < frenchType.size() ; i++) {
            System.out.print("French_at_rest(" + frenchType.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that serve Thai are listed below.");
        for (int i = 0; i < thaiType.size() ; i++) {
            System.out.print("Thai_at_rest(" + thaiType.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that serve Burgers are listed below.");
        for (int i = 0; i < burgerType.size() ; i++) {
            System.out.print("Burger_at_rest(" + burgerType.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that serve Italian are listed below.");
        for (int i = 0; i < italianType.size() ; i++) {
            System.out.print("Italian_at_rest(" + italianType.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Est of restaurant
     */
    public void ilpSplit_Est (){
        ArrayList<Example> zeroEst = new ArrayList<Example>();
        ArrayList<Example> tenEst = new ArrayList<Example>();
        ArrayList<Example> thirtyEst = new ArrayList<Example>();
        ArrayList<Example> sixtyEst = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getEst().equals("0-10")){
                zeroEst.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getEst().equals("10-30")){
                tenEst.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getEst().equals("30-60")){
                thirtyEst.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getEst().equals(">60")){
                sixtyEst.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples that have a wait time of 0-10 minutes are listed below.");
        for (int i = 0; i < zeroEst.size() ; i++) {
            System.out.print("Zero_to_ten_minute_wait_at_rest(" + zeroEst.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have a wait time of 10-30 minutes are listed below.");
        for (int i = 0; i < tenEst.size() ; i++) {
            System.out.print("10_to_30_minute_wait_at_rest(" + tenEst.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have a wait time of 30-60 minutes are listed below.");
        for (int i = 0; i < thirtyEst.size() ; i++) {
            System.out.print("30_to_60_minute_wait_at_rest(" + thirtyEst.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples that have a wait time of >60 minutes are listed below.");
        for (int i = 0; i < sixtyEst.size() ; i++) {
            System.out.print("Greater_than_60_minute_wait_at_rest(" + sixtyEst.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }
    /*
    This method splits on Wait
     */
    public void ilpSplit_Wait (){
        ArrayList<Example> trueWait = new ArrayList<Example>();
        ArrayList<Example> falseWait = new ArrayList<Example>();
        for (int i = 0; i < exampleArrayList.size(); i++) {
            if (exampleArrayList.get(i).getWait().equals("T")){
                trueWait.add(exampleArrayList.get(i));
            }
            if (exampleArrayList.get(i).getWait().equals("F")){
                falseWait.add(exampleArrayList.get(i));
            }
        }
        System.out.println("The names of the restaurant examples where the target waits are listed below.");
        for (int i = 0; i < trueWait.size() ; i++) {
            System.out.print("Target_waits_at_rest(" + trueWait.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
        System.out.println("The names of the restaurant examples where the target does not wait are listed below.");
        for (int i = 0; i < falseWait.size() ; i++) {
            System.out.print("~Target_waits_at_rest(" + falseWait.get(i).getName() + ")" + " ");
        }
        System.out.println("\n");
    }

}