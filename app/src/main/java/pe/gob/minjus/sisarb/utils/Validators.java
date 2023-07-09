package pe.gob.minjus.sisarb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    private Validators() {
    }

    public static boolean validOnlyAlfaNumericos(String textToValidate){
        Pattern patron = Pattern.compile("^[\\p{L}0-9\\sÁÉÍÓÚáéíóúñÑ]*$");
        return patron.matcher(textToValidate).matches();
    }


    public static boolean validUrl(String url) {
        String regex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static boolean validOnlyIntegerStrings(String textToValidate){
        Pattern patron = Pattern.compile("^\\d+$");
        return patron.matcher(textToValidate).matches();
    }

    public static String removeExtraSpacesAndUpperCase(String input) {
        if (input==null) return null;
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(" ").trim().toUpperCase();
    }


    /**
     * Compara los valores si después de aplicar la función removeExtraSpaces
     *
     * @param field1 primer camppo a comparar
     * @param field2 segundo campo a comparar
     * @return retorna true en caso sean iguales, y false si son diferentes o si alguno de ellos sea nulo
     */
    public static boolean compareFieldsStrings(String field1, String field2){
        if(field1 == null && field2 == null){
            return true;
        }else if (field1 == null || field2 == null){
            return false;
        }
        else{
            return removeExtraSpacesAndUpperCase(field1).equals(removeExtraSpacesAndUpperCase(field2));
        }
    }

    /**
     * Compara los valores enteros
     *
     * @param field1 primer camppo a comparar
     * @param field2 segundo campo a comparar
     * @return retorna true en caso sean iguales, y false si son diferentes o si alguno de ellos sea nulo
     */
    public static boolean compareFieldsGeneric(Object field1, Object field2){
        if(field1 == null && field2 == null){
            return true;
        }else if (field1 == null || field2 == null){
            return false;
        }else{
            return field1.equals(field2);
        }
    }
}
