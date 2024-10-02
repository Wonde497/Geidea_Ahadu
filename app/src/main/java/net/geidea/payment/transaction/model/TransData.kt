package net.geidea.payment.transaction.model

import android.content.Context
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.annotation.Keep
import net.geidea.payment.DBHandler
import net.geidea.payment.tlv.HexUtil
import java.nio.ByteBuffer
import java.util.Date
import java.util.Locale

@Keep
class TransData(private val context:Context) {
      val sharedPreferences=context.getSharedPreferences("SHARED_DATA",Context.MODE_PRIVATE)

    var txnType=sharedPreferences.getString("TXN_TYPE","")

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
    class ResponseFields{
        companion object{

            var MTI=""
            var primaryBitmap=""
            var Field02=""
            var Field03=""
            var Field04=""
            var Field07=""
            var Field11=""
            var Field12=""
            var Field14=""
            var Field22=""
            var Field24="200"
            var Field25="00"
            var Field35=""
            var Field37=""
            var Field38=""
            var Field39=""
            var Field41=""
            var Field42=""
            var Field49=""
            var Field52=""
            var Field55=""

        }



    }
    fun assignValue2Fields(){
        if(txnType.equals("PURCHASE")){
       Log.d(TAG,"txn type"+txnType)

        }
        if(txnType.equals("REVERSAL")){
            RequestFields.MTI="0400"
            RequestFields.primaryBitmap="7230058028C08200"
            RequestFields.Field24="400"
        }

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
        val dbHandler=DBHandler(context)
        RequestFields.Field41=dbHandler.getTID()//"TID12345"
        Log.d(TAG,"terminalID:"+RequestFields.Field41)
        RequestFields.Field42=dbHandler.getMID()//"MID123456789012"
        Log.d(TAG,"merchantID:"+RequestFields.Field42)
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
    val responseMessage="30323130723000000E808000313939323331343134313539343130363330373536303030303030303030303030303030323030303232353133333033373030303134363136303232353037323834363030303133383834383631343834383631343030305030303030303032323330"
    fun unpackResponseFields(responseMessage: String) {
        ResponseFields.MTI = responseMessage.substring(0, 8)
        val MTI = hex2Asc(ResponseFields.MTI)
        println("mti string: $MTI")

        ResponseFields.primaryBitmap = responseMessage.substring(8, 24)
        println("primarybitmap : ${ResponseFields.primaryBitmap}")

        val responseBody = hex2Asc(responseMessage.substring(24))
        println("responsebody : $responseBody")

        val binaryBitmap1 = hex2Binary(ResponseFields.primaryBitmap)
        println("binarybitmap1 : $binaryBitmap1")

        assignValue2ResponseFields(binaryBitmap1, responseBody)
    }



    fun fillGapSequence(data: String, size: Int): String {
        var result = data
        while (result.length != size) {
            result = "0" + result
        }
        return result
    }

    fun assignValue2ResponseFields(binaryBitmap: String, responseBody: String) {
        var n = 0
        var prefix = ""
        val charArray = binaryBitmap.toCharArray()
        val strArrayBitmap = Array(charArray.size) { i -> charArray[i].toString() }

        println("binaryBitmap1 : ${strArrayBitmap[1]}")

        for (i in 0 until 64) {
            if (strArrayBitmap[i] == "1") {
                val j = i + 1
                println("bitmap available : $j")
                when (j) {
                    2 -> {
                        prefix = responseBody.substring(0, 2)
                        println("prefix: $prefix")
                        val num = prefix.toInt()
                        n += 2
                        ResponseFields.Field02 = responseBody.substring(n, n + num)
                        Log.d(TAG, "field02: ${ResponseFields.Field02}")
                        n += num
                    }
                    3 -> {
                        ResponseFields.Field03 = responseBody.substring(n, n + 6)
                        Log.d(TAG, "field03: ${ResponseFields.Field03}")
                        n += 6
                    }
                    4 -> {
                        ResponseFields.Field04 = responseBody.substring(n, n + 12)
                        Log.d(TAG, "field04: ${ResponseFields.Field04}")
                        n += 12
                    }
                    7 -> {
                        ResponseFields.Field07 = responseBody.substring(n, n + 10)
                        Log.d(TAG, "field07: ${ResponseFields.Field07}")
                        n += 10
                    }
                    11 -> {
                        ResponseFields.Field11 = responseBody.substring(n, n + 6)
                        Log.d(TAG, "field11: ${ResponseFields.Field11}")
                        n += 6
                    }
                    12 -> {
                        ResponseFields.Field12 = responseBody.substring(n, n + 12)
                        Log.d(TAG, "field12: ${ResponseFields.Field12}")
                        n += 12
                    }
                    37 -> {
                        ResponseFields.Field37 = responseBody.substring(n, n + 12)
                        Log.d(TAG, "field37: ${ResponseFields.Field37}")
                        n += 12
                    }
                    38 -> {
                        ResponseFields.Field38 = responseBody.substring(n, n + 6)
                        Log.d(TAG, "field38: ${ResponseFields.Field38}")
                        n += 6
                    }
                    39 -> {
                        ResponseFields.Field39 = responseBody.substring(n, n + 3)
                        Log.d(TAG, "field39: ${ResponseFields.Field39}")
                        n += 3
                    }
                    41 -> {
                        ResponseFields.Field41 = responseBody.substring(n, n + 8)
                        Log.d(TAG, "field41: ${ResponseFields.Field41}")
                        n += 8
                    }
                    49 -> {
                        ResponseFields.Field49 = responseBody.substring(n, n + 3)
                        Log.d(TAG, "field49: ${ResponseFields.Field49}")
                        n += 3
                    }
                    55 -> {
                        prefix = responseBody.substring(n, n + 3)
                        println("prefix: $prefix")
                        val num = prefix.toInt()
                        n += 3
                        ResponseFields.Field55 = responseBody.substring(n, n + num)
                        Log.d(TAG, "field55: ${ResponseFields.Field55}")
                    }
                }
            }
        }
    }
    fun hex2Binary(hexString: String): String {
        val binary = StringBuilder()
        for (i in hexString.indices) {
            val hexChar = hexString[i]
            val binaryString = Integer.toBinaryString(Integer.parseInt(hexChar.toString(), 16))
            binary.append(String.format("%4s", binaryString).replace(' ', '0'))
        }
        return binary.toString()
    }

    fun hex2Asc(hexString: String): String {
        val output = StringBuilder()
        for (i in hexString.indices step 2) {
            val hex = hexString.substring(i, i + 2)
            output.append(hex.toInt(16).toChar())
        }
        return output.toString()
    }



}