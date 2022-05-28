import sampleapp.DataAndTime;
import sampleapp.FaceBookPagePostAutomate;
import sampleapp.Post;

import java.util.Arrays;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        String s="10 February\n" +
                "  ·";
        String[] split = s.split("\n");
        System.out.println("------------------------------>>>");
        System.out.println(DataAndTime.getDateFormat(split[0]));

        System.out.println("----------------------------->>>>>>>>>");
        s+="\b\b";



    }
}
/*
33 mins
  ·
success
23
true
25 May at 17:18
  ·
success
24
true
24 May at 12:13
  ·
success
25
true

"25 December 2021\n" +
                "  ·";

10 February
  ·

*/
