package dev.kdam.Helper;

import dev.kdam.Entities.LunarDateTime;
import dev.kdam.Entities.SoryaLeangsak;
import dev.kdam.Utils.ZodiacYear;
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
    private final SoryaLeangsakHelper helper;
    public KhmerLunarDateTime(int day, int month, int year) {
        this.lunar = new LunarDateTime();
        localDate = LocalDate.of( year, month, day);
        //initial Lesser Era
        this.helper = new SoryaLeangsakHelper(year);
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

    /**
     * គណនារកឆ្នាំដែលមាន ៣៦៦ថ្ងៃសុរិយគតិខ្មែរ
     * @return boolean
     */
    public boolean is366KhmerSolar() {
        return this.helper.is366KhmerSolar();
    }
    /**
     * ឆ្នាំបកតិមាស អធិកវារៈ ឬ ចន្ទ្រាធិមាស ១ឆ្នាំមាន១២ខែ ដោយខែជេស្ឋមាន៣០ថ្ងៃ
     * @return boolean
     */
    public boolean isAthikvearak() {
        return this.helper.isAthikvearak();
    }
    /**
     * អធិកមាស បកតិវារៈ ១ឆ្នាំមាន១៣ខែ(អាសាឍ២ដង) = ៣៨៤ថ្ងៃ
     * @return boolean
     */
    public boolean isAthikmeas(){
       return this.helper.isAthikmeas();
    }
    /**
     * check ches with 30 days
     * @return boolean
     */
    public boolean isChes30Days(){
        if(!this.helper.isAthikmeas()){
            if(!this.helper.isAthikvearak()){
                SoryaLeangsakHelper previous_year = new SoryaLeangsakHelper(this.localDate.getYear() - 1 );
                return previous_year.isAthikmeas() && previous_year.isAthikvearak();
            }else return true;
        }else {
            if(this.helper.isAthikvearak()) return false;
        }
        return false;
    }
    /**
     * @return String
     */
    public String calAnimalYear() {
        return ZodiacYear.year[(this.localDate.getYear() + 9) % 12];
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
