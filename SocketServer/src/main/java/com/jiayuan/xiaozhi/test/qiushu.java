package xiaozhi.test;

/**
 * Created by xiaoxian on 15/12/11.
 */
public class qiushu {
    public static void main(String[] args){

        for (int i = 8;;i++){
            if(i%2 == 1 && i%3 ==1 && i%4== 1
                    && i%5==1 && i%6 ==1 && i%7 ==1){
                System.out.println(i);
                break;
            }

        }
    }
}
