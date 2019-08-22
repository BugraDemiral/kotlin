package kotlinMM.fileSystemTree
import kotlinMM.optionTypes.*
import java.io.File
fun <E> traverse(path: String, init: () -> E, f: (File) -> E, append: (E, E) -> E): E {
   fun inner(p: String, acc: E) : E {
      return if (! File(p).isDirectory) acc else {
         File(p).listFiles().fold(acc, {e, file -> inner(file.path, append(e, f(file)))})
      }
   }
   return inner(path, init())
}
fun main(args: Array<String>) {
   fun ppFileName(path: String, f: File): String {
      fun pathLength(path: String): Int = File(path).parent.filter {it != File.separatorChar}.length
      val diff = pathLength(f.path) - pathLength(path)
      return  " ".repeat(diff) + "|\n" +
              " ".repeat(diff) + "|--" + f.name + "\n" +
              " ".repeat(diff) + "|\n"
   }
   val path = "/Users/bugra/article/samples"
   assert(traverse(path, {0}, {file -> if(file.isFile) 1 else 0}, {x, y -> x + y}) == 20) //--->(1)
   println("|--" + File(path).name + "\n" + traverse(path, {""}, { ppFileName(path, it)}, { s1, s2 -> s1 + s2})) //--->(2)
}