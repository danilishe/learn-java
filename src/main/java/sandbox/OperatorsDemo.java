package sandbox;

public class OperatorsDemo {
    public static void main(String[] args){
        //region typ cast operators
        byte var1 = 0;
        int var2 = Byte.MAX_VALUE;
        var1 = (byte)var2;
        System.out.println(var1);
        //endregion

        //region NON primitives
        Object obj = new String("a");
        if(obj instanceof String) {
            System.out.println((String)obj);
        }

        //endregion

        //region arithmetics ops
        byte b1 = 1,b2 = 3;
        double v = b1 / (double) b2;
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(v);
        System.out.println("");
        //endregion

        //region bitwise ops
        int v1 = 2, v2 = 2, v3 = -1;
        System.out.println(v1);
        System.out.println(v1 & v2);
        System.out.println(v1 | v2);
        System.out.println(v1 ^ v2);
        System.out.println(v1 << 1);
        System.out.println(v2 >> 1);
        System.out.println(v3 >>> 1); //signed shift
        System.out.println(v3 << 1);

        //endregion
    }
}
