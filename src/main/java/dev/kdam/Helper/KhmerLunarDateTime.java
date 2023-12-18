package dev.kdam.Helper;

import dev.kdam.Utils.AnimalYear;
import dev.kdam.Utils.DayOfWeek;
import dev.kdam.Utils.Era;
import dev.kdam.Entities.LunarDateTime;
import dev.kdam.Entities.SoryaLeangsak;
import dev.kdam.Utils.JourneyMoon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * KhmerLunarDateTime
 */
public class KhmerLunarDateTime {
    private LunarDateTime lunar;
    private LocalDateTime localDateTime;
    public KhmerLunarDateTime(int day, int month, int year) {
        this.lunar = new LunarDateTime();
        localDateTime = LocalDateTime.of( year, month, day,0,0,0 );
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

    public boolean isAkthimeas() {
        if(calculateSoryaLeangsak().get(0).getBodethey() > 24 || calculateSoryaLeangsak().get(0).getBodethey() < 6)
            return true;
        return false;
    }
    public List<SoryaLeangsak> calculateSoryaLeangsak() {
        List<SoryaLeangsak> lst = new ArrayList<>();
        lst.add( this.getSoryaLeangsakByLesserEra( this.localDateTime.getYear() + 543 - 1182 ) );
        lst.add( this.getSoryaLeangsakByLesserEra( this.localDateTime.getYear() + 544 - 1182 ) );

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
        return AnimalYear.year[(this.localDateTime.getYear() + 9) % 12];
    }

    /**
     * @return String
     */
    public String calEra() {
        return Era.sak[(this.localDateTime.getYear() + 2) % 10];
    }

    /**
     * @return
     */
    private String calDayOfWeek() {
        return DayOfWeek.day_of_week[this.localDateTime.getDayOfWeek().getValue()];
    }
    public String toString() {
        return String.format( "ថ្ងៃ%s %s ខែ%s ឆ្នាំ%s %s ព.ស.%s", this.calDayOfWeek(), this.lunar.getDayOfWeek(),this.lunar.getDayOfWeek(), this.calAnimalYear(), this.calEra(), this.lunar.getDayOfWeek());
    }
}
