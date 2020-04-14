package sample;


public class EncryptionClass {


    public static String ProcessEncryption(String text, int constant){

        char[] temp = text.toCharArray();

        for(int i = 0; i < text.length() ; i+= 2){

            int key = ((int)temp[i%text.length()] - (int)temp[(i+1)%text.length()]) + constant;

            temp[i] = (char) ((int)temp[i] + key);
            temp[(i+1)%text.length()] = (char) ((int)temp[(i+1)%text.length()] + key);

        }


        return String.valueOf(temp);
    }


}
