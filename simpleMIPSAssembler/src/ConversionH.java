import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Adam on 10/14/2015.
 */
public class ConversionH {
    public String listToHex(List<Integer> l, char type){
        String r = "", temp = "";
        r += adjustedBinary(l.get(0),6);
        if(type == 'j'){
            r += adjustedBinary(l.get(1),26);
            temp = r;
            r = "0x";
            for(int i = 0; i<temp.length(); i+=4){
                Object obj = makeHexHashMap().get(temp.substring(i,i+4)).toString();
                r+= obj;
            }
            return r;
        }
        r += adjustedBinary(l.get(1),5);
        r += adjustedBinary(l.get(2),5);
        if(type == 'i'){
            r += adjustedBinary(l.get(3),16);
            temp = r;
            r = "0x";
            for(int i = 0; i<temp.length(); i+=4){
                Object obj = makeHexHashMap().get(temp.substring(i,i+4));
                r+= obj;
            }
            return r;
        }
        r += adjustedBinary(l.get(3),5);
        r += adjustedBinary(l.get(4),5);
        r += adjustedBinary(l.get(5),6);
        temp = r;
        r = "0x";
        for(int i = 0; i<temp.length(); i+=4){
            Object obj = makeHexHashMap().get(temp.substring(i,i+4));
            r += obj;
        }
        return r;
    }

    public String adjustedBinary(int i, int disp){
        String r = "";
        while(i>=1){
            r+= i % 2;
            i = i / 2;
        }
        while(r.length() < disp)
            r = "0" + r;
        return r;
    }

    public LinkedHashMap makeHexHashMap(){
        LinkedHashMap<String,String> hash = new LinkedHashMap<>();
        hash.put("0000", "0");
        hash.put("0001","1");
        hash.put("0010","2");
        hash.put("0011","3");
        hash.put("0100","4");
        hash.put("0101","5");
        hash.put("0110","6");
        hash.put("0111","7");
        hash.put("1000","8");
        hash.put("1001","9");
        hash.put("1010","A");
        hash.put("1011","B");
        hash.put("1100","C");
        hash.put("1101","D");
        hash.put("1110","E");
        hash.put("1111","F");
        return hash;
    }

}


