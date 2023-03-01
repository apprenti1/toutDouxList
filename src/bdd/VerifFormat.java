package bdd;

public class VerifFormat {

    private boolean verifStringFormat(String text){
        if ((   text.indexOf('"')+
                text.indexOf("'")+
                text.indexOf('\n')+
                text.indexOf('\r')+
                text.indexOf(' ')+
                text.indexOf('\t')+
                text.indexOf('(')+
                text.indexOf(')'))==-8){
            return true;
        } else{ return false;}}
}
