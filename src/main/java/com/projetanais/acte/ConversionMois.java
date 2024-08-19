package com.projetanais.acte;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

public class ConversionMois {

    static String[] mois = { " ", "janoary", "febroary", "marsa", "aprily", "may", "jiona", "jiolay", "aogositra",
            "septambra",
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

    public String ConversionInverse(String m) {
        String vrai = mois[Integer.parseInt(m)];
        return vrai;
    }

    public String formatDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
        String formate = sf.format(date);
        formate = formate.replace("janvier", "janoary")
                .replace("février", "febroary")
                .replace("mars", "marsa")
                .replace("avril", "aprily")
                .replace("mai", "may")
                .replace("juin", "jona")
                .replace("juillet", "jolay")
                .replace("août", "aogositra")
                .replace("septembre", "septambra")
                .replace("octobre", "oktobra")
                .replace("novembre", "novambra")
                .replace("décembre", "desambra");

        return formate;
    }

    public String formatDate_enLettre(Date date){
        ChiffreEnLettre ch = new ChiffreEnLettre();
        SimpleDateFormat sf = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
        String formate = sf.format(date);
        formate = formate.replace("janvier", "janoary")
                .replace("février", "febroary")
                .replace("mars", "marsa")
                .replace("avril", "aprily")
                .replace("mai", "may")
                .replace("juin", "jona")
                .replace("juillet", "jolay")
                .replace("août", "aogositra")
                .replace("septembre", "septambra")
                .replace("octobre", "oktobra")
                .replace("novembre", "novambra")
                .replace("décembre", "desambra");

        String[] format = formate.split(" ");
        String jour = ch.convertirEnLettre(Integer.parseInt(format[0]));
        String annee = ch.convertirEnLettre(Integer.parseInt(format[2]));

        return (jour + " " + format[1] + " ,taona "+ annee);
    }

}
