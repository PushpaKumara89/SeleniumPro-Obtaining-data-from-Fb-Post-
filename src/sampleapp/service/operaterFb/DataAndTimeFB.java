package sampleapp.service.operaterFb;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataAndTimeFB {
    public static String getDateFormat(String date){
        try {
            String[] dateCondition ={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November" , "December"};

            for (int i = 0; i <dateCondition.length ; i++) {
                String[] d = date.split(" ");
                LocalDate now = LocalDate.now();
                Date dat = new Date();
                //
                if(date.contains("Yesterday")){
                    String[] time = d[2].split(":");
                    String yesterday = (now.minusDays(1)).format(DateTimeFormatter.ISO_DATE);
                    String[] yd = yesterday.split("-");
                    LocalDateTime ofDate = LocalDateTime.of(Integer.parseInt(yd[0]),Integer.parseInt(yd[1]),Integer.parseInt(yd[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]));
                    return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(ofDate);

                }else if (date.contains("hrs")){
                    int month = now.getMonth().getValue();
                    int day= now.getDayOfMonth();
                    int hr=dat.getHours();

                    if (hr>Integer.parseInt(d[0])){
                        hr-=Integer.parseInt(d[0]);
                    }else {
                        day--;hr+=(24-Integer.parseInt(d[0]));
                    }
                    if (day<=0){
                        day+=30;
                        month--;
                    }

                    LocalDateTime ofDate = LocalDateTime.of(now.getYear(),month,day,hr,0);
                    return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(ofDate);


                }else if (date.contains("mins")){
                    int minute=dat.getMinutes();
                    int hr=dat.getHours();
                    if (minute>Integer.parseInt(d[0])){
                        minute-=Integer.parseInt(d[0]);
                    }else {
                        hr--;minute+=(60-Integer.parseInt(d[0]));
                    }

                    LocalDateTime ofDate = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),hr,minute);
                    return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(ofDate);

                }else if (date.contains(dateCondition[i]) ){
                    if(d.length>2 && d[2].equals("at")){
                        String[] time = d[3].split(":");

                        LocalDateTime ofDate = LocalDateTime.of(LocalDate.now().getYear(), (i + 1), Integer.parseInt(d[0]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));
                        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(ofDate);
                    }else if (d.length==3){
                        LocalDateTime ofDate = LocalDateTime.of(Integer.parseInt(d[2]),(i+1), Integer.parseInt(d[0]), 0, 0);
                        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(ofDate);
                    }else if (d.length==2){
                        LocalDateTime ofDate = LocalDateTime.of(now.getYear(),(i+1), Integer.parseInt(d[0]), 0, 0);
                        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(ofDate);
                    }
                }
            }

        }catch (DateTimeException e){
            System.out.println(e);
        }
     return null;
    }
}
