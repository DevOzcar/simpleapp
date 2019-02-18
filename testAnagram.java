public class Anagram{

  String[] words={"cine","cafe","nice","roma","pepsi","cien","face","omar","inec","sipep","notorious","basura","sinsentido","cinefilo","filo","lifo"};

  List<String> lstCopy;
  List<String> lstSameSize;
/*
    Output
    cine - nice - cien
    cafe - face
    roma - amor - ramo

*/

   public Anagram(){

     lstCopy = new ArrayList<>(Arrays.asList(words));
     lstSameSize = new ArrayList<String>();

     for(int i = 0; i < lstCopy.size(); i++){

       lstSameSize = getElementsBySize(lstCopy.get(i));

        if(lstSameSize.size() > 1){
          printAnagram(lstSameSize);
          i = 0;
        }

       lstSameSize.clear();
     }

   }

  boolean compareLength(String value, String next){
    return value.length() == next.length();
  }


  public  List<String> getElementsBySize(String value){
    List<String> tmp = new ArrayList<String>();
    tmp.add(value);

    for (int i = 1; i < lstCopy.size(); i++ ){

      if(compareLength(value, lstCopy.get(i))){
        if (!value.equals(lstCopy.get(i)) && getWordToAddGroup(value, lstCopy.get(i))){
          tmp.add(lstCopy.get(i));
          lstCopy.remove(i);
        }
      }
    }

    return tmp;
  }

  void printAnagram(List<String> gp){
    StringBuffer s = new StringBuffer();

    for(String word : gp){
      s.append(word);
      s.append(" - ");
    }

    System.out.println(s);
  }


   boolean getWordToAddGroup(String base, String value){
     char[] gp1 = base.toCharArray();
     char[] gp2 = value.toCharArray();

     Arrays.sort(gp1);
     Arrays.sort(gp2);

     return Arrays.equals(gp1, gp2);
   }

}
