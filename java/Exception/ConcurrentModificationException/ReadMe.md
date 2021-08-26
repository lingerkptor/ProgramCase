

 - List.subList(fromIndex,toIndex);
   * 這個方法是這個Source List的鏡像，如果移除其中的元素，就會影響原來的sourceList；
 同理，如果sourceList被修改也會影響subList．（多執行敘時要特別注意）
 ConcurrentModificationException會發生在迴圈、地迴、多執行緒環境下修改集合．
   1. Case 1 <br/> 
      <t>修改來源的List後，subList發生的ConcurrentModificationException
   2. Case 2 <br/>
      <t>修改subList內的元素，origin List迴圈發生的ConcurrentModificationException
   3. Case 3 <br/>
   <t> 迴圈內修改list導致發生的ConcurrentModificationException
   4. 