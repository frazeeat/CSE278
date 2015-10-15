import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Adam on 10/14/2015.
 */
public class ConversionH {
    //listToHex takes a list of Integers and a char for the type of function it is
    //listToHex returns a the hex version of the integers combined accordingly with how mips works
    public String listToHex(List<Integer> l, char type){
        //r is a string that will obtain the binary values and concatinate them together and will return
        String r = "", temp = "";
        //op code conversion to binary for all types
        r += adjustedBinary(l.get(0),6);
        //break off for J type
        if(type == 'j'){
            //address for J type
            r += adjustedBinary(l.get(1),26);
            //temp will store r while r takes back in the binary values to hex
            temp = r;
            //base for hex string
            r = "0x";
            //loop for every set of 4 binary values converting them to hex
            for(int i = 0; i<temp.length(); i+=4){
                Object obj = makeHexHashMap().get(temp.substring(i,i+4)).toString();
                r+= obj;
            }
            return r;
        }
        //rs for R and I type
        r += adjustedBinary(l.get(1),5);
        //rt for R and I type
        r += adjustedBinary(l.get(2),5);
        //break off for I type
        if(type == 'i'){
            //address for I type
            r += adjustedBinary(l.get(3),16);
            //temp will store r while r takes back in the binary values to hex
            temp = r;
            //base for hex string
            r = "0x";
            //loop for every set of 4 binary values converting them to hex
            for(int i = 0; i<temp.length(); i+=4){
                Object obj = makeHexHashMap().get(temp.substring(i,i+4));
                r+= obj;
            }
            return r;
        }
        //rd for R type
        r += adjustedBinary(l.get(3),5);
        //shamt for R type
        r += adjustedBinary(l.get(4),5);
        //funct for R type
        r += adjustedBinary(l.get(5),6);
        //temp will store r while r takes back in the binary values to hex
        temp = r;
        //base for hex string
        r = "0x";
        //loop for every set of 4 binary values converting them to hex
        for(int i = 0; i<temp.length(); i+=4){
            Object obj = makeHexHashMap().get(temp.substring(i,i+4));
            r += obj;
        }
        return r;
    }

    //takes an int and number of desired bytes for the returning string containing binary for the initial int
    public String adjustedBinary(int i, int disp){
        String r = "";
        //loop that converts integer to binary
        while(i>=1){
            //add mod 2 of the given integer to show the convertion between decimal and binary
            r = i % 2 + r;
            //divide i by 2 to move on to next binary part of the decimal number
            i = i / 2;
        }
        //loop to format the string of binary numbers to the size of the desired number of bytes
        while(r.length() < disp)
            //adds a 0 to the front of r to set the binary to the correct size of desired number of bytes
            r = "0" + r;
        return r;
    }

    //hash map to convert binary strings to hex strings
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