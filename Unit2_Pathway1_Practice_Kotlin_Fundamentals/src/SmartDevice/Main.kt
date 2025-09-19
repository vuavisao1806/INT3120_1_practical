package SmartDevice

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int,
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }

}

open class SmartDevice(val name: String, val category: String) {
    var deviceStatus = "on"
        protected set

    open var deviceType = "unknown"

    constructor(name: String, category: String, statusCode: Int) : this(name, category) {
        deviceStatus = when(statusCode) {
            0 -> "off"
            1 -> "on"
            else -> "unknown"
        }
    }

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(deviceName, deviceCategory) {

    override var deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume() {
        ++speakerVolume
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        ++channelNumber
        println("Channel number increased to $channelNumber.")
    }

    fun decreaseVolume() {
        --speakerVolume
        println("Speaker volume decreased to $speakerVolume.")
    }

    fun previousChannel() {
        --channelNumber
        println("Channel number decreased to $channelNumber.")
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off.")
    }

    override fun turnOn() {
        super.turnOn()
        println("$name is turned on. Speaker volume is set to " +
                "$speakerVolume and channel number is " + "set to $channelNumber.")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(deviceName, deviceCategory) {

    override var deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        ++brightnessLevel
        println("Brightness increased to $brightnessLevel.")
    }

    fun decreaseBrightness() {
        --brightnessLevel
        println("Brightness decreased to $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off.")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name  turned on. The brightness level is $brightnessLevel.")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice,
) {
    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        if (smartLightDevice.deviceStatus == "off") {
            ++deviceTurnOnCount
            smartTvDevice.turnOn()
        }
    }

    fun turnOffTv() {
        if (smartLightDevice.deviceStatus == "on") {
            --deviceTurnOnCount
            smartTvDevice.turnOff()
        }
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun decreaseTvVolume() {
        smartTvDevice.decreaseVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun changeTvChannelToPrevious() {
        smartTvDevice.previousChannel()
    }

    fun printSmartTvInfo() {
        smartTvDevice.printDeviceInfo()
    }

    fun turnOnLight() {
        if (smartLightDevice.deviceStatus == "off") {
            ++deviceTurnOnCount
            smartLightDevice.turnOn()
        }
    }

    fun turnOffLight() {
        if (smartLightDevice.deviceStatus == "on") {
            --deviceTurnOnCount
            smartLightDevice.turnOff()
        }
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun decreaseLightBrightness() {
        smartLightDevice.decreaseBrightness()
    }

    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

fun main() {
    val smartTvDevice : SmartTvDevice = SmartTvDevice("hihi", "haha")
    println(smartTvDevice.name)
    val smartLightDevice : SmartLightDevice = SmartLightDevice("haha", "hehe")
    smartLightDevice.turnOn()
    val smartHome = SmartHome(smartTvDevice, smartLightDevice)
    smartHome.turnOffAllDevices()

    smartHome.printSmartLightInfo()
    smartHome.printSmartTvInfo()

//    var smartDevice = SmartTvDevice("Android TV", "Entertainment")
//    smartDevice.turnOn()
//    smartDevice.printDeviceInfo()

//    smartDevice = SmartLightDevice("Google Light", "Utility")
//    smartDevice.turnOn()
}