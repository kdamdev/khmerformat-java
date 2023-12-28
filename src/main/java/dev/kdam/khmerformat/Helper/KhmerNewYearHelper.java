package dev.kdam.khmerformat.Helper;

import dev.kdam.khmerformat.Entity.AverageSun;
import dev.kdam.khmerformat.Entity.SoryaLeangsak;
import dev.kdam.khmerformat.Enum.DayOfWeek;

/**
 *
 */
public class KhmerNewYearHelper {
    private final SoryaLeangsak lesserEra;
    private final int year;
    public KhmerNewYearHelper(int year) {
        this.lesserEra = getSoryaLeangsakByLesserEra( year + 544 - 1182  );
        this.year = year;
    }
    public boolean is366KhmerSolar() {
        return lesserEra.getKromathopol() <= 207;
    }
    /**
     * ឆ្នាំបកតិមាស អធិកវារៈ ឬ ចន្ទ្រាធិមាស ១ឆ្នាំមាន១២ខែ ដោយខែជេស្ឋមាន៣០ថ្ងៃ
     * @return boolean
     */
    public boolean isAthikvearak() {
        int nextAvaman = this.getSoryaLeangsakByLesserEra(lesserEra.getLesserEra() + 1).getAvaman();
        return (this.is366KhmerSolar() && lesserEra.getAvaman() < 127) ||
                ((lesserEra.getAvaman() != 137 || nextAvaman != 0 ) &&
                        lesserEra.getAvaman() < 138) ;
    }
    /**
     * អធិកមាស បកតិវារៈ ១ឆ្នាំមាន១៣ខែ(អាសាឍ២ដង) = ៣៨៤ថ្ងៃ
     * @return boolean leap year
     */
    public boolean isAthikmeas(){
        int nextBodethey = this.getSoryaLeangsakByLesserEra(lesserEra.getLesserEra() + 1).getBodethey();
        return (lesserEra.getBodethey() != 25 || nextBodethey != 5) && ((lesserEra.getBodethey() > 24 || lesserEra.getBodethey() < 6) ||
                (lesserEra.getBodethey() == 24 && nextBodethey == 6));
    }

    /**
     * ឆ្នាំបកតិមាស អធិកវារៈ ឬ ចន្ទ្រាធិមាស ១ឆ្នាំមាន១២ខែ ដោយខែជេស្ឋមាន៣០ថ្ងៃ = ៣៥៥ថ្ងៃ ។
     * @return boolean leap month (Ches 30days)
     */
    public boolean isChes30Days(){
        if(!this.isAthikmeas()){
            if(!this.isAthikvearak()){
                KhmerNewYearHelper previous_year = new KhmerNewYearHelper(this.year - 1 );
                return previous_year.isAthikmeas() && previous_year.isAthikvearak();
            }else return true;
        }else {
            if(this.isAthikvearak()) return false;
        }
        return false;
    }
    public String LeungsakDay() {
        switch (lesserEra.getHarkun() % 7){
            case 0:
                return DayOfWeek.day_of_week[6];
            case 1:
                return DayOfWeek.day_of_week[0];
            case 2:
                return DayOfWeek.day_of_week[1];
            case 3:
                return DayOfWeek.day_of_week[2];
            case 4:
                return DayOfWeek.day_of_week[3];
            case 5:
                return DayOfWeek.day_of_week[4];
            case 6:
            default:
                return DayOfWeek.day_of_week[5];
        }
    }
    public AverageSun getAverageOfSun(int sotin) {
        AverageSun sun = new AverageSun();
        int pre_kromathopol = getSoryaLeangsakByLesserEra(lesserEra.getLesserEra() - 1).getKromathopol();
        int tmp = 800 * sotin + pre_kromathopol;
        sun.setReasey(tmp / 24350);
        sun.setAngsar((tmp % 24350) / 811);
        sun.setLibda((((tmp % 24350) % 811) / 14) - 3);
        return sun;
    }
    public String PressureOfSun() {
        return "";
    }
    private SoryaLeangsak getSoryaLeangsakByLesserEra(int LesserEra) {
        SoryaLeangsak soryaLeangsak = new SoryaLeangsak();
        soryaLeangsak.setLesserEra( LesserEra );
        soryaLeangsak.setHarkun((292207 * LesserEra + 373) / 800  + 1);
        soryaLeangsak.setKromathopol(800 - (292207 * LesserEra + 373) % 800 );
        soryaLeangsak.setAvaman((11 * soryaLeangsak.getHarkun() + 650) % 692);
        soryaLeangsak.setBodethey( (soryaLeangsak.getHarkun() + ((11 * soryaLeangsak.getHarkun() + 650) / 692)) % 30);
        return soryaLeangsak;
    }
}
