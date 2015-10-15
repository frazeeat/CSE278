import java.util.*;

/**
 * Created by Adam on 10/14/2015.
 */
public class IType {
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
        List<String> aType = Arrays.asList("addi", "slti", "andi", "ori");
        List<String> bType = Arrays.asList("lw","sw");
        List<String> cType = Arrays.asList("beq");

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
            return bType(line);
        }
        if(encoding == 'c') {
            return cType(line);
        }
        return null;
    }
    public List<Integer> aType(String line){
        /* Take in a full line of instruction of I-type of the a-type encoding.
         * a-type is Rt,Rs,imm
         * Returns List<Integer>
         *     list.get(0) = op
         *     list.get(1) = Rt
         *     list.get(2) = Rs
         *     list.get(3) = imm
         */
        HashLookup hashLookup = new HashLookup();
        List<Integer> list = new ArrayList<>();
        //initialize list to NULL
        list.add(0,null);
        list.add(1,null);
        list.add(2,null);
        list.add(3,null);
        LinkedHashMap hashOp = hashLookup.makeOpHashMap();
        LinkedHashMap hashReg = hashLookup.makeRegHashMap();


        StringTokenizer st = new StringTokenizer(line," ,()[]$");

        //Op code into list
        String opString = st.nextToken();
        Object op = hashOp.get(opString);
        list.set(0, (Integer) op);

        String rtString = st.nextToken();
        char a = rtString.charAt(0);
        if(Character.isLetter(a)){
            Object rt = hashReg.get(rtString);
            list.set(2, (Integer) rt);
        }else{
            list.add(2,Integer.parseInt(rtString));
        }
        String rsString = st.nextToken();
        char b = rsString.charAt(0);
        if(Character.isLetter(b)){
            Object rs = hashReg.get(rsString);
            list.set(1, (Integer) rs);
        }else{
            list.set(1, Integer.parseInt(rsString));
        }

        String immString = st.nextToken();
        list.set(3, Integer.parseInt(immString));
        return list;
    }
    public List<Integer> bType(String line){
        /* Take in a full line of instruction of I-type of the b-type encoding.
         * b-type is Rt,offset(Rs)
         * Returns List<Integer>
         *     list.get(0) = op
         *     list.get(1) = Rs
         *     list.get(2) = Rt
         *     list.get(3) = imm
         */
        HashLookup hashLookup = new HashLookup();
        List<Integer> list = new ArrayList<>();
        //initialize list to NULL
        list.add(0,null);
        list.add(1,null);
        list.add(2,null);
        list.add(3,null);

        LinkedHashMap hashOp = hashLookup.makeOpHashMap();
        LinkedHashMap hashReg = hashLookup.makeRegHashMap();

        StringTokenizer st = new StringTokenizer(line," ,()[]$");

        //Op code into list
        String opString = st.nextToken();
        Object op = hashOp.get(opString);
        list.set(0, (Integer) op);

        String rtString = st.nextToken();
        char a = rtString.charAt(0);
        if(Character.isLetter(a)){
            Object rt = hashReg.get(rtString);
            list.set(2, (Integer) rt);
        }else{
            list.set(2, Integer.parseInt(rtString));
        }
        String immString = st.nextToken();
        list.set(3, Integer.parseInt(immString));

        String rsString = st.nextToken();
        char b = rsString.charAt(0);
        if(Character.isLetter(b)){
            Object rs = hashReg.get(rsString);
            list.set(1, (Integer) rs);
        }else{
            list.set(1, Integer.parseInt(rsString));
        }
        return list;
    }
    public List<Integer> cType(String line){
        /* Take in a full line of instruction of I-type of the ctype encoding.
         * c-type is Rs,Rt,offset
         * Returns List<Integer>
         *     list.get(0) = op
         *     list.get(1) = Rs
         *     list.get(2) = Rt
         *     list.get(3) = offset
         */
        HashLookup hashLookup = new HashLookup();
        List<Integer> list = new ArrayList<>();
        //initialize list to NULL
        list.add(0,null);
        list.add(1,null);
        list.add(2,null);
        list.add(3,null);
        LinkedHashMap hashOp = hashLookup.makeOpHashMap();
        LinkedHashMap hashReg = hashLookup.makeRegHashMap();
        StringTokenizer st = new StringTokenizer(line," ,()[]$");

        //Op code into list
        String opString = st.nextToken();
        Object op = hashOp.get(opString);
        list.set(0, (Integer)op);

        String rtString = st.nextToken();
        char a = rtString.charAt(0);
        if(Character.isLetter(a)){
            Object rt = hashReg.get(rtString);
            list.set(1, (Integer)rt);
        }else{
            list.set(1, Integer.parseInt(rtString));
        }


        String rsString = st.nextToken();
        char b = rsString.charAt(0);
        if(Character.isLetter(b)){
            Object rs = hashReg.get(rsString);
            list.set(2, (Integer)rs);
        }else{
            list.set(2, Integer.parseInt(rsString));
        }
        String immString = st.nextToken();
        list.set(3, Integer.parseInt(immString));
        return list;
    }

}
