package net.geidea.payment.transaction.model

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.annotation.Keep
import net.geidea.payment.tlv.HexUtil
import java.nio.ByteBuffer
import java.util.Date
import java.util.Locale

@Keep
class TransData {
    var entryMode:String = ""
    var cardLabelNameEng = ""
    var amount:Long = 0L
    var pinBlock = ""
    var transactionReqDateTime = ""
    var aid = ""
    var rrn = ""
    var stan = ""
    var transactionStatus = false
    var transactionType = ""
    var genACResult = ""
    var pan = ""
    var applicationCryptogram = ""
    var cardExpiryDate = ""
    var verificationMethod = 0
    val TAG="TransData"
    var track2=""

    override fun toString(): String {
        return "TransData(" +
                "entryMode='$entryMode', " +
                "cardLabelNameEng='$cardLabelNameEng', " +
                "amount=$amount, " +
                "pinBlock='$pinBlock', " +
                "transactionReqDateTime='$transactionReqDateTime', " +
                "aid='$aid', " +
                "rrn='$rrn', " +
                "stan='$stan', " +
                "transactionStatus=$transactionStatus, " +
                "transactionType='$transactionType', " +
                "genACResult='$genACResult', " +
                "pan='$pan', " +
                "applicationCryptogram='$applicationCryptogram', " +
                "cardExpiryDate='$cardExpiryDate', " +
                "verificationMethod=$verificationMethod" +
                ")"
    }
    class RequestFields{
        companion object{

            var MTI="0200"
            var primaryBitmap=""
            var Field02=""
            var Field03="000000"
            var Field04=""
            var Field07=""
            var Field11=""
            var Field12=""
            var Field14=""
            var Field22="051"
            var Field24="200"
            var Field25="00"
            var Field35=""
            var Field41=""
            var Field42=""
            var Field49="230"
            var Field52=""
            var Field55=""

        }



    }
    fun assignValue2Fields(){

        RequestFields.primaryBitmap="7234058020C09200"//bitmap for online pin
        RequestFields.Field02=pan


        RequestFields.Field07= SimpleDateFormat("MMddhhmmss", Locale.getDefault()).format(Date())
        RequestFields.Field11=stan
        Log.d(TAG,"stan:"+RequestFields.Field11)
        RequestFields.Field12=SimpleDateFormat("yyMMddhhmmss", Locale.getDefault()).format( Date())
        RequestFields.Field14=cardExpiryDate+"01"
        Log.d(TAG,"Field14:"+RequestFields.Field14)

        if(RequestFields.Field35.endsWith("F")){
            RequestFields.Field35=RequestFields.Field35.substringBefore("F")


        }
        Log.d(TAG,"Field35:"+RequestFields.Field35)
        RequestFields.Field41="TID12345"
        RequestFields.Field42="MID123456789012"

        RequestFields.Field52=pinBlock
        Log.d(TAG,"Field52:"+RequestFields.Field52)

    }
    fun packRequestFields():ByteArray {
        val bitmaplength = RequestFields.primaryBitmap.length / 2
        Log.d("TransData", "bitmaptry.........: ${RequestFields.primaryBitmap}")

        val primarybitmap = Array(bitmaplength) { "" }
        val charArray = RequestFields.primaryBitmap.toCharArray()

        var j = 0
        for (i in 0 until bitmaplength * 2 step 2) {
            primarybitmap[j] = "${charArray[i]}${charArray[i + 1]}"
            j++
        }

        val mti = RequestFields.MTI.toByteArray()

        val listOfByteArrays1 = mutableListOf<ByteArray>()
        for (r in primarybitmap.indices) {
            listOfByteArrays1.add(HexUtil.hexStr2Byte(primarybitmap[r]))
        }
        Log.d(TAG, "list of byte arrays1: $listOfByteArrays1")

        var lengthOfBitmap = 0
        for (byteArray1 in listOfByteArrays1) {
            lengthOfBitmap += byteArray1.size
        }
        //field 02 and its length
        val lengthOfField02 = RequestFields.Field02.length.toString().toByteArray()
        val field02 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field02))
        val field03 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field03))
        val field04 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field04))
        val field07 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field07))
        val field11 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field11))
        val field12 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field12))
        val field14 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field14))
        val field22 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field22))
        val field24 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field24))
        val field25 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field25))
        val lengthOfField35 = RequestFields.Field35.length.toString().toByteArray()
        val field35 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field35))
        val field41 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field41))
        val field42 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field42))
        val field49 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field49))
        //byte buffer variable
        val buffer = ByteBuffer.allocate(
            mti.size +
                    lengthOfBitmap +
                    lengthOfField02.size +
                    field02.size +
                    field03.size +
                    field04.size +
                    field07.size +
                    field11.size +
                    field12.size +
                    field14.size +
                    field22.size +
                    field24.size +
                    field25.size +
                    lengthOfField35.size +
                    field35.size +
                    field41.size +
                    field42.size +
                    field49.size
        )


        buffer.apply {
            put(mti)
            //for loop to put the bit map in buffer
            for (byteArray1 in listOfByteArrays1) {
                put(byteArray1)
            }

            put(lengthOfField02)
            put(field02)
            put(field03)
            put(field04)
            put(field07)
            put(field11)
            put(field12)
            put(field14)
            put(field22)
            put(field24)
            put(field25)
            put(lengthOfField35)
            put(field35)
            put(field41)
            put(field42)
            put(field49)
        }
        //pinblock for online pin
        val lengthOfField52 = RequestFields.Field52.length.toString().toByteArray()

        val field52 = HexUtil.hexStr2Byte(HexUtil.asc2Hex(RequestFields.Field52))
        val bufferForF52 = ByteBuffer.allocate(lengthOfField52.size + field52.size)
        bufferForF52.apply {
            put(lengthOfField52)
            put(field52)
        }


        //Field 55

        val field55Length = RequestFields.Field55.length / 2
        Log.d(TAG, "fld55try.........:" + RequestFields.Field55)
        val field55 = Array(field55Length) { "" }
        val charArray1 = RequestFields.Field55.toCharArray()
        var m = 0

        for (l in 0 until field55Length * 2 step 2) {
            field55[m] = charArray1[l].toString() + charArray1[l + 1]
            m++
        }

        val listOfByteArrays = mutableListOf<ByteArray>()
        for (c in field55.indices) {
            listOfByteArrays.add(HexUtil.hexStr2Byte(field55[c]))
        }
        Log.d(TAG, "list of byte arrays$listOfByteArrays")

        var l55 = 0
        for (byteArray in listOfByteArrays) {
            l55 += byteArray.size
        }

        val len_byte = l55.toString().toByteArray()
        //Log.d(TAG, "len55:" + Utility.byte2HexStr(len_byte))

        val byteBufferF55 = ByteBuffer.allocate(len_byte.size + l55)
        byteBufferF55.apply {
            put(len_byte)
            for (byteArray in listOfByteArrays) {
                put(byteArray)
            }
        }


        val bufferF55 = byteBufferF55.array()
        lateinit var combinedBuffer: ByteBuffer
        val field2to49: ByteArray = buffer.array()
        Log.d(TAG, "Sent field2to49....:" + HexUtil.toHexString(field2to49))
        val fld52: ByteArray = bufferForF52.array()
        Log.d(TAG, "Sent fld52....:" + HexUtil.toHexString(fld52))

        //if (true) {
        //combinedBuffer = ByteBuffer.allocate(field2to49.size + fld52.size+bufferF55.size)
        //combinedBuffer.apply {
        // put(field2to49)
        //  put(fld52)
        //  put(bufferF55)

        // }

        // }else {
        combinedBuffer = ByteBuffer.allocate(field2to49.size + bufferF55.size)
        combinedBuffer.apply {
            put(field2to49)
            put(bufferF55)
        }
        //}
        val result: ByteArray = combinedBuffer.array()
        Log.d(TAG, "Sent packet....:" + HexUtil.toHexString(result))

        return result
    }


    fun fillGapSequence(data: String, size: Int): String {
        var result = data
        while (result.length != size) {
            result = "0" + result
        }
        return result
    }

}