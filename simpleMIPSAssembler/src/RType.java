import java.util.*;

/**
 * Created by Adam on 10/15/2015.
 */
public class RType {
    public char checkEncode(String line){
        /*
         * This method takes in a line. Then finds the index of the first '\n'
         * creates a substring from that index. That substring is then checked against
         * all of the available mips encodings for R-type instructions .
         * Returns: char a  Rd, Rs, Rt
         *          char b  Rd, Rt , SA
        *           char c  Rs
         *          char u - for unknown
         */
        int index;
        List<String> aType = Arrays.asList("add", "sub", "slt", "and", "or");
        List<String> bType = Arrays.asList("sll","srl");
        List<String> cType = Arrays.asList("jr");

        if (line.indexOf(' ') != -1) {
            index = line.indexOf(' ');
        } else {
            return 'u';
        }
        String checkStr = line.substring(0, index);
        if (aType.contains(checkStr)) {
            return 'a';
        }
        if (bType.contains(checkStr)) {
            return 'b';
        }
        if (cType.contains(checkStr)) {
            return 'c';
        }

        return 'u';
    }
    public List<Integer> theType(String line, char encoding){
        if(encoding == 'a'){
            return aType(line);
        }
        if(encoding == 'b'){
            return  bType(line);
        }
        if(encoding == 'c'){
            return  cType(line);
        }
        else{
            return null;
        }
    }
    public List<Integer> aType(String line){
        /* Take in a full line of instruction of R-type of the a-type encoding.
         * a-type is Rd, Rs, Rt
         * Returns List<Integer>
         *     list.get(0) = op
         *     list.get(1) = Rs
         *     list.get(2) = Rt
         *     list.get(3) = Rd
         *     list.get(4) = SA
         *     list.get(5) = Funct-code
         */

        int index =0;
        HashLookup hashLookup = new HashLookup();
        List<Integer> list = new ArrayList<>();
        LinkedHashMap hash = hashLookup.makeOpHashMap();
        LinkedHashMap funct = hashLookup.makeFunctHashMap();
        LinkedHashMap reg = hashLookup.makeRegHashMap();
        list.add(0, null);
        list.add(1, null);
        list.add(2, null);
        list.add(3, null);
        list.add(4, null);
        list.add(5, null);

        StringTokenizer st = new StringTokenizer(line," ,()[]$");


        String checkStr = st.nextToken();
        String opString = checkStr;
        char a;

        Object op = hash.get(checkStr);
        list.set(0, (Integer)op);

        checkStr = st.nextToken();
        a = checkStr.charAt(0);
        if(Character.isLetter(a)){
            Object rd = reg.get(checkStr);
            list.set(3, (Integer)rd);
        }
        else{
            list.set(3, Integer.parseInt(checkStr));
        }

        checkStr = st.nextToken();
        a = checkStr.charAt(0);
        if(Character.isLetter(a)){
            Object rs = reg.get(checkStr);
            list.set(1, (Integer)rs);
        }
        else{
            list.set(1, Integer.parseInt(checkStr));
        }


        checkStr = st.nextToken();
        a = checkStr.charAt(0);
        if(Character.isLetter(a)){
            Object rt = reg.get(checkStr);
            list.set(2, (Integer) rt);
        }
        else{
            list.set(2, Integer.parseInt(checkStr));
        }

        Object fCode = funct.get(opString);
        list.set(5, (Integer) fCode);

        list.set(4, 0);


        return list;
    }

    public List<Integer> bType(String line) {
            /* Take in a full line of instruction of R-type of the a-type encoding.
         * b-type is Rd, Rt, SA
         * Returns List<Integer>
         *     list.get(0) = op
         *     list.get(1) = Rs
         *     list.get(2) = Rt
         *     list.get(3) = Rd
         *     list.get(4) = SA
         *     list.get(5) = Funct-code
         */



        int index =0;
        HashLookup hashLookup = new HashLookup();
        List<Integer> list = new ArrayList<>();
        LinkedHashMap hash = hashLookup.makeOpHashMap();
        LinkedHashMap funct = hashLookup.makeFunctHashMap();
        LinkedHashMap reg = hashLookup.makeRegHashMap();
        list.add(0, null);
        list.add(1, null);
        list.add(2, null);
        list.add(3, null);
        list.add(4, null);
        list.add(5, null);

        StringTokenizer st = new StringTokenizer (line, " ()[]$,");

        String checkStr = st.nextToken();
        String opString = checkStr;
        char a = checkStr.charAt(0);

        Object op = hash.get(checkStr);
        list.set(0, (Integer)op);

        checkStr = st.nextToken();
        a = checkStr.charAt(0);
        if(Character.isLetter(a)){
            Object rd = reg.get(checkStr);
            list.set(3, (Integer)rd);
        }
        else{
            list.set(3, Integer.parseInt(checkStr));
        }
        //Doesn't take in an Rs set to ZERO
        list.set(1, 0);

        checkStr = st.nextToken();
        a = checkStr.charAt(0);
        if(Character.isLetter(a)){
            Object rt = reg.get(checkStr);
            list.set(2, (Integer)rt);
        }
        else{
            list.set(2, Integer.parseInt(checkStr));
        }

        String sAmount = st.nextToken();

        list.set(4, Integer.parseInt(sAmount));

        Object fCode = funct.get(opString);
        list.set(5, (Integer) fCode);

        return list;
    }

    public List<Integer> cType(String line) {
                /* Take in a full line of instruction of R-type of the a-type encoding.
         * a-type is Rd, Rs, Rt
         * Returns List<Integer>
         *     list.get(0) = op
         *     list.get(1) = Rs
         *     list.get(2) = Rt
         *     list.get(3) = Rd
         *     list.get(4) = SA
         *     list.get(5) = Funct-code
         */
        HashLookup hashLookup = new HashLookup();
        List<Integer> list = new ArrayList<>();
        LinkedHashMap hash = hashLookup.makeOpHashMap();
        LinkedHashMap funct = hashLookup.makeFunctHashMap();
        LinkedHashMap reg = hashLookup.makeRegHashMap();
        list.add(0, null);
        list.add(1, null);
        list.add(2, null);
        list.add(3, null);
        list.add(4, null);
        list.add(5, null);

        StringTokenizer st = new StringTokenizer (line, " ()[]$");


        String checkStr = st.nextToken();
        String opString = checkStr;
        char a = checkStr.charAt(0);
        Object op = hash.get(checkStr);
        list.set(0, (Integer)op);

        checkStr = st.nextToken();
        a = checkStr.charAt(0);
        if(Character.isLetter(a)){
            Object rs = reg.get(checkStr);
            list.set(1, (Integer)rs);
        }
        else{
            list.set(1, Integer.parseInt(checkStr));
        }

        list.set(2, 0);
        list.set(3, 0);
        list.set(4, 0);
        Object fCode = funct.get(opString);
        list.set(5, (Integer) fCode);

        return list;
    }
}
