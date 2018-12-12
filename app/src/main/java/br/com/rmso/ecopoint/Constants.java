package br.com.rmso.ecopoint;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static String bundlePoints = "points";
    public static List<String> mPointTypes = new ArrayList<String>() {
        {
            add("ELETONICO");
            add("BATERIAS");
            add("RECICLAVEIS");
            add("MEDICAMENTO");
            add("OLEO");
            add("OUTROS");
            add("GERAL");
        }
    };
}
