package xiaozhi.test;

import java.util.Map;
import java.util.Properties;

/**
 * Created by xiaoxian on 15/11/30.
 */
public class GetEnv {
    public static void main(String[] args){
        Properties properties = System.getProperties();
        for(Map.Entry<Object,Object> entry:properties.entrySet()){
            System.out.println(entry.getKey()+"*****:******"+entry.getValue());
        }
    }
}
