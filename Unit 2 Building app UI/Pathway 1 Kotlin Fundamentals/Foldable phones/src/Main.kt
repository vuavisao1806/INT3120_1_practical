open class Phone(var isScreenLightOn: Boolean = false) {
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone : Phone() {
    private var isFolded = true

    override fun switchOn() {
        if (isFolded) {
            return
        }
        isScreenLightOn = true
    }

    fun changeFoldingState() {
        isFolded = !isFolded
        if (isFolded) {
            isScreenLightOn = false
        }
    }
}

fun main() {
    val foldablePhone = FoldablePhone()
    foldablePhone.checkPhoneScreenLight()
    // folded and try to switch on
    foldablePhone.switchOn()
    foldablePhone.checkPhoneScreenLight()

    // not folded and check (turn off)
    foldablePhone.changeFoldingState()
    foldablePhone.checkPhoneScreenLight()

    // not folded and check (turn on)
    foldablePhone.switchOn()
    foldablePhone.checkPhoneScreenLight()

    // not folded and check (turn off)
    foldablePhone.switchOff()
    foldablePhone.checkPhoneScreenLight()

    // folded when the screen is turned on
    foldablePhone.switchOn()
    foldablePhone.changeFoldingState()
    foldablePhone.checkPhoneScreenLight()
}