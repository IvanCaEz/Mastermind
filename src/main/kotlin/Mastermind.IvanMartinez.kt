/**
* @author: Iván Martínez Cañero
* @version: 1.0 - 2022/11/15
 */
import java.util.*

/**
 * These constants are used to color the foreground and background of each character when needed
 * Write the name or names of the constants you want to use before the next word or character and then
 * use the constant "reset" after the desired word or character to end the formatting
 */
const val reset = "\u001b[0m"
const val box = "\u001b[51m"
const val bold = "\u001b[1m"
const val underline = "\u001b[21m"
const val black = "\u001b[38;5;0m"
const val red = "\u001b[31m"
const val cyan = "\u001b[38;5;87m"
const val green = "\u001b[38;5;10m"
const val yellow = "\u001b[38;5;11m"
const val gray = "\u001b[38;5;7m"
const val pink = "\u001b[38;5;207m"
const val purple = "\u001b[38;5;99m"
const val blue = "\u001b[38;5;69m"


/**
 * This function will explain the user the instructions of the game
 * - Will ask the user to type "START" to begin the game
 */
fun instructions(){
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    do {
        println("$yellow$bold✩°｡⋆ $purple$bold Benvingut / Benvinguda! $pink(*•ᴗ•*)ノﾞ  $yellow$bold⋆｡°✩$reset  ")
        println("A continuació t'explicaré les ${underline}instruccions:$reset  ")
        println("- Tens $underline${bold}6 intents$reset per endevinar el $underline${bold}ordre$reset i $underline${bold}color$reset correcte de la seqüència ")
        println("""- Es generarà una seqüència de 4 colors no repetits
        |- Els colors disponibles son el $box$red$bold Vermell $reset, el $box$green$bold Verd $reset, el $box$purple$bold Lila $reset, el $box$cyan$bold Cyan $reset, i el $box$pink$bold Rosa $reset
        |- Tens $underline${bold}6 intents$reset per endevinar el ordre i color correcte de la seqüència 
        |- Si el cercle es possa $box$gray$bold gris $reset vol dir que no existeix en la seqüència
        |- Si es possa $box$bold blanca $reset vol dir que existeix però no està a la posició correcta
        |- Si es possa $box$black$bold negra $reset vol dir que està a la posició correcta
    """.trimMargin())
        println("Per començar a jugar entra $yellow$bold✩°｡⋆$bold$box$purple START $reset$yellow$bold⋆｡°✩$reset")
        val start = scanner.nextLine().uppercase()
    } while (start != "START")
}

/**
 * The main fuction will call in order other functions
 */

fun main(){
    instructions()
    codi()
}

/**
 * The codi function is where the logic of the application lies.
 * - First will take a random color from the colorPool and will check if is present on the colorRandomList
 * - If the color isn't present, it will be added to the colorRandomList
 * - The program will repeat that until the size of the list is 4
 * - Then will ask the user for a color, if the color isn't one of the 5 availables or is repeated,
 * will return an error
 *
 * ### When the user typed 4 colors:
 *
 * - It will scan every color the user entered and will compare with the order of the randomized sequence
 * - If the color is in the right position, will add a black circle to a history variable.
 * - If isn't in the right position will add a white one.
 * - If doesn't exist, will add a gray one.
 * - Then it will be added to a list called "historyList" and with each iteration will print the content
 * of the list creating the history of the game
 * - The program will also print the number of the current round.
 * - Last it will rest 1 try
 *
 * ### When is the correct sequence:
 *
 * - Will print a congratulation message with the correct sequence
 *
 * ### When the user spends all their tries
 *
 * - Will end the programm and the game with a lose.
 *
 * ### After the game
 *
 * - If the tries reach 0 or the user guesses the sequence it will ask if the user wants to continue playing,
 * read the rules or stop playing.
 */
fun codi() {
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    val colorPool = arrayOf("vermell","verd","lila","cyan","rosa")
    var intents = 6
    var ronda = 1
    val colorRandomList: MutableList<String> = mutableListOf()
    val historyList: MutableList<MutableList<String>> = mutableListOf()
    while (colorRandomList.size != 4){ // genera una llista de 4 colors no repetits
        for (randomColor in 0..3) {
            val random = colorPool.random().uppercase()
            if (random !in colorRandomList) {
                colorRandomList.add(random)
            }
        }
    }
    println(colorRandomList)
    do {
        var userGuess: String
        val colorUserList: MutableList<String> = mutableListOf()
        do {
            var colorRepetit = false
            println("Entra el teu color")
            userGuess = scanner.nextLine().uppercase()
            if (userGuess !in colorUserList && ((userGuess == "VERMELL") || (userGuess == "LILA") ||
                (userGuess == "CYAN") || (userGuess == "ROSA") || (userGuess == "VERD"))) {
                colorUserList.add(userGuess)
            }
            else if (userGuess in colorUserList){
                colorRepetit = true
            }
            if (colorRepetit){
                println("$underline$red${bold}No$reset pots repetir color")
            }
            if ((userGuess != "VERMELL") && (userGuess != "LILA") && (userGuess !=  "CYAN") &&
                (userGuess != "ROSA") && (userGuess != "VERD")){
                println("Entra un color valid")
            }
        } while (colorUserList.size != 4)
        var correctPositionCounter = 0
        val blackGrayWhite: MutableList<String> = mutableListOf()
        val userColors: MutableList<String> = mutableListOf()

        for (userColor in 0..colorUserList.lastIndex){
            if (colorUserList[userColor] !in colorRandomList){
                blackGrayWhite += " $gray◉$reset"
            }
            if (colorUserList[userColor] in colorRandomList &&
                colorUserList[userColor] != colorRandomList[userColor] ){
                blackGrayWhite += " ◉"
            }
            if (colorUserList[userColor] in colorRandomList &&
                colorUserList[userColor] == colorRandomList[userColor] ){
                blackGrayWhite += " $black◉$reset"
                correctPositionCounter++
            }
        }
        for (userColor in 0..colorUserList.lastIndex) {
            when (colorUserList[userColor]) {
                "VERMELL" ->  userColors += " $red◉$reset"
                "CYAN" ->  userColors += " $cyan◉$reset"
                "LILA" ->  userColors += " $purple◉$reset"
                "ROSA" ->  userColors += " $pink◉$reset"
                "VERD" ->  userColors += " $green◉$reset"
            }
        }
        historyList.add(blackGrayWhite)
        historyList.add(userColors)
        println("    Ronda $cyan$bold$box $ronda $reset")
        for (position in 0..historyList.lastIndex){
            if (position == 0){
                println("╔════════════════╗")
            }
            print("║")
            print(" ${(historyList[position]).joinToString().replace(",", "")}")
            println("  ║")
            if (position == historyList.lastIndex){
                print("╚════════════════╝")
            }
        }
        intents--
        if (intents==0) {
            println("\n $box Ja no et queden intents :( $reset")
            println("""- Si vols tornar a jugar entra $pink$bold$box AGAIN $reset
                |
                |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                |
                |- Si vols revisar les instruccions entra $blue$bold$box HELP $reset""".trimMargin())
            val again = scanner.nextLine()
            if (again.uppercase() == "AGAIN"){
                codi()
            }
            if (again.uppercase() == "HELP") {
                main()
            }
            if (again.uppercase() == "EXIT") {
                println("Fins un altre $pink$bold(~‾▿‾)~$reset")
            }
        }
        if (correctPositionCounter !=4 ){
            println("\nEt queden $cyan$bold$box $intents $reset intents")
        }
        if (correctPositionCounter ==4 ){
            println("\n $yellow$bold✩°｡⋆$green$bold FELICITATS!! $yellow$bold⋆｡°✩$reset")
            println("""- Si vols tornar a jugar entra $pink$bold$box AGAIN $reset
                |
                |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                |
                |- Si vols revisar les instruccions entra $blue$bold$box HELP $reset""".trimMargin())
            val again = scanner.nextLine()
            if (again.uppercase() == "AGAIN"){
                codi()
            }
            if (again.uppercase() == "HELP") {
                main()
            }
            if (again.uppercase() == "EXIT") {
                println("Fins un altre $pink$bold(~‾▿‾)~$reset")
            }
        }
        ronda++

    } while ( intents != 0 && correctPositionCounter != 4)
}
// A l’esquerra apareixerà un codi de color blanc o negre on:
//Negre: Representa que la posició i el color són correctes.
//Blanc: El color existeix però no en la posició adient.