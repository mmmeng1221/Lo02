package classes;

/**
 * Created by zhangmeng on 02/12/2016.
 */
public class Factory {



    public Guide createGuide(){
        return new Guide("xxx",null,null,null,new Sacrifier(){

            @Override
            public void sacrifier(Parmas parmas) {

            }
        });

    }
}
