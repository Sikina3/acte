package com.projetanais.acte;

class ChiffreEnLettre {

    public String convertirEnLettre(int valeur) {
        String chiffres[] = {
            "aotra",
            "iray",
            "roa",
            "telo",
            "efatra",
            "dimy",
            "enina",
            "fito",
            "valo",
            "sivy"
        };       

        String dizaines[] = {
            "folo",
            "roapolo",
            "telopolo",
            "efapolo",
            "dimampolo",
            "enimpolo",
            "fitopolo",
            "valopolo",
            "sivifolo"
        };

        String resultat = "";
        int centaine = valeur % 1000;
        int reste = (int)(valeur / 1000);
        int unite = centaine % 10;
        
        resultat += chiffres[unite];

        int dizaine = (centaine % 100) - unite;

        if(dizaine != 0) {
            resultat += " ambiny " + dizaines[(dizaine / 10) - 1];
        } 
        centaine = centaine - dizaine - unite;

        if(centaine != 0){
            resultat += ( (centaine / 100) == 1  ? " aminy zato" : " sy " + (dizaines[(centaine / 100) - 1]).replace("polo", "njato").replace("efanjato", "efajato").replace("mn", "n") );  
        }

        int millieme = reste % 10;
        reste = reste / 10;

        if(millieme != 0){
            resultat += " sy " + (millieme == 1 ? "arivo" : chiffres[millieme] + " arivo");
        }
        int dimillieme = reste % 10;
        reste  = reste / 10;

        if(dimillieme != 0){
            resultat += " sy " + chiffres[dimillieme] + " alina";
        }

        int centmillieme = reste % 10;
        reste = reste / 10;

        if(centmillieme != 0){
            resultat += " sy " + chiffres[centmillieme] + " hetsy";
        }

        unite = reste % 10;
        reste = reste / 10;

        dizaine = reste % 10;
        reste = reste / 10;

        centaine = reste % 10;
        reste = reste / 10;

        if(unite != 0){
            resultat += " sy " + chiffres[unite];
        }

        if(dizaine != 0){
            resultat += " ambiny " + dizaines[dizaine - 1];
        }

        if(centaine != 0){
            resultat += (centaine == 1 ? " aminy zato" : " sy " + (dizaines[centaine - 1]).replace("polo", "njato").replace("efanjato", "efajato").replace("mn", "n"));
        }

        if(reste != 0){
            resultat += " sy " + ( reste != 1 ? chiffres[reste] : "") + " arivo";
        }

        if(unite != 0 || dizaine != 0 || reste != 0){
            resultat += " tapitrisa";
        }

        resultat = resultat.replace("aotra sy", "");
        resultat = resultat.replace("aotra ambiny", "");

        return resultat;
    }

    String ConvertHeure(String heureString){
        String[] h = heureString.split(":");
        String minutes = "";

        String heure = convertirEnLettre(Integer.parseInt(h[0]));

        if(!h[1].equals("00")){    
          minutes = " sy " + convertirEnLettre(Integer.parseInt(h[1])) + " minitra";
        }

        return heure + " ora "+ minutes;
    }
}