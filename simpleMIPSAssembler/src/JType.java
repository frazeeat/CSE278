import java.util.*;
/**
 * Created by Adam on 10/15/2015.
 */
public class JType {
    public char checkEncode(String line){
        /*
         * This method takes in a line. Then finds the index of the first '\n'
         * creates a substring from that index. That substring is then checked against
         * all of the available mips encodings for I-type instructions .
         * Returns: char a  Rt,Rs,imm
         *          char b  Rt,offset(Rs)
         *          char c  Rs,Rt,offset
         *          char u - for unknown
         */
        int index;
        List<String> aType = Arrays.asList("j", "jal");

        if (line.indexOf(' ') != -1) {
            index = line.indexOf(' ');
        } else {
            return 'u';
        }
        String checkStr = line.substring(0, index);
        if (aType.contains(checkStr)) {
            return 'a';
        }
        return 'u';
    }
    //takes in a J Type line of mips code and returns a list of integers coresponding to the op code and address
    public List<Integer> aType(String line){
        //HashMap lookup
        HashLookup hashLookup = new HashLookup();
        //return list
        List<Integer> r = new ArrayList<>();
        LinkedHashMap hash = hashLookup.makeOpHashMap();
        //seperates string into tokens based on characters space, comma, ( , ) , [ , ] , $
        StringTokenizer st = new StringTokenizer(line," ,()[]$");
        //selects next string in the array created by StringTokenizer
        String op = st.nextToken();
        //get the op integer from the HashMap
        Object hm = hash.get(op);
        //add the op code to the list r
        r.add(0,(Integer)hm);
        //get the address integer
        String address = st.nextToken();
        //add the address to the list r
        r.add(1,Integer.parseInt(address));
        return r;
    }
    public List<Integer> theType(String line, char encoding){
        if(encoding == 'a'){
            return aType(line);
        }
        return null;
    }
}
