package sample;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncryptionClassTest {

    @Test
    void processEncryption() {
        String password1 = "hotdog";
        String password2 = "12345";
        String password3 = "#hotdog90134$@(qerewzx|\\%";

        System.out.println(EncryptionClass.ProcessEncryption(password1,3));

        assertEquals(password1,DecryptionClass.ProcessDecryption(EncryptionClass.ProcessEncryption(password1,3),3));
        assertEquals(password2,DecryptionClass.ProcessDecryption(EncryptionClass.ProcessEncryption(password2,3),3));
        assertEquals(password3,DecryptionClass.ProcessDecryption(EncryptionClass.ProcessEncryption(password3,3),3));
    }
}