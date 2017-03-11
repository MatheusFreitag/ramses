package ramses;

/**
 *
 * @author moni e leti
 */
public class Op_ramses {  
    
    public Op_ramses(Memoria memoria){
        this.memoria = memoria;
    }
    
    public int executaOperacao(String opcode[], int indice, int Final){
        String palavra = opcode[indice];
        String copiaPalavra = palavra.substring(0, 4);
        int tipoEnd = this.identificaModo(indice);
        int tipoReg = this.identificaReg(indice);
        
        switch(copiaPalavra){
            case "0000":
                indice = this.nop(indice);
            break;
            
            case "0001":
                indice = this.str(indice + 1, tipoEnd, tipoReg);
            break;
            
            case "0010":
                indice = this.ldr(indice + 1, tipoEnd, tipoReg);
            break;
            
            case "0011":
                indice = this.add(indice + 1, tipoEnd, tipoReg);
            break;
            
            case "0100":
                indice = this.or(indice + 1, tipoEnd, tipoReg);
            break;
            
            case "0101":
                indice = this.and(indice + 1, tipoEnd, tipoReg);
            break;
            
            case "0110":
                indice = this.not(indice, tipoReg);
            break;
            
            case "0111":
                indice = this.sub(indice + 1, tipoEnd, tipoReg);
            break;
            
            case "1000":
                indice = this.jmp(indice + 1, tipoEnd);
            break;
            
            case "1001":
                indice = this.jn(indice + 1, tipoEnd);
            break;
            
            case "1010":
                indice = this.jz(indice + 1, tipoEnd);
            break;
            
            case "1011":
               indice = this.jc(indice + 1, tipoEnd);
            break;
            
            case "1100":
                indice = this.jsr(indice + 1, tipoEnd);
            break;
            
            case "1101":
                indice = this.neg(indice, tipoReg);
            break;
            
            case "1110":
                indice = this.shr(indice, tipoReg);
            break;
            
            case "1111":
                indice = this.hlt(indice);
            break;    
        }
        return indice;
    }
    
    private int nop(int indice){
        setPC(getPC() + 1);
    return indice + 1;
    }
    
    private int str(int indice, int tipoEnd, int tipoReg){
        int registrador = getRegistrador(tipoReg);
        
        if (tipoEnd == 0){
            setPalavra(getPalavra(indice), registrador);
        } else if (tipoEnd == 1){
            setPalavraIndireto(getPalavra(indice), registrador);
        } else if (tipoEnd == 3){
            setPalavra(getPalavra(indice) + getRegistrador(2), registrador);
        } else 
            return -3;
        
        setPC(getPC() + 1);
        return indice + 1;
    }
    
    private int ldr(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        setRegistrador(operando, tipoReg);
        
        atualizaEstadosNZ(operando);
        setPC(getPC() + 1);
        return indice + 1;
    }
    
    private int add(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        int registrador = getRegistrador(tipoReg) + operando;
        setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        
        String atualizaC = getRegistardorNZC();
        String novaString = new String();
        
        novaString = atualizaC.substring(0, 2)+'0'+atualizaC.substring(3, 8);
        setRegistradorNZC(novaString);
                
        setPC(getPC() + 1);
        return indice + 1;
        
    }
    
    private int or(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        int registrador = getRegistrador(tipoReg) || operando;
        setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        setPC(getPC() + 1);
        return indice + 1;
    }
    
    private int and(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        int registrador = getRegistrador(tipoReg) && operando;
        setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        setPC(getPC() + 1);
        return indice + 1;
    }
    
    private int not(int indice, int tipoReg){
        int registrador = ~getRegistrador(tipoReg);
        setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        setPC(getPC() + 1);
        return indice + 1;        
    }
    
    private int sub(int indice, int tipoEnd, int tipoReg){
        int operando = recebeOperando(indice, tipoEnd);
        int registrador = getRegistrador(tipoReg) - operando;
        setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        
        String atualizaC = getRegistardorNZC();
        String novaString = new String();
        
        if (registrador < 0){
            novaString = atualizaC.substring(0, 2)+'1'+atualizaC.substring(3, 8);
            setRegistradorNZC(novaString);
        }else{
            novaString = atualizaC.substring(0, 2)+'0'+atualizaC.substring(3, 8);
            setRegistradorNZC(novaString);
        }
            
        setPC(getPC() + 1);
        return indice + 1;
    }
    
    private int jmp(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        
        if (tipoEnd == 2){
            setPC(getPC + 1);
            return indice + 1;
        } else {
            setPC(endereco);
            return endereco;
        }
            
 
    }
    
    private int jn(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        String verificaNZC = getRegistradorNZC();

        if (verificaNZC.charAt(0) == '1'){
            if (tipoEnd == 2){
                setPC(getPC() + 1);
                return indice + 1;
            } else {
                setPC(endereco);
                return endereco;
            }
        } else if (verificaNZC.charAt(0) == '0'){
            setPC(getPC() + 1);
            return indice + 1;
        }
    }
    
    private int jz(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        String verificaNZC = getRegistradorNZC();
               
        
        if (verificaNZC.charAt(1) == '1'){
            if (tipoEnd == 2){
                setPC(getPC() + 1);
                return indice + 1;
            } else {
                setPC(endereco);
                return endereco;
            }
        } else if (verificaNZC.charAt(1) == '0'){
            setPC(getPC() + 1);
            return indice + 1;
        }
    }
    
    private int jc(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        String verificaNZC = getRegistradorNZC();
               
        
        if (verificaNZC.charAt(2) == '1'){
            if (tipoEnd == 2){
                setPC(getPC() + 1);
                return indice + 1;
            } else {
                setPC(endereco);
                return endereco;
            }
        } else if (verificaNZC.charAt(2) == '0'){
            setPC(getPC() + 1);
            return indice + 1;
        }
    
    }
    
    private int jsr(int indice, int tipoEnd){
        int endereco = recebeOperando(indice, tipoEnd);
        setEndereco(endereco, getPC()); //seta o pc no endereço
        setPC(endereco + 1);
        
        return endereco + 1;
    }
    
    private int neg(int indice, int tipoReg){
        int registrador = -1*getRegistrador(tipoReg);
        setRegistrador(registrador, tipoReg);
        
        atualizaEstadosNZ(registrador);
        
        String atualizaC = getRegistardorNZC();
        String novaString = new String();
        
        if (registrador < 0){
            novaString = atualizaC.substring(0, 2)+'1'+atualizaC.substring(3, 8);
            setRegistradorNZC(novaString);
        }else{
            novaString = atualizaC.substring(0, 2)+'0'+atualizaC.substring(3, 8);
            setRegistradorNZC(novaString);
        }
            
        
        setPC(getPC() + 1);
        return indice + 1;    
    }
    
    private int shr(int indice, int tipoReg){
        int registrador = getRegistrador(tipoReg);
        registrador = registrador >> 1;
        setRegistrador(registrador, tipoReg);
        
        
        atualizaEstadosNZ(registrador);
        
        String atualizaC = getRegistardorNZC();
        String novaString = new String();
        
        novaString = atualizaC.substring(0, 2)+'0'+atualizaC.substring(3, 8);
        setRegistradorNZC(novaString);
        
        setPC(getPC() + 1);
        return indice + 1;
    }
    
    private int hlt(int indice){
        
        setPC(getPC() + 1);
        return indice + 1; 
        
    }
    
    private int identificaReg(int indice){
        String copiaPalavra = this.memoria.getPalavra(N);
        copiaPalavra = copiaPalavra.substring(4, 6);
        
        if(copiaPalavra.equalsIgnoreCase("00")){
            return 0; //registrador A (RA)
        } else if (copiaPalavra.equalsIgnoreCase("01")){
            return 1; //registrador B (RB)
        } else if (copiaPalavra.equalsIgnoreCase("10")){
            return 2; //registrador X (RX)
        } else 
            return -1; //registrador indefinido
    }
    
    private int identificaModo(int indice){
        String copiaPalavra = this.memoria.getPalavra(N);
        copiaPalavra = copiaPalavra.substring(6, 9);
        
        if(copiaPalavra.equalsIgnoreCase("00")){
            return 0; //endereçamento direto
        } else if (copiaPalavra.equalsIgnoreCase("01")){
            return 1; //endereçamento indireto
        } else if (copiaPalavra.equalsIgnoreCase("10")){
            return 2; //endereçamento imediato
        } else if (copiaPalavra.equalsIgnoreCase("11")){
            return 3; //endereçamento indexado
        } else
            return -2;
    }
    
    private int recebeOperando (int indice, int modo){
        int operando = 0;
        
        if (modo == 0) { //direto
            int i = Converter.stringParaInt(this.memoria.getPalavra(indice));
            operando = Converter.stringParaInt(this.memoria.getPalavra(i));
        } else if (modo == 1){ //indireto
            int i = Converter.stringParaInt(this.memoria.getPalavra(indice));
            operando = Converter.stringParaInt(this.memoria.getPalavraInd(i));
        } else if (modo == 2){ //imediato
            operando = Converter.stringParaInt(this.memoria.getPalavra(indice));
        } else if (modo == 3){ //indexado
            int i = Converter.stringParaInt(this.memoria.getPalavra(indice)) + getRegistrador(2);
            operando = Converter.stringParaInt(this.memoria.getPalavra(i));            
        }
        
        return operando;          
    }
    
    private void atualizaEstadosNZ(int registrador){
        String atualizaNZC = getRegistardorNZC();
        String novaString = new String();
        
        if (registrador == 0){
            novaString = "01"+atualizaNZC.substring(2, 8);
        }else if (registrador < 0){
            novaString = "10"+atualizaNZC.substring(2, 8);
        }else if (registrador > 0){
            novaString = "00"+atualizaNZC.substring(2, 8);
        }
        
        setRegistradorNZC(novaString);        
    }
}
