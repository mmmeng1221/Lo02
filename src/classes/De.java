package classes;

/**
 * Created by zhangmeng on 05/12/2016.
 */
public class De {
    private int num;
    private static De de = new De();
    private De(){};
    public static De getDe(){return de;}

    public int lancer(){
       double num = Math.random();
        if(num<0.17)
        {return 1;}
        else if(num>=0.17 && num<0.33)
        {return 2;}
        else if(num>=0.33 && num<0.5)
        {return 3;}
        else if(num>=0.5 && num<0.67)
        {return 4;}
        else if(num>=0.67 && num<0.83)
        {return 5;}
        else
        {return 6;}
}
}
