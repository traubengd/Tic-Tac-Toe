/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Thomas Raub
 */


public class TicTacToe {

    static ArrayList <Integer> genomenPositiesX = new ArrayList<>();
    static ArrayList <Integer> genomenPositiesO = new ArrayList<>();
    
    public static Boolean spelAfgelopen = false;
    
    public static void main(String[] args) {
        
        //array genereren van 5x5 als speelbord, met tweede en vierde rijen en kolommen als esthetische verdelers
        char [][] speelbord = {{'1', '|', '2', '|', '3'},
                               {'-', '+', '-', '+', '-'},
                               {'4', '|', '5', '|', '6'},
                               {'-', '+', '-', '+', '-'},
                               {'7', '|', '8', '|', '9'}};
         
        
        //welkomstbericht voor gebruikers om te starten
        System.out.println("Welkom bij Boter Kaas en Eieren!");
        
        //speelbord tonen
        printSpeelbord (speelbord);
             
       
        //constante loop voor het spelverloop
        while (true) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Speler X: Kies een speelvlak");
        
        //check of input een integer is, en anders nieuwe vragen
        while (scan.hasNextInt() == false) {
            System.out.println("Geen nummer gekozen! Kies een bestaand speelvlak!");
            scan.next();
        }
        
        //aflezen welke positie speler X een symbool wil plaatsen
        int positieX = scan.nextInt();        
        
        //check voor of gekozen vakje bestaat
        while(positieX < 1 || positieX > 9) {
            System.out.println("Geen bestaand speelvlak gekozen! Kies een bestaand speelvlak!");
            positieX = scan.nextInt();
        }
        
        //check voor of gekozen vakje nog beschikbaar is
        while(genomenPositiesX.contains(positieX) || genomenPositiesO.contains(positieX)){
            System.out.println("Speelvlak niet meer beschikbaar! Kies een andere!");
            positieX = scan.nextInt();
        }

        //symbool X plaatsen op gekozen positie
        symboolPlaatsen (speelbord, positieX, "spelerX");
        
        //checken of iemand gewonnen heeft, en zo ja melden
        String resultaat;
        resultaat = checkWinst();
        //als inderdaad iemand gewonnen heeft en spel dus is afgelopen, dan melden en beeindigen
        if(spelAfgelopen == true) {
            System.out.println(resultaat);
            break;
        }
        
        //beurt wisselt naar andere speler
        System.out.println("Speler O: Kies een speelvlak");
        
        //check of input een integer is, en anders nieuwe vragen
        while (scan.hasNextInt() == false) {
            System.out.println("Geen nummer gekozen! Kies een bestaand speelvlak!");
            scan.next();
        }
        
        //aflezen welke positie speler O een symbool wil plaatsen
        int positieO = scan.nextInt();
        
        //check voor of gekozen vakje bestaat
        while(positieO < 1 || positieO > 9) {
            System.out.println("Geen bestaand speelvlak gekozen! Kies een bestaand speelvlak!");
            positieO = scan.nextInt();
        }
        
        //check voor of gekozen vakje nog beschikbaar is
        while(genomenPositiesX.contains(positieO) || genomenPositiesO.contains(positieO)){
            System.out.println("Speelvlak niet meer beschikbaar! Kies een andere!");
            positieO = scan.nextInt();
        }
        //symbool O plaatsen op gekozen positie
        symboolPlaatsen (speelbord, positieO, "spelerO");
        
        //checken of iemand gewonnen heeft, en zo ja melden
        resultaat = checkWinst();
        //als inderdaad iemand gewonnen heeft en spel dus is afgelopen, dan melden en beeindigen
        if(spelAfgelopen == true) {
            System.out.println(resultaat);
            break;
        }
    
        }
    }
    
    //method voor het tonen van het speelbord, door elke rij afzonderlijk te printen
    public static void printSpeelbord(char[][] speelbord) {
        System.out.println(speelbord[0]);
        System.out.println(speelbord[1]);
        System.out.println(speelbord[2]);
        System.out.println(speelbord[3]);
        System.out.println(speelbord[4]);    
}
    
    //method voor plaatsen van spelers symbolen op gekozen posities
    public static void symboolPlaatsen (char[][] speelbord, int positie, String speler){
            
            char symbool = ' '; 
    
            if (speler.equals ("spelerX")) {
                symbool = 'X';
                genomenPositiesX.add(positie);
            }
            else if (speler.equals ("spelerO")) {
                symbool = 'O';
                genomenPositiesO.add(positie);
            }
    
            //wisselt op de door de speler gekozen positie het symbool naar symbool van betreffende speler
            switch(positie){
                case 1 -> speelbord[0][0] = symbool;
                case 2 -> speelbord[0][2] = symbool;
                case 3 -> speelbord[0][4] = symbool;
                case 4 -> speelbord[2][0] = symbool;
                case 5 -> speelbord[2][2] = symbool;
                case 6 -> speelbord[2][4] = symbool;
                case 7 -> speelbord[4][0] = symbool;
                case 8 -> speelbord[4][2] = symbool;
                case 9 -> speelbord[4][4] = symbool;
                default -> {
            }
        }
        
        //speelbord tonen na het plaatsen van symbool, zodat nieuwe situatie zichtbaar is
        printSpeelbord (speelbord);
}

    //method voor checken of een van de spelers gewonnen heeft
    public static String checkWinst() {
        
        //lijnen die gelijk moeten zijn voor een speler om te winnen
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9); 
        List diag2 = Arrays.asList(7, 5, 3);
        
        List<List> winstConditie = new ArrayList<>();
        winstConditie.add(topRow); 
        winstConditie.add(midRow); 
        winstConditie.add(botRow); 
        winstConditie.add(leftCol); 
        winstConditie.add(midCol); 
        winstConditie.add(rightCol);
        winstConditie.add(diag1); 
        winstConditie.add(diag2); 
       
        //bepalen of iemand gewonnen heeft en aangeven dat spel afgelopen is
        for(List l : winstConditie){
            if(genomenPositiesX.containsAll(l)){
                spelAfgelopen = true;
                return "Speler X heeft gewonnen!";
                
            }
            else if (genomenPositiesO.containsAll(l)){
                spelAfgelopen = true;
                return "Speler O heeft gewonnen!";
            }
            //checken voor gelijkspel, namelijk als bord vol is maar niemand gewonnen heeft
            else if (genomenPositiesX.size() + genomenPositiesO.size() == 9) {
                spelAfgelopen = true;
                return "Helaas! Het is gelijkspel!";
            }
        }
        
        return "";
    }

}