package dev.kdam.Helper;

import dev.kdam.Entities.LunarDateTime;
import dev.kdam.Entities.SoryaLeangsak;
import dev.kdam.Utils.AnimalYear;
import dev.kdam.Utils.DayOfWeek;
import dev.kdam.Utils.Era;
import dev.kdam.Utils.JourneyMoon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * KhmerLunarDateTime
 */
public class KhmerLunarDateTime {
    private final LunarDateTime lunar;
    private final LocalDate localDate;
    private final SoryaLeangsak xLesserEra;
    private final SoryaLeangsak zLesserEra;
    public KhmerLunarDateTime(int day, int month, int year) {
        this.lunar = new LunarDateTime();
        localDate = LocalDate.of( year, month, day);
        //initial Lesser Era
        this.xLesserEra = getSoryaLeangsakByLesserEra( (year + 543) - 1182  );
        this.zLesserEra = getSoryaLeangsakByLesserEra( (year + 544) - 1182  );
    }
    public int calculateLesserEra() {
        return 0;
    }
    public int getDayOfYear() {
        return 0;
    }

    /**
     *
     * @param month
     * @return 29 for odd month or 30 even month
     */
    public int getTotalDayOfMonth(int month){
        return month % 2 == 0 ? 30 : 29;
    }

    public String getDayOfMonth(int day){
        int t = day % 15 == 0 ? 15 : day % 15;
        return t + " " + (day > 15 ? JourneyMoon.WANING.getLabel() : JourneyMoon.WAXING.getLabel());
    }
    public boolean is366KhmerSolar() {
        return xLesserEra.getKromathopol() <= 207;
    }
    public boolean isAkthimeas() {
        if (xLesserEra.getBodethey() > 24 || zLesserEra.getBodethey() < 6)
            return true;
//        else if (xLesserEra.getBodethey() == 24 && ze) {
//
//        }
        return false;
    }
    public boolean isChantreathimeas(int kromathopol, int avoman, int newavoman){
//        if(is366KhmerSolar(kromathopol)){
//            return avoman < 127;
//        }else{
//            return avoman < 138 && avoman == 137 && newavoman == 0;
//        }
        return false;

    }
    public List<SoryaLeangsak> calculateSoryaLeangsak() {
        List<SoryaLeangsak> lst = new ArrayList<>();
        lst.add( this.getSoryaLeangsakByLesserEra( this.localDate.getYear() + 543 - 1182 ) );
        lst.add( this.getSoryaLeangsakByLesserEra( this.localDate.getYear() + 544 - 1182 ) );

        return lst;
    }

    private SoryaLeangsak getSoryaLeangsakByLesserEra(int LesserEra) {
        SoryaLeangsak soryaLeangsak = new SoryaLeangsak();
        soryaLeangsak.setLesserEra( LesserEra );
        soryaLeangsak.setHarkun((292207 * LesserEra + 373) / 800 + 1);
        soryaLeangsak.setKromathopol(800 - (292207 * LesserEra + 373) % 800 );
        soryaLeangsak.setAvaman((11 * soryaLeangsak.getHarkun() + 650) % 692);
        soryaLeangsak.setBodethey( (soryaLeangsak.getHarkun() + ((11 * soryaLeangsak.getHarkun() + 650) / 692)) % 30);
        return soryaLeangsak;
    }
    /**
     * @return String
     */
    public String calAnimalYear() {
        return AnimalYear.year[(this.localDate.getYear() + 9) % 12];
    }

    /**
     * @return String
     */
    public String calEra() {
        return Era.sak[(this.localDate.getYear() + 2) % 10];
    }

    /**
     * @return
     */
    private String calDayOfWeek() {
        return DayOfWeek.day_of_week[0];
    }
    public String toString() {
        return String.format( "ថ្ងៃ%s %s ខែ%s ឆ្នាំ%s %s ព.ស.%s", this.calDayOfWeek(), this.lunar.getDayOfWeek(),this.lunar.getDayOfWeek(), this.calAnimalYear(), this.calEra(), this.lunar.getDayOfWeek());
    }
}
