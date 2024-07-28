package com.projetanais.acte;

import java.time.LocalDate;

public class ConversionMois {

    static String[] mois = { " ", "janoary", "febroary", "marsa", "aprily", "may", "jiona", "jiolay", "aogositra", "sektambra",
            "oktobra", "novambra", "desambra" };

    public LocalDate ConversionMois(String date) {
        String[] dates = date.split(" ");
        int int_mois = 0;

        int jour = Integer.parseInt(dates[0]);
        int annee = Integer.parseInt(dates[2]);

        for (int i = 1; i < mois.length; i++) {
            if (dates[1].toLowerCase().equals(mois[i])) {
                int_mois = i;
                break;
            }
        }

        if (int_mois == 0) {
            throw new IllegalArgumentException("Mois invalide : " + dates[1]);
        }

        LocalDate local = LocalDate.of(annee, int_mois, jour);

        return local;
    }
}
