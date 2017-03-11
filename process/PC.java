/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ramses;

/**
 *
 * @author FelipeTadeu
 */
public class PC extends Registrador {


    public void incrementa() {
        int a = Converter.stringParaInt(dado);
        dado = Converter.paraStringBin8(a+1);
    }

}