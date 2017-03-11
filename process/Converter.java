/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ramses;

import static java.lang.Integer.toBinaryString;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author teste
 */
public class Converter {
    
    public static int stringParaInt(String string) {
        int binary = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '1') {
                binary += Math.pow(2, (string.length() - 1) - i);
            }
        }
        return binary;
    }

    public static String paraStringBin8(int inteiro) {
        String str;

        //tratamento para casos maiores que 16 bits = 65535
        if (inteiro > 255) {
            System.out.println("Erro: palavra maior que 8 bits");
            return null;
        }

        str = toBinaryString(inteiro);
        int stringLength = str.length();
        for (int i = 0; i <= 7 - stringLength; i++) {
            str = "0" + str;
        }

        return str;
    }

}

