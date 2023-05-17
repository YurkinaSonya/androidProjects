package com.example.mobilemodule

import android.app.Dialog
import android.content.ClipData
import android.content.ClipDescription
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import com.example.mobilemodule.databinding.ActivityMainBinding


//i change it and you can see it
//and i change it again
//and noooow
//test

//i write sth on virtual machine
/*
class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("You did it!")
                .setMessage("wow!")
                .setPositiveButton("ОК") {
                        dialog, id ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var listIsOpen = false
        val viewBlocks = binding.viewForBlocks
        val listBlocks = binding.listOfBlocks
        viewBlocks.setOnClickListener() {
            /*
            val myDialogFragment = MyDialogFragment()
            val manager = supportFragmentManager
            //myDialogFragment.show(manager, "wtf")
            val transaction: FragmentTransaction = manager.beginTransaction()
            myDialogFragment.show(transaction, "dialog")
             */
            val params = viewBlocks.layoutParams
            val paramsList = listBlocks.layoutParams
            if (!listIsOpen) {
                params.width = 1000
                paramsList.width = ViewGroup.LayoutParams.WRAP_CONTENT
                println("yep")
            } else {
                params.width = 300
                paramsList.width = 0
                println("nope")
            }
            viewBlocks.setLayoutParams(params);
            listBlocks.setLayoutParams(paramsList);
            listIsOpen = !listIsOpen
        }
        var bufIsOpen = false
        val bufBlocks = binding.buf
        val listBuf = binding.listForBuf
        bufBlocks.setOnClickListener() {
            val params = bufBlocks.layoutParams
            val paramsList = listBuf.layoutParams
            if (!bufIsOpen) {
                params.width = 1000
                paramsList.width = ViewGroup.LayoutParams.WRAP_CONTENT
                println("yep")
            } else {
                params.width = 300
                paramsList.width = 0
                println("nope")
            }
            bufBlocks.setLayoutParams(params);
            listBuf.setLayoutParams(paramsList);
            bufIsOpen = !bufIsOpen
        }


        val plt = binding.linLayoutPlt
        val list = binding.linLayoutList
        /*
        for (i in 1..20) {
            var idB = "block" + i.toString()
            val block = binding.(idB)
        }

         */

        val block1 = binding.block1
        block1.setOnLongClickListener() {
            println("YEEEE")
            val checkText = "Yepppp"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val block2 = binding.block2
        block2.setOnLongClickListener() {
            println("YEEEE")
            val checkText = "Yepppp"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val block3 = binding.block3
        block3.setOnLongClickListener() {
            println("YEEEE")
            val checkText = "Yepppp"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }

        list.setOnDragListener(dragListener)
        plt.setOnDragListener(dragListener)

        val mainBody = Body()
        println("I create main body")
        /*
        val numOne = NumberValue("Int", "NumberValue", 0,"1")
        val numTwo = NumberValue("Int", "NumberValue", 1,"2")
        val numFive = NumberValue("Int", "NumberValue", 2,"5")
        val numThree = NumberValue("Int", "NumberValue", 5,"3")
        val numTen = NumberValue("Int", "NumberValue", 6,"10")
        println("I create all numbers")
        val mathOperLittle = MathOperator("Int", "MathOperator", 3, numOne, numTwo, "+")
        val mathOperMain = MathOperator("Int", "MathOperator", 4, mathOperLittle, numFive, "*")
        println("I create all operations")
        mainBody.bodyInsides.add(SpareOutputBlock("output", mathOperMain))
        println("I add output operations")
        val ineqTest = LogicOperator("Boolean", "LogicOperator", 7, mathOperMain, numTen, ">")
        val eqTest = LogicOperator("Boolean", "LogicOperator", 8, mathOperLittle, numThree, "==")
        val condTest = Logic("Boolean", "Logic", 9, ineqTest, eqTest, "and")
        mainBody.bodyInsides.add(IfBlock("if", condTest))
        val message = StringValue("String", "StringValue", 10, "I check this condition!!!!", 10)
        mainBody.bodyInsides[1].bodyOfBlock.bodyInsides.add(SpareOutputBlock("output", message))
        mainBody.doBody()
        println("I finish program!!!")

         */

        /*
        println("I create main body")
        mainBody.bodyInsides.add(SpareOutputBlock("output", "first"))
        mainBody.bodyInsides.add(SpareOutputBlock("output", "second"))
        mainBody.bodyInsides.add(IfBlock("if", true))
        println("I add first part to main body")
        mainBody.bodyInsides[2].bodyOfBlock.bodyInsides.add(SpareOutputBlock("output", "firstInIf"))
        mainBody.bodyInsides[2].bodyOfBlock.bodyInsides.add(
            SpareOutputBlock(
                "output",
                "firstInSecond"
            )
        )
        println("i add all to if body")
        mainBody.bodyInsides.add(SpareOutputBlock("output", "thirst"))
        println("i add all to main body and gonna to start program")
        mainBody.doBody()
        println("i finish program!!!")
        println("now im gonna to add new thing")
        mainBody.bodyInsides.add(1, SpareOutputBlock("output", "firstButAddedOn2String"))
        println("i add it and gonna to start program")
        mainBody.doBody()
        println("i finish program again!!!")
        */

    }

    val blockDnD = View.OnLongClickListener() {
        println("YEEEE")
        val checkText = "Yepppp"
        val item = ClipData.Item(checkText)
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(checkText, mimeTypes, item)
        val dragShadowBuilder = View.DragShadowBuilder(it)
        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
        true
    }

    val dragListener = View.OnDragListener() { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                println("Up")
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                println("In")
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                println("Loc")
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                println("Exit")
                true
            }

            DragEvent.ACTION_DROP -> {
                println("Drop")
                val item: ClipData.Item = event.clipData.getItemAt(0)
                val dragData = item.text
                Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()

                val v = event.localState as View
                val owner = v.parent as ViewGroup
                owner.removeView(v)
                //val vNew = LayoutInflater.from(this).inflate(v, null) as View
                //View vNew = new View(context)
                //ask about copy View
                val dest = view as LinearLayout
                dest.addView(v)
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                println("End")
                //println(event.getLocalState())
                when (event.result) {
                    true ->
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG)

                    else ->
                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG)
                }.show()
                true
            }

            else -> {
                println("Noooo(")
                false
            }
        }
    }
}


fun printToConsole (included: String) {
    println("Console: " + included)
}

abstract class MainOperator (val type : String, val typeOfBlock : String, val id: Int) {
    open fun define(): Boolean {return false}
    open fun calculate(): Int {return 0}
    open fun valueToString(): String {
        when (this.typeOfBlock) {
            "Logic" -> {
                return (define().toString())
            }
            "LogicOperator" -> {
                return (define().toString())
            }
            "MathOperator" -> {
                return (this.calculate().toString())
            }
            else -> {
                return (this.takeValue())
            }
        }
    }
    open fun takeValue() : String {return ""}
}

abstract class Block (type : String, typeOfBlock : String, id: Int) : MainOperator (type, typeOfBlock, id) {
}

class MathOperator (type : String, typeOfBlock : String, id: Int, val first: Block, val second: Block, val typeOfOper: String) : Block (type, typeOfBlock, id) {
    override fun calculate(): Int {
        var value1 = 0
        var value2 = 0
        var value3 = 0

        when (first.typeOfBlock) {
            "MathOperator" -> {
                value2 = first.calculate()
            }

            "NumberValue" -> {
                value2 = first.takeValue().toInt()
            }

            "NumberVariable" -> {
                value2 = first.takeValue().toInt()
            }

            else -> {
                printToConsole("value2 " + "Math operand Error")
            }
        }
        //printToConsole(value2.toString())
        when (second.typeOfBlock) {
            "MathOperator" -> {
                value3 = second.calculate()
            }

            "NumberValue" -> {
                value3 = second.takeValue().toInt()
            }

            "NumberVariable" -> {
                value3 = second.takeValue().toInt()
            }

            else -> {
                printToConsole("Math operand Error")
            }
        }
        //printToConsole("value3 " + value3.toString())
        when (typeOfOper) {
            "+" -> {
                value1 = value2 + value3
            }

            "-" -> {
                value1 = value2 - value3
            }

            "/" -> {
                value1 = value2 / value3
            }

            "*" -> {
                value1 = value2 * value3
            }

            "%" -> {
                value1 = value2 % value3
            }

            "!" -> {
                value1 = value2 * -1
            }

            else -> {
                printToConsole("Math move Error")
            }
        }
        return value1
    }

    override fun define(): Boolean {
        val value = calculate().toString()
        when (value) {
            "0" -> return false
            else -> return true
        }
    }
}

class negation(){}

class negativity(){}

class Logic (type : String, typeOfBlock : String, id: Int,  val first: MainOperator, val second: MainOperator, val typeOfLogic: String ) : MainOperator (type, typeOfBlock, id){
    override fun define(): Boolean {
        var value = false
        val value1 = first.define()
        val value2 = second.define()
        when (typeOfLogic) {
            "and" -> {
                value = value1 && value2
            }
            "or" -> {
                value = value1 || value2
            }
            else -> {
                printToConsole("Logistic move Error")
            }
        }
        return value
    }
}

class LogicOperator (type : String, typeOfBlock : String, id: Int,  val first: Block, val second: Block, val typeOfLogic: String ) : MainOperator (type, typeOfBlock, id){
    override fun define(): Boolean {
        var value = false
        var value1 = 0
        var value2 = 0

        when (first.typeOfBlock) {
            "MathOperator" -> {
                value1 = first.calculate()
            }

            "NumberValue" -> {
                value1 = first.takeValue().toInt()
            }

            "NumberVariable" -> {
                value1 = first.takeValue().toInt()
            }

            else -> {
                printToConsole( "logic Math operand Error")
            }
        }
        when (second.typeOfBlock) {
            "MathOperator" -> {
                value2 = second.calculate()
            }

            "NumberValue" -> {
                value2 = second.takeValue().toInt()
            }

            "NumberVariable" -> {
                value2 = second.takeValue().toInt()
            }

            else -> {
                printToConsole("logic Math operand Error")
            }
        }
        when (typeOfLogic) {
            ">" -> {
                if(value1 > value2){
                    value = true
                }
            }
            "<" -> {
                if(value1 < value2){
                    value = true
                }
            }
            ">=" -> {
                if(value1 >= value2){
                    value = true
                }
            }
            "<=" -> {
                if(value1 <= value2){
                    value = true
                }
            }
            "==" -> {
                if(value1 == value2){
                    value = true
                }
            }
            "!=" -> {
                if (value1 != value2) {
                    value = true
                }
            }
            else -> {
                printToConsole("Logistic move Error")
            }
        }
        return value
    }
}

abstract class Value (type : String, typeOfBlock : String, id: Int, val value : String) : Block (type, typeOfBlock, id) {

}

class BooleanValue (type : String, typeOfBlock : String, id: Int, value : String) : Value (type, typeOfBlock, id, value) {
    override fun define(): Boolean {
        return value.toBoolean()
    }
    override fun takeValue(): String {
        return value
    }
}

class NumberValue (type : String, typeOfBlock : String, id: Int, value : String) : Value (type, typeOfBlock, id, value) {
    override fun define(): Boolean {
        when (value) {
            "0" -> return false
            else -> return true
        }
    }
    override fun takeValue(): String {
        return value
    }
}

class StringValue (type : String, typeOfBlock : String, id: Int, value : String, val size: Int) : Value (type, typeOfBlock, id, value) {
    override fun define(): Boolean {
        when (size) {
            0 -> return false
            else -> return true
        }
    }
    override fun takeValue(): String {
        return value
    }
}

abstract class Variable (type : String, typeOfBlock : String, id: Int, val value : String, val name: String) : Block (type, typeOfBlock, id) {

}

class BooleanVariable (type : String, typeOfBlock : String, id: Int, name: String, value : String) : Variable (type, typeOfBlock, id, value, name) {
    override fun define(): Boolean {
        return value.toBoolean()
    }
    override fun takeValue(): String {
        return value
    }
}

class NumberVariable (type : String, typeOfBlock : String, id: Int, name: String, value : String) : Variable (type, typeOfBlock, id, value, name) {
    override fun define(): Boolean {
        when (value) {
            "0" -> return false
            else -> return true
        }
    }
    override fun takeValue(): String {
        return value
    }
}

class StringVariable (type : String, typeOfBlock : String, id: Int, name: String, value : String, val size: Int) : Variable (type, typeOfBlock, id, value, name) {
    override fun define(): Boolean {
        when (size) {
            0 -> return false
            else -> return true
        }
    }
    override fun takeValue(): String {
        return value
    }
}



abstract class FunBlock (val type : String) {
    val bodyOfBlock = Body()
    open fun checkCond() {}
    open fun doOutput() {}
}

class SpareOutputBlock (type : String, private val included : MainOperator) : FunBlock(type) {
    override fun doOutput () {
        printToConsole(included.valueToString())
    }
}

class IfBlock (type : String, var cond : MainOperator) : FunBlock(type) {
    override fun checkCond() {
        if (cond.define()) {
            bodyOfBlock.doBody()
        }
    }

}

class Body() {
    val bodyInsides = mutableListOf<FunBlock>()

    fun doBody() {
        for (i in bodyInsides) {
            when (i.type) {
                "if" -> i.checkCond()
                "output" -> i.doOutput()
            }
        }
    }
}