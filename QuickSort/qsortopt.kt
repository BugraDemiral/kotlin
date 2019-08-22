package kotlinMM.qsort
fun <E> partition(l: List<E>, f: (List<E>) -> Int): Triple<List<E>, E, List<E>> where E: Comparable<E> {
   val pivotIndex = f(l)
   val listMinusPivot = l.subList(0, pivotIndex) + l.subList(pivotIndex + 1, l.size)
   fun inner(list: List<E>, pivot: E, less: List<E>, more: List<E>): Triple<List<E>, E, List<E>> {
      return if(list.isEmpty()) Triple(less, pivot, more) else {
         if (list[0] < pivot)
            inner(list.drop(1), pivot, less + list[0], more)
         else
            inner(list.drop(1), pivot, less, more + list[0])
      }
   }
   return inner(listMinusPivot, l[pivotIndex], listOf(), listOf())
}
fun <E> qsort(list: List<E>, f: (List<E>) -> Int): List<E> where E: Comparable<E> { //--->(1)
   fun inner(l: List<E>, acc: List<E>): List<E> {
      return if(l.isEmpty()) acc else {
         val (left, pivot, right) = partition(l, f) //--->(2)
         if(left.size <= right.size)
            inner(right, acc + qsort(left, f) + pivot)
         else
            inner(left, acc + qsort(right, f) + pivot)
      }
   }
   return if (list.size < 2) list else inner(list, listOf())
}
fun main(args: Array<String>) {
   val list = listOf(4,5,6,9,1,3,4,0,3,9,2,6,3)
   val sorted = qsort(list, {0}) //--->(3)
   print("original list: $list\nsorted list: $sorted")
}