package com.example.julian.sistemaaulas;

import android.util.Base64;

/**
 * Created by JULIAN on 21/11/2017.
 */

public class Codificador {
    public static String codificarTexto(String text){
        return Base64.encodeToString(text.getBytes(),
                Base64.DEFAULT).replace("(\\n|r)","");
    }
    public static String decodificarTexto(String text){
        return new String(Base64.decode(text, Base64.DEFAULT));
    }
}
