class Test{
    public static void main(String args[]){

        Arete pute1 = new Arete(1,2);
        Arete pute2 = new Arete(2,1);
        Arete pute3 = new Arete(0,2);

        Arete pute11 = new Arete(pute1);

        System.out.println(pute1);
        System.out.println(pute2.equals(pute1));
    }
}