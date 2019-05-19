import java.io.File

fun main(args: Array<String>) {
    if (args.size != 1) {
        System.err.println("Usage: java -jar renamer.jar <directory>")
        return
    }
    val root = File(args[0])
    if (!root.isDirectory) {
        System.err.println("${args[0]} is not a directory")
        return
    }
    root.walkTopDown().onFail { _, ex ->
        System.err.println(ex.message)
    }.forEach {
        if (it.isFile) {
            val name = it.name
            if (name.endsWith(".kt") || name.endsWith(".java")) {
                val path = it.path
                if (it.renameTo(File("$path.2019")))
                    println(path)
                else
                    System.err.println("$path: Failed to rename")
            }
        }
    }
}