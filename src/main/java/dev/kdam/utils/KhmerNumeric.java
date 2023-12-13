package dev.kdam.utils;

import dev.kdam.constraints.Numeric;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Numeric
 */
public class KhmerNumeric {
    private final Object number;
    public KhmerNumeric(Object number) {
        this.number = number;
    }
    /**
     * @return String
     */
    public String toKhmer(){
        return  Arrays.stream(this.number.toString().split( "" )).map( x-> x.indexOf( '.' ) != -1 ? x : Numeric.num[Integer.parseInt( x )]
        ).collect( Collectors.joining("") );
    }

    /**
     * allowing for a maximum of 15 decimal places
     * @param comma
     * @return
     */
    public String toKhmer(boolean comma){
        DecimalFormat formatter = new DecimalFormat("#,###.##############");
        return  Arrays.stream(formatter.format(this.number).split( "" )).map( x-> x.indexOf( '.' ) != -1 || x.indexOf( ',' ) != -1 ? x : Numeric.num[Integer.parseInt( x )]
        ).collect( Collectors.joining("") );
    }

    public String toKhmerText(){
        return "";// Arrays.stream(this.number.split( "" )).map( x-> dev.kdam.constraints.Numeric.num[Integer.parseInt( x )]).collect( Collectors.joining("") );
    }
}
