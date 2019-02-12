public class Main {

    public static void main(String[] args) {
        boolean isCondition1=true;
        boolean isCondition2=true;
        boolean isCondition3=false;
        boolean isCondition4=isCondition1&isCondition2&&isCondition3;

        System.out.println("isCondition3="+isCondition3);
    }
}
