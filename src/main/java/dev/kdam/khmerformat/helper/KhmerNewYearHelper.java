package dev.kdam.khmerformat.helper;

import dev.kdam.khmerformat.entity.Sun;
import dev.kdam.khmerformat.entity.SoryaLeangsak;
import dev.kdam.khmerformat.entity.VanabatDay;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class KhmerNewYearHelper {
    private final SoryaLeangsak lesserEra;
    private final int year;
    public KhmerNewYearHelper(int year) {
        this.lesserEra = getSoryaLeangsakByLesserEra( year + 544 - 1182 );
        this.year = year;
    }
    public boolean is366KhmerSolar() {
        return lesserEra.getKromathopol() <= 207;
    }
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
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
    public int LeungsakDay() {
//        switch (lesserEra.getHarkun() % 7){
//            case 0:
//                return DayOfWeek.day_of_week[6];
//            case 1:
//                return DayOfWeek.day_of_week[0];
//            case 2:
//                return DayOfWeek.day_of_week[1];
//            case 3:
//                return DayOfWeek.day_of_week[2];
//            case 4:
//                return DayOfWeek.day_of_week[3];
//            case 5:
//                return DayOfWeek.day_of_week[4];
//            case 6:
//            default:
//                return DayOfWeek.day_of_week[5];
//        }
        return lesserEra.getHarkun() % 7;
    }
    /**
     * @return day and month of leung sak
     */
    public int[] getLeungsakDay() {
        KhmerNewYearHelper old_year = new KhmerNewYearHelper(this.year - 1);
        int bodethey = this.lesserEra.getBodethey();
        if(old_year.isAthikmeas() && old_year.isAthikvearak()) {
            ++bodethey;
        }
        return new int[] {bodethey >= 6 ? bodethey : ++bodethey, bodethey >= 6 ? 5 : 6};
    }
    private Sun getAverageOfSun(int sotin) {
        Sun sun = new Sun();
        int pre_kromathopol = getSoryaLeangsakByLesserEra(lesserEra.getLesserEra() - 1).getKromathopol();
        int tmp = 800 * sotin + pre_kromathopol;
        sun.setReasey(tmp / 24350);
        sun.setAngsar((tmp % 24350) / 811);
        sun.setLibda((((tmp % 24350) % 811) / 14) - 3);
        return sun;
    }

    /**
     * មធ្យមព្រះអាទិត្យ ដក R2.A20.L0
     * @param averageSun 
     * @return
     */
    private Sun getPressureOfSun(Sun averageSun) {
        Sun s1 = new Sun();
        s1.setReasey( averageSun.getReasey() < 2 ? averageSun.getReasey() + 12 - 2 : averageSun.getReasey() - 2);
        s1.setAngsar( averageSun.getAngsar() - 20);
        s1.setLibda( averageSun.getLibda() );
        //
        Sun s2 = new Sun();
        switch (s1.getReasey()){
            case 0:
            case 1:
            case 2:
                // RS2 = RS1
                s2 = s1;
                break;
            case 3:
            case 4:
            case 5:
                // RS2 = R6.A0.L0 - RS1
                s2.setReasey(6 - s1.getReasey());
                s2.setAngsar(-s1.getAngsar());
                s2.setLibda(-s1.getLibda());
                break;
            case 6:
            case 7:
            case 8:
                // RS2 = RS1 - R6.A0.L0
                s2.setReasey(s1.getReasey() - 6);
                s2.setAngsar(s1.getAngsar());
                s2.setLibda(s1.getLibda());
                break;
            case 9:
            case 10:
            case 11:
                // RS2 =  R11.A29.L60 - RS1
                s2.setReasey(11 - s1.getReasey());
                s2.setAngsar(29 - s1.getAngsar());
                s2.setLibda(60 - s1.getLibda());
                break;
            default:
                throw new RuntimeException("Invalid R");
        }

        Sun phol = getPhol( s2 );
        Sun preSun;
        if(s1.getReasey() >= 6) {
            preSun = new Sun(
                    averageSun.getReasey() + phol.getReasey(),
                    averageSun.getAngsar() + phol.getAngsar(),
                    averageSun.getLibda() + phol.getLibda());
        }else {
            preSun = new Sun(
                    averageSun.getReasey() - phol.getReasey(),
                    averageSun.getAngsar() - phol.getAngsar(),
                    averageSun.getLibda() - phol.getLibda());
        }
        return convertToReasey(preSun);
    }

    /**
     * ១រាសី = ៣០អង្សា, ១អង្សា = ៦០លិប្ដា
     * @param sun
     * @return
     */
    private static Sun convertToReasey(Sun sun) {
        int l = sun.getLibda() / 60;
        int rl = sun.getLibda() % 60;

        int a = (sun.getAngsar() + l) / 30;
        int ra = (sun.getAngsar() + l) % 30;

        int r = (sun.getReasey() + a) % 12;

        return new Sun(r,ra,rl);
    };
    private static int getKhan(Sun sun) {
        return sun.getAngsar() >= 15 ? 2 * sun.getReasey() + 1 : 2 * sun.getReasey();
    }
    private static int getPouichalip(Sun sun) {
        return sun.getAngsar() >= 15 ? 60 * (sun.getAngsar() - 15) + sun.getLibda(): 60 * sun.getAngsar() + sun.getLibda();
    }
    private static Sun getPhol(Sun info) {
        int k = getKhan(info);
        int p = getPouichalip(info);
        int[] chaya = getSunChaya( k );
        int q1 = (p * chaya[1]) / 900 ;
        return new Sun(0, (q1 + chaya[2]) / 60, (q1 + chaya[2]) % 60);
    }
    private VanabatDay getNumberOfVanabatDay() {
        List<Integer> sotin = is366KhmerSolar() ?
                new ArrayList<>( Arrays.asList(363, 364, 365, 366)) :
                new ArrayList<>(Arrays.asList(362, 363, 364, 365));
        VanabatDay vanabatDay = new VanabatDay();
        for (int i : sotin) {
            Sun pressureOfSun = getPressureOfSun(getAverageOfSun( i ));
            if (pressureOfSun.getReasey() == 0 && pressureOfSun.getAngsar() == 0) {
                vanabatDay.setNumberOfVanabat(is366KhmerSolar() ? 365 - i : 364 - i);
                vanabatDay.setPressureOfSun(pressureOfSun);
                break;
            }
        }
        return vanabatDay;
    }
    /**
     * ដោយដឹងថា ២៤ ម៉ោងមាន ៦០ លិប្ដា ដូចនេះ ១ លិប្ដា មាន ២៤ នាទី
     * @return
     */
    public int[] getKhmerNewYearTime() {
        Sun pressureSun = getNumberOfVanabatDay().getPressureOfSun();
        Duration duration = Duration.ofMinutes( 1440 - pressureSun.getLibda() * 24L);
        return new int[] {(int) duration.toHours(), (int) (duration.toMinutes() % 60)};
    }
    /**
     * @return
     */
    public int[] getNewYearDay() {
        System.out.println();
        System.out.println( Arrays.toString( getLeungsakDay() ) );
        int[] leungSak = getLeungsakDay();

        for (int i = 1; i <= getNumberOfVanabatDay().getNumberOfVanabat() + 1; i++) {
            leungSak[0]--;
            if (leungSak[0] == 0 ) {
                leungSak[0] = 29; // ដោយយើងដឹងថាខែ ចេត្រ មាន ២៩ថ្ងៃ
                leungSak[1]--;
            }
        }
        return leungSak;
    }
    private static int[] getSunChaya(int khan) {
        switch (khan){
            case 0:
                return new int[] {0, 35, 0};
            case 1:
                return new int[] {1, 32, 35};
            case 2:
                return new int[] {2, 27, 67};
            case 3:
                return new int[] {3, 22, 94};
            case 4:
                return new int[] {4, 13, 116};
            case 5:
                return new int[] {5, 5, 129};
            default:
                return new int[] {-1, -1, 134}; // undefined value
        }
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
