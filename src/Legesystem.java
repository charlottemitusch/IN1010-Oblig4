
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.io.*;
import java.util.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Legesystem {
    private static Liste<Legemiddel> legemiddelListe = new Lenkeliste<Legemiddel>();
    private static Liste<Resept> reseptListe = new Stabel<Resept>();
    private static Liste<Lege> legeListe = new SortertLenkeliste<>();
    private static Liste<Pasient> pasientListe = new Lenkeliste<Pasient>();

    Scanner scan = new Scanner(System.in);

    public static void hovedmeny(Scanner input) {

    }


    public void lesFil(File filnavn) throws FileNotFoundException, UlovligUtskrift {
        Scanner scanner = null;
        try {
            scanner = new Scanner(filnavn);
        } catch (FileNotFoundException e) {
            System.out.println("Finner ikke filen");
            return;
        }
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] PasientBiter = scanner.nextLine().split(",");

            while (Character.toString(PasientBiter[0].charAt(0)) != "#") {
                String navn = PasientBiter[0];
                String fnr = PasientBiter[1];
                Pasient pasient = new Pasient(navn, fnr);
                pasientListe.leggTil(pasient);
                PasientBiter = scanner.nextLine().split(",");
            }

            String[] LegemiddelBiter = scanner.nextLine().split(",");
            while (Character.toString(LegemiddelBiter[0].charAt(0)) != "#") {
                String navn = LegemiddelBiter[0];
                String type = LegemiddelBiter[1];
                double pris = Double.parseDouble(LegemiddelBiter[2]);
                double virkestoff = Double.parseDouble(LegemiddelBiter[3]);

                if (type.equals("narkotisk")) {
                    int styrke = Integer.parseInt(LegemiddelBiter[4]);
                    Narkotisk narko = new Narkotisk(navn, pris, virkestoff, styrke);
                    legemiddelListe.leggTil(narko);
                    LegemiddelBiter = scanner.nextLine().split(",");
                } else if (type.equals("vanedannende")) {
                    int styrke = Integer.parseInt(LegemiddelBiter[4]);
                    Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                    legemiddelListe.leggTil(vanedannende);
                    LegemiddelBiter = scanner.nextLine().split(",");
                } else if (type.equals("vanlig")) {
                    int styrke = Integer.parseInt(LegemiddelBiter[4]);
                    Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                    legemiddelListe.leggTil(vanlig);
                    LegemiddelBiter = scanner.nextLine().split(",");
                }

            }
            String[] LegeBiter = scanner.nextLine().split(",");
            while (Character.toString(LegeBiter[0].charAt(0)) != "#") {
                String navn = LegeBiter[0];
                int kontrollid = Integer.parseInt(LegeBiter[1]);

                if (kontrollid == 0) {
                    Lege lege = new Lege(navn);
                    legeListe.leggTil(lege);
                    LegeBiter = scanner.nextLine().split(",");
                } else {
                    Spesialistlege spesialist = new Spesialistlege(navn, kontrollid);
                    legeListe.leggTil(spesialist);
                    LegeBiter = scanner.nextLine().split(",");
                }
            }

            while (scanner.hasNextLine()) {
                String[] ReseptBiter = scanner.nextLine().split(",");
                int legemiddelNummer = Integer.parseInt(ReseptBiter[0]);
                String legeNavn = ReseptBiter[1];
                int pasientID = Integer.parseInt(ReseptBiter[2]);
                String type = ReseptBiter[3];

                Legemiddel legemiddel = null;
                for (Legemiddel l : legemiddelListe) {
                    if (l.hentId() == legemiddelNummer) {
                        legemiddel = l;
                    }
                }

                Lege lege = null;
                for (Lege l : legeListe) {
                    if (l.hentNavn() == legeNavn) {
                        lege = l;
                    }
                }

                Pasient pasient = null;
                for (Pasient p : pasientListe) {
                    if (p.hentId() == pasientID) {
                        pasient = p;
                    }
                }

                if (type.equals("hvit")) {
                    try {
                        int reit = Integer.parseInt(ReseptBiter[4]);
                        HvitRes hvitResept = lege.skrivHvitResept(legemiddel, pasient, reit);
                        reseptListe.leggTil(hvitResept);

                    } catch (UlovligUtskrift e) {
                    }
                } else if (type.equals("blaa")) {
                    try {
                        int reit = Integer.parseInt(ReseptBiter[4]);
                        BlåRes blaaResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                        reseptListe.leggTil(blaaResept);

                    } catch (UlovligUtskrift e) {
                    }
                } else if (type.equals("militaer")) {
                    try {
                        int reit = Integer.parseInt(ReseptBiter[4]);
                        Militaerresept militaeResept = lege.skrivMillitaerResept(legemiddel, pasient, reit);
                        reseptListe.leggTil(militaeResept);

                    } catch (UlovligUtskrift e) {
                    }

                } else if (type.equals("p")) {
                    try {
                        int reit = Integer.parseInt(ReseptBiter[4]);
                        PResept pResept = lege.skrivPResept(legemiddel, pasient);
                        reseptListe.leggTil(pResept);
                    } catch (UlovligUtskrift e) {
                    }
                }
            }
        }
    }

    public void menyvalg() {
        int inputFraBruker = -1;

        while (inputFraBruker != 0) {
            if (inputFraBruker == 1) {
                oversikt();
            } else if (inputFraBruker == 2) {
                leggTilElement();
            } else if (inputFraBruker == 3) {
                brukResept();
            } else if (inputFraBruker == 4) {
                skrivStatestikk();
            } else if (inputFraBruker == 5) {
                skrivTilFil(pasientListe, legemiddelListe, legeListe, reseptListe, "utdata.txt");
            } else if (6 < inputFraBruker || inputFraBruker < -1) {
                System.out.println("Velg en av de fem alternativene");
            }
            System.out.println();
            System.out.println("Hovedmeny:");
            System.out.println("1: Skriv ut fullstendig liste over pasienter, lege, legemidler, og resepter.");
            System.out.println("2: Legg til pasient, lege, legemiddel eller resept.");
            System.out.println("3: Bruk en resept.");
            System.out.println("4: Skriv ut statestikk om systemet.");
            System.out.println("5: Skriv alle data til fil."); // frivillig oppgave
            System.out.println("0: Avslutt.");
            inputFraBruker = Integer.parseInt(scan.nextLine());
        }
    }

    public static void oversikt() {
        System.out.println("Oversikt over pasienter: ");
        for (Pasient p : pasientListe) {
            System.out.println("\n" + p + "\n");
            for (Resept r : p.hentReseptListe()) {
                System.out.print("\n" + r.toString() + "\n");
            }
        }
        System.out.println("\n Leger \n");
        for (Lege l : legeListe) {
            System.out.println(l + "\n");
        }
        System.out.println("\n Legemidler \n");
        for (Legemiddel l : legemiddelListe) {
            System.out.println(l + "\n");
        }

        System.out.println("\n Resepter \n");
        for (Resept r : reseptListe) {
            System.out.println(r + "\n");
        }
    }

    public static void leggTilElement() {
        //la bruker legge til en lege, pasient, resept eller legemiddel
        //sjekker at det er mulig å opprette objekt
        int nyttObjekt = 1;

        while (nyttObjekt != 0) {
            Scanner input = new Scanner(System.in);
            System.out.println("Valgmuligheter:" + " \n"
                    + "0. Avslutt/Gaa tilbake " + " \n"
                    + "1. Legg til en pasient \n"
                    + "2. Legg til en lege \n"
                    + "3. Legg til et legemiddel \n"
                    + "4. Legg til en resept \n");
            nyttObjekt = input.nextInt();

            //legg til pasient
            if (nyttObjekt == 1) {
                Scanner pasient = new Scanner(System.in);
                System.out.println("Tast inn navn på pasient: ");
                String navn = pasient.nextLine();
                System.out.println("Hva er fødselsnummeret(11 siffer) til pasienten? ");
                String id = pasient.nextLine();
                for (Pasient p : pasientListe) {           //sjekker om pasient finnes
                    if (p.hentFodselsnummer().equalsIgnoreCase(id)) {
                        System.out.println("Denne pasienten er allerede registert.");
                        return;
                    }
                }
                Pasient nyPasient = new Pasient(navn, id);
                pasientListe.leggTil(nyPasient);
                //legg til lege
            } else if (nyttObjekt == 2) {
                Scanner lege = new Scanner(System.in);
                System.out.println("Er legen en spesialist? Ja/Nei ");
                String svar = lege.nextLine();

                if (svar.equalsIgnoreCase("Ja")) {
                    System.out.println("Tast inn navn på spesialist:");
                    String legeNavn = lege.nextLine();
                    System.out.println("vennligst oppgi kontrollID:");
                    int kontrollId = lege.nextInt();
                    Spesialistlege nyLege = new Spesialistlege(legeNavn, kontrollId);
                    legeListe.leggTil(nyLege);
                } else if (svar.equalsIgnoreCase("Nei")) {
                    System.out.println("Tast inn navn på legen:");
                    String legeNavn = lege.nextLine();
                    Lege nyLege = new Lege(legeNavn);
                    legeListe.leggTil(nyLege);
                }
                // legge til legemiddel
            } else if (nyttObjekt == 3) {
                Scanner legemiddel = new Scanner(System.in);
                System.out.println("Tast inn type legemiddel:  Vanlig/Vanedannende/Narkotisk ");
                String typeL = legemiddel.nextLine();
                if (typeL.equalsIgnoreCase("Vanlig")) {
                    System.out.println("Oppgi navn på legemdiddel: ");
                    String navnL = legemiddel.nextLine();
                    System.out.println("Oppgi pris på legemiddel: ");
                    Double prisL = legemiddel.nextDouble();
                    System.out.println("Oppgi virkestoff: ");
                    Double virkestoffL = legemiddel.nextDouble();
                    Vanlig nyttLegemiddel = new Vanlig(navnL, prisL, virkestoffL);
                    legemiddelListe.leggTil(nyttLegemiddel);
                } else if (typeL.equalsIgnoreCase("vanedannende")) {
                    System.out.println("Oppgi navn på legemdiddel: ");
                    String navnL = legemiddel.nextLine();
                    System.out.println("Oppgi pris på legemiddel: ");
                    Double prisL = legemiddel.nextDouble();
                    System.out.println("Oppgi virkestoff: ");
                    Double virkestoffL = legemiddel.nextDouble();
                    System.out.println("Oppgi styrke: ");
                    int styrkeL = legemiddel.nextInt();
                    Vanedannende nyttLegemiddel = new Vanedannende(navnL, prisL, virkestoffL, styrkeL);
                    legemiddelListe.leggTil(nyttLegemiddel);
                } else if (typeL.equalsIgnoreCase("narkotisk")) {
                    System.out.println("Oppgi navn på legemdiddel: ");
                    String navnL = legemiddel.nextLine();
                    System.out.println("Oppgi pris på legemiddel: ");
                    Double prisL = legemiddel.nextDouble();
                    System.out.println("Oppgi virkestoff: ");
                    Double virkestoffL = legemiddel.nextDouble();
                    System.out.println("Oppgi styrke: ");
                    int styrkeL = legemiddel.nextInt();
                    Narkotisk nyttLegemiddel = new Narkotisk(navnL, prisL, virkestoffL, styrkeL);
                    legemiddelListe.leggTil(nyttLegemiddel);
                } else {
                    System.out.println("Ugyldig input, vennligst prøv igjen. ");
                }
                //legge til resept
            } else if (nyttObjekt == 4) {
                Scanner resept = new Scanner(System.in);
                System.out.println("Oppgi resepttype: Hvit/Blaa/Militaer/P ");
                String typeR = resept.nextLine();
                if (typeR.equalsIgnoreCase("hvit")) {
                    System.out.println("Navn på lege: ");
                    for (Lege l : legeListe) {
                        System.out.println(l.toString());
                    }
                    String reseptLNavn = resept.nextLine();
                    //Tester for lege
                    boolean godkjent = false;
                    for (Lege godkjenning : legeListe) {
                        if (godkjenning.hentNavn().equalsIgnoreCase(reseptLNavn)) {
                            godkjent = true;
                        }
                    }
                    if (!godkjent) {
                        System.out.println("Ugyldig lege oppgitt, vennligst prøv igjen.");
                        return;
                    }
                    for (Lege lege : legeListe) {
                        if (lege.hentNavn().equalsIgnoreCase(reseptLNavn)) {
                            Lege reseptL = lege;
                            System.out.println("Oppgi pasientens fnr: ");
                            for (Pasient p : pasientListe) {
                                System.out.println(p.toString());
                            }
                            String reseptPFnr = resept.nextLine();
                            for (Pasient pasient : pasientListe) {
                                System.out.println(pasient.hentFodselsnummer());
                                if (pasient.hentFodselsnummer().equalsIgnoreCase(reseptPFnr)) {
                                    Pasient reseptPa = pasient;
                                    System.out.println("Oppgi navn på legemiddel: ");
                                    for (Legemiddel l : legemiddelListe) {
                                        System.out.println(l.toString());
                                    }
                                    String reseptLMNavn = resept.nextLine();
                                    for (Legemiddel legemiddel : legemiddelListe) {
                                        if (legemiddel.hentNavn().equalsIgnoreCase(reseptLMNavn)) {
                                            Legemiddel reseptLM = legemiddel;
                                            System.out.println(" Oppgi antall reit? ");
                                            int reseptReit = resept.nextInt();
                                            try {
                                                Resept reseptN = reseptL.skrivHvitResept(reseptLM, reseptPa, reseptReit);
                                                reseptListe.leggTil(reseptN);
                                                reseptPa.leggTilResept(reseptN);
                                                System.out.println("Ny Hvit resept ble opprettet.");
                                            } catch (UlovligUtskrift e) {
                                                System.out.println("Denne legen kan ikke skrive ut narkotisk legemiddel");
                                            }


                                        }

                                    }
                                }


                            }
                        }
                    }

                } else if (typeR.equalsIgnoreCase("Blaa")) {
                    System.out.println("Navn på lege: ");
                    for (Lege l : legeListe) {
                        System.out.println(l.toString());
                    }
                    String reseptLNavn = resept.nextLine();
                    //Tester for lege
                    boolean godkjent = false;
                    for (Lege godkjenning : legeListe) {
                        if (godkjenning.hentNavn().equalsIgnoreCase(reseptLNavn)) {
                            godkjent = true;
                        }
                    }
                    if (!godkjent) {
                        System.out.println("Ugyldig lege oppgitt, vennligst prøv igjen.");
                        return;
                    }
                    for (Lege lege : legeListe) {
                        if (lege.hentNavn().equalsIgnoreCase(reseptLNavn)) {
                            Lege reseptL = lege;
                            System.out.println("Oppgi pasientens fnr: ");
                            for (Pasient p : pasientListe) {
                                System.out.println(p.toString());
                            }
                            String reseptPFnr = resept.nextLine();
                            for (Pasient pasient : pasientListe) {
                                System.out.println(pasient.hentFodselsnummer());
                                if (pasient.hentFodselsnummer().equalsIgnoreCase(reseptPFnr)) {
                                    Pasient reseptPa = pasient;
                                    System.out.println("Oppgi navn på legemiddel: ");
                                    for (Legemiddel l : legemiddelListe) {
                                        System.out.println(l.toString());
                                    }
                                    String reseptLMNavn = resept.nextLine();
                                    for (Legemiddel legemiddel : legemiddelListe) {
                                        if (legemiddel.hentNavn().equalsIgnoreCase(reseptLMNavn)) {
                                            Legemiddel reseptLM = legemiddel;
                                            System.out.println(" Oppgi antall reit? ");
                                            int reseptReit = resept.nextInt();
                                            try {
                                                Resept reseptN = reseptL.skrivBlaaResept(reseptLM, reseptPa, reseptReit);
                                                reseptListe.leggTil(reseptN);
                                                reseptPa.leggTilResept(reseptN);
                                                System.out.println("Ny Blå resept ble opprettet.");
                                            } catch (UlovligUtskrift e) {
                                                System.out.println("Denne legen kan ikke skrive ut narkotisk legemiddel");
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (typeR.equalsIgnoreCase("militaer")) {
                    System.out.println("Navn på lege: ");
                    for (Lege l : legeListe) {
                        System.out.println(l.toString());
                    }
                    String reseptLNavn = resept.nextLine();
                    //Tester for lege
                    boolean godkjent = false;
                    for (Lege godkjenning : legeListe) {
                        if (godkjenning.hentNavn().equalsIgnoreCase(reseptLNavn)) {
                            godkjent = true;
                        }
                    }
                    if (!godkjent) {
                        System.out.println("Ugyldig lege oppgitt, vennligst prøv igjen.");
                        return;
                    }
                    for (Lege lege : legeListe) {
                        if (lege.hentNavn().equalsIgnoreCase(reseptLNavn)) {
                            Lege reseptL = lege;
                            System.out.println("Oppgi pasientens fnr: ");
                            for (Pasient p : pasientListe) {
                                System.out.println(p.toString());
                            }
                            String reseptPFnr = resept.nextLine();
                            for (Pasient pasient : pasientListe) {
                                System.out.println(pasient.hentFodselsnummer());
                                if (pasient.hentFodselsnummer().equalsIgnoreCase(reseptPFnr)) {
                                    Pasient reseptPa = pasient;
                                    System.out.println("Oppgi navn på legemiddel: ");
                                    for (Legemiddel l : legemiddelListe) {
                                        System.out.println(l.toString());
                                    }
                                    String reseptLMNavn = resept.nextLine();
                                    for (Legemiddel legemiddel : legemiddelListe) {
                                        if (legemiddel.hentNavn().equalsIgnoreCase(reseptLMNavn)) {
                                            Legemiddel reseptLM = legemiddel;
                                            System.out.println(" Oppgi antall reit? ");
                                            int reseptReit = resept.nextInt();
                                            try {
                                                Resept reseptN = reseptL.skrivMillitaerResept(reseptLM, reseptPa, reseptReit);
                                                reseptListe.leggTil(reseptN);
                                                reseptPa.leggTilResept(reseptN);
                                                System.out.println("Ny Militaer resept ble opprettet.");
                                            } catch (UlovligUtskrift e) {
                                                System.out.println("Denne legen kan ikke skrive ut narkotisk legemiddel");
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (typeR.equalsIgnoreCase("p")) {
                    System.out.println("Navn på lege: ");
                    for (Lege l : legeListe) {
                        System.out.println(l.toString());
                    }
                    String reseptLNavn = resept.nextLine();
                    //Tester for lege
                    boolean godkjent = false;
                    for (Lege godkjenning : legeListe) {
                        if (godkjenning.hentNavn().equalsIgnoreCase(reseptLNavn)) {
                            godkjent = true;
                        }
                    }
                    if (!godkjent) {
                        System.out.println("Ugyldig lege oppgitt, vennligst prøv igjen.");
                        return;
                    }
                    for (Lege lege : legeListe) {
                        if (lege.hentNavn().equalsIgnoreCase(reseptLNavn)) {
                            Lege reseptL = lege;
                            System.out.println("Oppgi pasientens fnr: ");
                            for (Pasient p : pasientListe) {
                                System.out.println(p.toString());
                            }
                            String reseptPFnr = resept.nextLine();
                            for (Pasient pasient : pasientListe) {
                                System.out.println(pasient.hentFodselsnummer());
                                if (pasient.hentFodselsnummer().equalsIgnoreCase(reseptPFnr)) {
                                    Pasient reseptPa = pasient;
                                    System.out.println("Oppgi navn på legemiddel: ");
                                    for (Legemiddel l : legemiddelListe) {
                                        System.out.println(l.toString());
                                    }
                                    String reseptLMNavn = resept.nextLine();
                                    for (Legemiddel legemiddel : legemiddelListe) {
                                        if (legemiddel.hentNavn().equalsIgnoreCase(reseptLMNavn)) {
                                            Legemiddel reseptLM = legemiddel;
                                            try {
                                                Resept reseptN = reseptL.skrivPResept(reseptLM, reseptPa);
                                                reseptListe.leggTil(reseptN);
                                                reseptPa.leggTilResept(reseptN);
                                                System.out.println("Ny P resept ble opprettet.");
                                            } catch (UlovligUtskrift e) {
                                                System.out.println("Denne legen kan ikke skrive ut narkotisk legemiddel");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private void brukResept() {
        int antallPasienter = 0;
        Scanner scanner = new Scanner(System.in);

        //Melding til bruker om at ingen pasienter er lagt til i liste
        if (pasientListe.stoerrelse() <= 0) {
            System.out.println("Ingen pasienter funnet");
            return;
        }

        //--------PASIENT---------
        System.out.println("Hvilken pasient vil du se resept for?");

        //Skriver ut pasienter bruker kan velge, tar inn valgt pasient
        for (Pasient pasient : pasientListe) {
            System.out.println(pasient.hentId() + ": " + pasient.hentNavn() + "(fnr " + pasient.hentFodselsnummer() + ")");
            antallPasienter++;
        }

        int valgtPasient;

        //Feilsjekking av input fra bruker
        try {
            valgtPasient = scanner.nextInt();
        } catch (NumberFormatException e) {
            valgtPasient = -1;
        }

        //-----RESEPT------
        if (valgtPasient >= 0 && valgtPasient < antallPasienter) {
            Pasient pasient = pasientListe.hent(valgtPasient);
            System.out.println("Valgt pasient: " + pasient.hentNavn() + "(fnr" + pasient.hentFodselsnummer() + ")\n" + "Hvilken resept vil du bruke?");

            int antalltResepter = 0;

            for (Resept resept : pasient.hentReseptListe()) {
                System.out.println(antalltResepter + ": " + resept.hentLegemiddel() + " (" + resept.hentReit() + " reit)");
                antalltResepter++;
            }

            int valgtResept;

            try {
                valgtResept = scanner.nextInt();
            } catch (NumberFormatException e) {
                valgtResept = -1;
            }

            Resept oensketResept = pasient.hentReseptListe().hent(valgtResept);

            try {
                pasient.hentReseptListe().hent(valgtResept).bruk();

                System.out.println("Brukte resept paa " + oensketResept.hentLegemiddel() + ". Antall gjenværende reit: " + oensketResept.hentReit());

            } catch (Exception e) {
                System.out.println("Kunne ikke bruke resept paa " + oensketResept.hentLegemiddel() + " (ingen gjenvaerende reit).");
            }
        } else {
            System.out.println("Ugyldig valg. Gaar tilbake til hovedmeny");
        }
    }


    protected void skrivStatestikk() {
        int inputFraBruker = -1;

        while (inputFraBruker != 0) {
            if (inputFraBruker == 1) {
                // antall Vanedannende resepter
                int antallVane = 0;
                for (Resept r : reseptListe) {
                    if (r.legemiddel instanceof Vanedannende) {
                        antallVane++;
                    }
                }
                System.out.println("Det er skrevet ut " + antallVane + " vanedannende resepter.");
                System.out.println("Trykk en tast for å komme tilbake til hovedmeny");
                String ventHer = scan.nextLine();
            } else if (inputFraBruker == 2) {
                // antall Narkotiske resepter
                int antallNarko = 0;
                for (Resept r : reseptListe) {
                    if (r.legemiddel instanceof Narkotisk) {
                        antallNarko++;
                    }
                }
                System.out.println("Det er skrevet ut " + antallNarko + " narkotiske resepter.");
                System.out.println("Trykk en tast for å komme tilbake til meny for statestikk");
                String ventHer = scan.nextLine();
            } else if (inputFraBruker == 3) {
                // narkotisk Misbruk
                System.out.println("Skriv ut statestikk om:");
                System.out.println("1: Leger");
                System.out.println("2: Pasienter");
                int valg = Integer.parseInt(scan.nextLine());
                if (valg == 1) {
                    // skriver ut statestikk for leger
                    System.out.println("# (Lege, antall resepter med narkotisk legemiddel)");
                    for (Lege lege : legeListe) {       // for hver lege
                        int antallNarkotiskResept = 0;
                        Lenkeliste<Resept> enkeltlegesReseptListe = lege.hentUtskrevedeResepter(); // Oppretter lenkeliste med reseptene til legen
                        for (Resept resept : enkeltlegesReseptListe) { // for hver resept til legen
                            if (resept.legemiddel instanceof Narkotisk) {    // hvis narkotisk
                                antallNarkotiskResept++;
                            }
                        }
                        System.out.println(lege.navn + ", " + antallNarkotiskResept);
                    }
                    System.out.println("Trykk en tast for å gå tilbake");
                    String ventHer = scan.nextLine();
                } else if (valg == 2) {
                    // skriver ut statestikk for pasienter
                    System.out.println("# (Pasient, antall reit på narkotiske legemidler)");
                    for (Pasient pasient : pasientListe) {             // for hver pasient
                        int totaltAntallNarkotiskReit = 0;
                        Stabel<Resept> pasientReseptListe = pasient.hentReseptListe(); // lager en reseptliste
                        boolean narko = false;
                        for (Resept resept : pasientReseptListe) {  // for hver resept
                            int antallNarkotiskReit = 0;
                            if (resept.legemiddel instanceof Narkotisk) {
                                antallNarkotiskReit += resept.reit;
                                narko = true;                       // Hvis pasienten her noen narkotiske resepter
                            }
                            totaltAntallNarkotiskReit += antallNarkotiskReit;
                        }
                        if (narko) {                                // så skrives de ut
                            System.out.println(pasient.navn + ", (" + totaltAntallNarkotiskReit + " reit)");
                        }
                    }
                }
            } else if (3 < inputFraBruker || inputFraBruker < -1) {
                System.out.println("Velg en av de fire alternativene");
                System.out.println("Trykk en tast for å komme tilbake til meny for statestikk");
                String ventHer = scan.nextLine();
            }
            System.out.println();
            System.out.println("Skriv ut statestikk om:");
            System.out.println("1: Totatlt antall utskrevne resepter på vanedannende legemidler.");
            System.out.println("2: Totatlt antall utskrevne resepter på narkotiske legemidler.");
            System.out.println("3: Narkotisk misbruk.");
            System.out.println("0: Gå tilbake.");
            inputFraBruker = Integer.parseInt(scan.nextLine());
        }
    }

    protected void skrivTilFil(Liste<Pasient> pasientListe, Liste<Legemiddel> legemiddelListe, Liste<Lege> legeListe, Liste<Resept> reseptListe, String utfil) {
        File fil = new File(utfil);
        try {
            PrintWriter skriv = new PrintWriter(fil);
            skriv.append("# Pasienter (navn, fnr)\n");
            for (int i = 0; i < pasientListe.stoerrelse(); i++) {
                Pasient pas = pasientListe.hent(i);
                String pasNavn = pas.hentNavn();
                String fnr = pas.hentFodselsnummer();
                skriv.append(pasNavn + ", " + fnr + "\n");
            }
            skriv.append("# Legemidler (navn, type, pris, virkestoff [, styrke])\n");
            for (int i = 0; i < legemiddelListe.stoerrelse(); i++) {
                Legemiddel legemiddel = legemiddelListe.hent(i);
                String legemiddelNavn = legemiddel.hentNavn();
                String type = "";
                String styrke = "";
                if (legemiddel instanceof Narkotisk) {
                    type = "narkotisk";
                    styrke = Integer.toString(((Narkotisk) legemiddel).hentNarkotiskStyrke());
                } else if (legemiddel instanceof Vanedannende) {
                    type = "vanedannende";
                    styrke = Integer.toString(((Vanedannende) legemiddel).hentVandedannendeStyrke());
                } else if (legemiddel instanceof Vanlig) {
                    type = "vanlig";
                }
                String pris = Double.toString(legemiddel.hentPris());
                String virkestoff = Double.toString(legemiddel.hentVirkestoff());
                skriv.append(legemiddelNavn + ", " + type + ", " + pris + ", " + virkestoff + ", " + styrke + "\n");
            }
            skriv.append("# Leger (navn, kontrollid / 0 hvis vanlig lege)\n");
            for (int i = 0; i < legeListe.stoerrelse(); i++) {
                Lege lege = legeListe.hent(i);
                String legeNavn = lege.hentNavn();
                String kontrollId = Integer.toString(lege.hentKontrollId());
                skriv.append(legeNavn + ", " + kontrollId + "\n");
            }
            skriv.append("# Resepter (legemiddelNummer, legeNavn, pasientID, typeresept, reit)\n");
            for (int i = 0; i < reseptListe.stoerrelse(); i++) {
                Resept resept = reseptListe.hent(i);
                String legemiddelNummer = Integer.toString(resept.hentId());
                String legeNavn = resept.hentLege();
                String pasientId = Integer.toString(resept.hentPasientId());
                String type = "";
                if (resept instanceof Militaerresept) {
                    type = "militaer";
                } else if (resept instanceof PResept) {
                    type = "p";
                } else if (resept instanceof HvitRes) {
                    type = "hvit";
                } else if (resept instanceof BlåRes) {
                    type = "blaa";
                }
                String reit = Integer.toString(resept.hentReit());
                skriv.append(legemiddelNummer + ", " + legeNavn + ", " + pasientId + ", " + type + ", " + reit + "\n");
            }
            skriv.close();
        } catch (FileNotFoundException e) {
        }
    }


}

