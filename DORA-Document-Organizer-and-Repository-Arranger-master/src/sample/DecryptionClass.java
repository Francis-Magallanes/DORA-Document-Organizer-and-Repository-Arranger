package sample;

public class  DecryptionClass {


    public static String ProcessDecryption(String text, int constant){

        char[] temp = text.toCharArray();

        boolean isOdd = temp.length%2 != 0;

        if(isOdd){


            for(int i = text.length() - 1 ; i >= 0 ; i-=2){

                int key = ((int) temp[(i) % text.length()] - (int)temp[(i+1)%text.length()]) + constant;

                temp[i] = (char) (temp[i] - key);
                temp[(i+1)%text.length()] = (char) ((int) temp[(i+1)%text.length()] - key);

            }

        }
        else{

            for(int i = text.length() - 2 ; i >= 0 ; i-=2){

                int key = ((int)text.charAt((i) % text.length()) - (int)text.charAt((i+1)%text.length())) + constant;

                temp[i] = (char) (temp[i] - key);
                temp[(i+1)%text.length()] = (char) ((int) temp[(i+1)%text.length()] - key);

            }

        }



        return String.valueOf(temp);
    }
}
