package dev.kdam.Helper;

import dev.kdam.Utils.AnimalYear;
import dev.kdam.Utils.DayOfWeek;
import dev.kdam.Utils.Era;
import dev.kdam.entities.LunarDateTime;
import dev.kdam.entities.SoryaLeangsak;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int getDayOFYear() {
        return 0;
    }

    public boolean isAkthimeas() {
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
        soryaLeangsak.setBodethey( soryaLeangsak.getHarkun() + (int)((11 * soryaLeangsak.getHarkun() + 650) / 692) - (30 * 17069));
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
