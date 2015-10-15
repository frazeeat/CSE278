import java.util.LinkedHashMap;

/**
 * Created by Adam on 10/14/2015.
 */
public class HashLookup {
    public LinkedHashMap makeOpHashMap(){
        LinkedHashMap<String,Integer> hash = new LinkedHashMap<>();
        hash.put("add", 0);
        hash.put("sub",0);
        hash.put("slt",0);
        hash.put("and",0);
        hash.put("or",0);
        hash.put("addi",8);
        hash.put("slti",10);
        hash.put("andi",13);
        hash.put("ori",14);
        hash.put("lw",35);
        hash.put("sw",43);
        hash.put("beq",4);
        hash.put("jal",3);
        hash.put("j",2);
        hash.put("jr",0);
        hash.put("sll",0);
        hash.put("slr",0);
        return hash;
    }
    public LinkedHashMap makeFunctHashMap(){
        LinkedHashMap<String,Integer> hash = new LinkedHashMap<>();
        hash.put("add",32);
        hash.put("sub",34);
        hash.put("slt",42);
        hash.put("and",36);
        hash.put("or",37);
        hash.put("addi",0);
        hash.put("slti",0);
        hash.put("andi",0);
        hash.put("ori",0);
        hash.put("lw",0);
        hash.put("sw",0);
        hash.put("beq",0);
        hash.put("jal",0);
        hash.put("j",0);
        hash.put("jr",8);
        hash.put("sll",0);
        hash.put("slr",2);
        return hash;
    }
    public LinkedHashMap makeRegHashMap(){
        LinkedHashMap<String,Integer> hash = new LinkedHashMap<>();
        hash.put("zero",0);
        hash.put("at",1);
        hash.put("v0",2);
        hash.put("v1",3);
        hash.put("a0",4);
        hash.put("a1",5);
        hash.put("a2",6);
        hash.put("a3",7);
        hash.put("t0",8);
        hash.put("t1",9);
        hash.put("t2",10);
        hash.put("t3",11);
        hash.put("t4",12);
        hash.put("t5",13);
        hash.put("t6",14);
        hash.put("t7",15);
        hash.put("s0",16);
        hash.put("s1",17);
        hash.put("s2",18);
        hash.put("s3",19);
        hash.put("s4",20);
        hash.put("s5",21);
        hash.put("s6",22);
        hash.put("s7",23);
        hash.put("t8",24);
        hash.put("t9",25);
        hash.put("k0",26);
        hash.put("k1",27);
        hash.put("gp",28);
        hash.put("sp",29);
        hash.put("s8",30);
        hash.put("ra",31);
        return hash;
    }
}
