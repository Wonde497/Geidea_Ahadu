package net.geidea.payment.tlv

class BerTag {
    @JvmField
    val bytes: ByteArray

    constructor(aBuf: String) : this(HexUtil.parseHex(aBuf)) {}
    constructor(aBuf: ByteArray) {
        val temp = ByteArray(aBuf.size)
        System.arraycopy(aBuf, 0, temp, 0, aBuf.size)
        bytes = temp
    }

    constructor(aBuf: ByteArray?, aOffset: Int, aLength: Int) {
        val temp = ByteArray(aLength)
        System.arraycopy(aBuf, aOffset, temp, 0, aLength)
        bytes = temp
    }

    constructor(aFirstByte: Int, aSecondByte: Int) {
        bytes = byteArrayOf(aFirstByte.toByte(), aSecondByte.toByte())
    }

    constructor(aFirstByte: Int, aSecondByte: Int, aFirth: Int) {
        bytes = byteArrayOf(aFirstByte.toByte(), aSecondByte.toByte(), aFirth.toByte())
    }

    constructor(aFirstByte: Int) {
        bytes = byteArrayOf(aFirstByte.toByte())
    }

    val isConstructed: Boolean
        get() = bytes[0].toInt() and 0x20 != 0
    val berTagHex: String
        get() = HexUtil.toHexString(bytes, 0, bytes.size)

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val berTag = o as BerTag
        return bytes.contentEquals(berTag.bytes)
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }

    override fun toString(): String {
        return (if (isConstructed) "+ " else "- ") + HexUtil.toHexString(bytes, 0, bytes.size)
    }
}