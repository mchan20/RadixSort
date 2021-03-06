public class Radix {
  public static int nth(int n, int col) {
    n = Math.abs(n);
    return ( n / (int) Math.pow(10,col) ) % 10;
  }

  public static int length(int n) {
    String result = ""+Math.abs(n);
    return result.length();
  }

  public static void merge( SortableLinkedList original, SortableLinkedList[]buckets) {
    for(SortableLinkedList m : buckets) {
      if (m != null) original.extend(m);
    }
  }

  public static void radixSortSimple(SortableLinkedList data) {
    int lala = data.get(0);
    SortableLinkedList[] buckets = new SortableLinkedList[10];
    for (int i=0;i<10;i++) {
      buckets[i] = new SortableLinkedList();
    }
    SortableLinkedList temp = new SortableLinkedList();
    int maxCol = length(data.get(0));
    for (int i=0;i<maxCol;i++) {
      // for (int j=0;j<data.size();j++) {
      //   lala = data.get(j);
      //    buckets[nth(lala,i)].add(lala);
      //   if (length(lala) > maxCol) maxCol = length(lala);
      // }
      // clear(data);
      while (data.size() > 0) {
        lala = data.get(0);
        buckets[nth(lala,i)].add(lala);
        if (length(lala) > maxCol) maxCol = length(lala);
        data.remove(0);
      }
      merge(data, buckets);
    }
  }

  public static void radixSort(SortableLinkedList data) {
    int lala = 0;
    int thing = 0;
    SortableLinkedList negative = new SortableLinkedList();
    SortableLinkedList positive = new SortableLinkedList();
    // for(int i=0;i<data.size();i++) {
    //   lala = data.get(i);
    //   if (lala < 0) negative.add(lala*-1);
    //   else positive.add(lala);
    // }
    while (data.size() > 0) {
      lala = data.get(0);
      if (lala < 0) negative.add(lala*-1);
      else positive.add(lala);
      data.remove(0);
    }
    if (negative.size() > 0) {
      radixSortSimple(negative);
      // for(int i=negative.size()-1;i>=0;i--) {
      //   data.add(negative.get(i)*-1);
      // }
      while (negative.size() > 0) {
        lala = data.get(negative.size()-1);
        data.add(lala*-1);
        negative.remove(negative.size()-1);
      }
      if (positive.size() > 0) {
        radixSortSimple(positive);
        data.extend(positive);
      }
    }
    else {
      radixSortSimple(positive);
      data.extend(positive);
    }
  }

  public static void clear(SortableLinkedList list) {
    SortableLinkedList temp = new SortableLinkedList();
    temp.extend(list);
  }


  // public static void main(String[] args) {
  //   SortableLinkedList hi = new SortableLinkedList();
  //   // hi.add(0);
  //   hi.add(-271);
  //   hi.add(-701);
  //   hi.add(-72);
  //   hi.add(-74);
  //   hi.add(-111);
  //   hi.add(-91);
  //   System.out.println(hi.toString());
  //   radixSort(hi);
  //   System.out.println(hi.toString());
  // }
}
