package io.github.kdamdev.khmerformat.utils;

import io.github.kdamdev.khmerformat.enums.Number;
import io.github.kdamdev.khmerformat.enums.NumberText;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Numeric
 */
public class Numeric {
    private final String number;

    /**
     * @param number
     */
    public Numeric(String number)  {
        if (!number.matches("^[^.,]*\\.?[^.,]*$")) throw new IllegalArgumentException("Invalid String Number.");
        this.number = number;
    }

    /**
     * @param number
     */
    public Numeric(Integer number) {
        this.number = String.valueOf(number);
    }
    /**
     * @return String
     */
    public String toKhmer(){
        return  Arrays.stream( this.number.split( "" )).map( x-> x.indexOf( '.' ) != -1 || x.indexOf( '-' ) != -1 ? x : Number.num[Integer.parseInt( x )]
        ).collect( Collectors.joining("") );
    }

    /**
     * allowing for a maximum of 15 decimal places
     * @param comma
     * @return
     */
    public String toKhmer(boolean comma){
        String[] parts = this.number.split("\\.");
        parts[0] = parts[0].replaceAll("(?<=\\d)(?=(\\d{3})+$)", ",");
        return  Arrays.stream(String.join( ".", parts ).split( "" )).map( x-> x.indexOf( '.' ) != -1 || x.indexOf( ',' ) != -1 || x.indexOf( '-' ) != -1 ? x : Number.num[Integer.parseInt( x )]
        ).collect( Collectors.joining("") );
    }

    /**
     * @return
     */
    public String toKhmerText() {
        String[] value = this.number.split( "\\.");
        long num = Long.parseLong(value[0]);

        String fractionalWord = "";
        if(value.length == 2 ) {
            fractionalWord = NumberText.sign[0] + Arrays.stream( value[1].split("") ).map(m-> NumberText.one_digit[Integer.parseInt( m )] ).collect( Collectors.joining("") );
        }

        if (num == 0) {
            return NumberText.one_digit[0];
        }

        int i = 0;
        StringBuilder words = new StringBuilder();

        while (num > 0) {
            if (num % 1000 != 0) {
                words.insert( 0, helper( num % 1000 ) + NumberText.four_digit[i]);
            }
            num /= 1000;
            i++;
        }
        return words + fractionalWord;
    }

    /**
     * @param number
     * @return
     */
    private static String helper(long number) {
        if (number == 0) {
            return "";
        } else if (number < 10) {
            return NumberText.one_digit[(int) number];
        } else if (number < 100) {
            return NumberText.two_digit[(int) (number / 10)] + helper(number % 10);
        } else {
            return NumberText.one_digit[(int) (number / 100)] + NumberText.three_digit[0] + helper(number % 100);
        }
    }
}
