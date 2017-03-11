/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ramses;

/**
 *
 * @authors Felipe Tadeu Trinhain Palermo
 *          Frederico de Oliveira Sedrez
 */

public class Registrador {
    String dado = new String();
    
  
    public void setReg(String elem){
        dado = elem;
    }
    
    public String getReg(){
        return dado;
    }
}
