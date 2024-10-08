import java.util.ArrayList;

public class mm {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(9);
        arrayList.add(2);
        arrayList.add(1);
        ArrayList<Integer> arrayListCopy = arrayList;

        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).equals(1)){
                arrayList.remove(i);
            }
        }

        for(Integer i : arrayList){
            System.out.println(i);
        }
    }

}
