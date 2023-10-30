import java.util.*
fun main() {
    val str : String = readlnOrNull().toString() //ввод строки с консоли

    var mas_str : MutableList<String> = str.split(" ").toMutableList() //разделение строки на слова
    mas_str.removeAll(listOf("",null)) //удаление всех пустых слов

    val reg = Regex("a..")
    mas_str.removeAll { !it.matches(reg) } //удаление всех слов неудовлетворяющих регулярному выражению

    if(mas_str.isEmpty()) {
        print("Слов на 'а' больше 3 символов не найдено.")
    }
    else {
        print(mas_str)
    }
}