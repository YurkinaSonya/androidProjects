package com.example.mobilemodule

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

var hashMapOfVariableValues : HashMap<String, String> = HashMap<String, String>()
var hashMapOfVariableTypes : HashMap<String, String> = HashMap<String, String>()
//var hashMapOfVariable : HashMap<String, VariableData> = HashMap<String, VariableData>()
val varNames = arrayListOf<String>("-")
var varNameRightNow = ""
var pltInd = 0

//var spinnerArrayAdapter: ArrayAdapter<String>? = null
fun HashMap<String, VariableData>.getValue (key : String) : VariableData {
    return this[key]?:throw IllegalArgumentException()
}


class MainActivity : AppCompatActivity() {
    var mainBody = Body()
    var indCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        //hashMapOfVariable["a"] = VariableData("Int", "0")
        //var testStr = hashMapOfVariable["a"]?.value?:return


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
                paramsList.width = ViewGroup.LayoutParams.WRAP_CONTENT
                params.width = 500
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
        val buf = binding.linLayoutBuf

        list.setOnDragListener(dragListenerDelete)
        plt.setOnDragListener(dragListenerPlt)
        buf.setOnDragListener(dragListenerBuf)

        val blockCout = binding.coutBlock
        blockCout.setOnLongClickListener() {
            val checkText = "Cout"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val blockIf = binding.ifBlock
        blockIf.setOnLongClickListener() {
            val checkText = "If"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val blockWhile = binding.whileBlock
        blockWhile.setOnLongClickListener() {
            val checkText = "While"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val blockInit = binding.initBlock
        blockInit.setOnLongClickListener() {
            val checkText = "Init"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val blockAssig = binding.assigBlock
        blockAssig.setOnLongClickListener() {
            val checkText = "Assig"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val mathOperator = binding.mathOper
        mathOperator.setOnLongClickListener() {
            val checkText = "oper.Math"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val ireqOperator = binding.ireqOper
        ireqOperator.setOnLongClickListener() {
            val checkText = "oper.Ireq"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val concatOperator = binding.concatOper
        concatOperator.setOnLongClickListener() {
            val checkText = "oper.Concat"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val logicOperator = binding.logicOper
        logicOperator.setOnLongClickListener() {
            val checkText = "oper.Logic"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val minusOperator = binding.negNum
        minusOperator.setOnLongClickListener() {
            val checkText = "oper.Minus"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val negOperator = binding.negBool
        negOperator.setOnLongClickListener() {
            val checkText = "oper.Neg"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val strValueOperator = binding.stringValueOper
        strValueOperator.setOnLongClickListener() {
            val checkText = "oper.strValue"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val numValueOperator = binding.numValueOper
        numValueOperator.setOnLongClickListener() {
            val checkText = "oper.numValue"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val boolValueOperator = binding.boolValueOper
        boolValueOperator.setOnLongClickListener() {
            val checkText = "oper.boolValue"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val strVariableOperator = binding.stringVariableOper
        strVariableOperator.setOnLongClickListener() {
            val checkText = "oper.strVariable"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val numVariableOperator = binding.numVariableOper
        numVariableOperator.setOnLongClickListener() {
            val checkText = "oper.numVariable"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val boolVariableOperator = binding.boolVariableOper
        boolVariableOperator.setOnLongClickListener() {
            val checkText = "oper.boolVariable"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }




        val btn = binding.buttonInter
        btn.setOnClickListener () {
            mainBody = createBlocksInBody(plt)
            //println(mainBody.bodyInsides)

            /*
            mainBody.bodyInsides.clear()
            mainBody.bodyInsides.add(Init("initialization", "testVariable", "Int"))
            val testVar = NumberVariable("Int", "NumberVariable", 2, "testVariable", hashMapOfVariableValues["testVariable"].toString())
            val numZero = NumberValue("Int", "NumberValue", 6,"0")
            val numOne = NumberValue("Int", "NumberValue", 0,"1")
            val numTwo = NumberValue("Int", "NumberValue", 1,"2")
            val numTen = NumberValue("Int", "NumberValue", 6,"10")
            mainBody.bodyInsides.add(Assignment("assignment", testVar, numOne))
            val condTest = LogicOperator("Boolean", "LogicOperator", 8, testVar, numTen, "!=")
            mainBody.bodyInsides.add(WhileBlock("while", condTest))
            val mathOperLittle = MathOperator("Int", "MathOperator", 3, testVar, numOne, "+")
            val mathOperCondition = MathOperator("Int", "MathOperator", 3, testVar, numTwo, "%")
            val condIf = LogicOperator("Boolean", "LogicOperator", 8, mathOperCondition, numZero, "==")
            mainBody.bodyInsides[2].bodyOfBlock.bodyInsides.add(IfBlock("if", condIf, true))
            val messageIf = StringValue("String", "StringValue", 10, "it is in loop and in true if: ")
            val messageElse = StringValue("String", "StringValue", 10, "it is in loop and in false if: ")
            mainBody.bodyInsides[2].bodyOfBlock.bodyInsides[0].bodyOfBlock.bodyInsides.add(Output("output", Concat("String", "Concat", 11, messageIf, ToStringOper("String", "toString", 1, testVar))))
            mainBody.bodyInsides[2].bodyOfBlock.bodyInsides[0].secondBodyOfBlock.bodyInsides.add(Output("output", Concat("String", "Concat", 11, messageElse, ToStringOper("String", "toString", 1, testVar))))
            mainBody.bodyInsides[2].bodyOfBlock.bodyInsides.add(Assignment("assignment", testVar, mathOperLittle))

             */



            println("I start program!!!")
            mainBody.doBody()
            println("I finish program!!!")
        }
    }

    private fun createBlocksInBody (llBody : LinearLayout) : Body {
        val newBody = Body()
        for (i in 1 until llBody.getChildCount()) {
            //println(llBody.getChildAt(i))
            val block = llBody.getChildAt(i) as RelativeLayout
            //println(block)
            //println(block.transitionName)
            //println(block.getChildCount())
            /*
            for (j in 0 until block.getChildCount()) {
                println("     " + block.getChildAt(j))
                println("     " + block.getChildAt(j).transitionName)

            }
             */

            val blockName = block.transitionName
            when (blockName) {
                "init" -> {
                    //println("it is init")
                    val ll = block.getChildAt(0) as LinearLayout
                    val spinner = ll.getChildAt(0) as Spinner
                    val nameOfVariable = ll.getChildAt(1) as EditText
                    //val newBlock =  Output("output", true, StringValue("String", "StringValue", 0, "this is init block"))
                    val newBlock =  Init("initialization", nameOfVariable.text.toString(), spinner.selectedItem.toString())
                    newBody.bodyInsides.add(newBlock)
                }
                "assig" -> {
                    println("it is ass")
                    val ll = block.getChildAt(0) as LinearLayout
                    val spinner = ll.getChildAt(0) as Spinner
                    val llForOper = ll.getChildAt(2) as LinearLayout
                    val llOper = llForOper.getChildAt(0) as RelativeLayout
                    //println("     " + ll.getChildAt(2))
                    //val newBlock = Output("output", true, StringValue("String", "StringValue", 0, "this is ass block"))
                    val newBlock = Assignment("assignment", spinner.selectedItem.toString(), createMainOperator(llOper))
                    newBody.bodyInsides.add(newBlock)
                }
                "output" -> {
                    println("it is output")
                    val newBlock = Output("output", true, StringValue("String", "StringValue", 0, "this is output block"))
                    newBody.bodyInsides.add(newBlock)
                }
                "if" -> {
                    println("it is if")
                    val newBlock = Output("output", true, StringValue("String", "StringValue", 0, "this is if block"))
                    newBody.bodyInsides.add(newBlock)
                }
                "while" -> {
                    println("it is while")
                    val newBlock = Output("output", true, StringValue("String", "StringValue", 0, "this is while block"))
                    newBody.bodyInsides.add(newBlock)
                }
                else -> {
                    val newBlock = Output("output", false, StringValue("String", "StringValue", 0, "Wrong block reading!!! " + blockName))
                    newBody.bodyInsides.add(newBlock)
                }
            }
        }
        return newBody
    }

    fun createMainOperator (rl : RelativeLayout) : MainOperator {
        val ll = rl.getChildAt(0) as LinearLayout
        val blockName = ll.transitionName
        println (blockName)
        when (blockName) {
            "IntValue" -> {
                val valueOfOper = ll.getChildAt(0) as EditText
                val block = NumberValue("Int", "NumberValue", 6,valueOfOper.text.toString())
                return block
            }
            "BoolValue" -> {
                val spinner = ll.getChildAt(0) as Spinner
                val block = BooleanValue("Boolean", "BooleanValue", 6,spinner.selectedItem.toString())
                return block
            }
            "StringValue" -> {
                val valueOfOper = ll.getChildAt(0) as EditText
                val block = StringValue("String", "StringValue", 6, valueOfOper.text.toString())
                return block
            }
            "IntVariable" -> {
                val spinner = ll.getChildAt(1) as Spinner
                val block = NumberVariable("Int", "NumberVariable", 6, spinner.selectedItem.toString())
                return block
            }
            "StringVariable" -> {
                val spinner = ll.getChildAt(1) as Spinner
                val block = StringVariable("String", "StringVariable", 6, spinner.selectedItem.toString())
                return block
            }
            "BoolVariable" -> {
                val spinner = ll.getChildAt(1) as Spinner
                val block = BooleanVariable("Boolean", "BooleanVariable", 6, spinner.selectedItem.toString())
                return block
            }

            "Math" -> {

                val lFirst = ll.getChildAt(0) as LinearLayout
                val mathFirst = lFirst.getChildAt(0) as RelativeLayout
                val spinner = ll.getChildAt(1) as Spinner
                val lSecond = ll.getChildAt(2) as LinearLayout
                val mathSecond = lSecond.getChildAt(0) as RelativeLayout
                //println(ll)
                //println(mathFirst)
                val block = MathOperator("Int", "Math", 6, createBlock(mathFirst), createBlock(mathSecond), spinner.selectedItem.toString())
                return block

            }



            else -> {
                return NumberValue("Int", "NumberValue", 6,"0")
            }
        }

    }

    fun createBlock (rl : RelativeLayout) : Block {
        val ll = rl.getChildAt(0) as LinearLayout
        val blockName = ll.transitionName
        println (blockName)
        when (blockName) {
            "Math" -> {
                val mathFirst = ll.getChildAt(0) as RelativeLayout
                val spinner = ll.getChildAt(1) as Spinner
                val mathSecond = ll.getChildAt(2) as RelativeLayout
                val block = MathOperator("Int", "Math", 6, createBlock(mathFirst), createBlock(mathSecond), spinner.selectedItem.toString())
                return block
            }
            else -> {
                return NumberValue("Int", "NumberValue", 6,"0")
            }
        }
    }



    val dragListenerPlt = View.OnDragListener() { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                //println("Up")
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                //println("In")
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                //println("Loc")
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                //println("Exit")
                true
            }

            DragEvent.ACTION_DROP -> {
                //println("Drop")
                val item: ClipData.Item = event.clipData.getItemAt(0)
                val dragData = item.text
                Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()
                val blockName = dragData.toString()
                val list = blockName.split(".")

                if (list[0] != "move") {
                    if (list.size == 1) {
                        val dest = view as LinearLayout
                        build(list[0], dest)
                    }
                }
                else {
                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)
                    val dest = view as ViewGroup
                    dest.addView(v)
                }





                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                //println("End")
                //println(event.getLocalState())
                /*
                when (event.result) {
                    true ->
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG)

                    else ->
                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG)
                }.show()

                 */
                true
            }

            else -> {
                println("Noooo(")
                false
            }
        }
    }

    val dragListenerBuf = View.OnDragListener() { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                //println("Up")
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                //println("In")
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                //println("Loc")
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                //println("Exit")
                true
            }

            DragEvent.ACTION_DROP -> {
                //println("Drop")
                val item: ClipData.Item = event.clipData.getItemAt(0)
                val dragData = item.text
                Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()
                val blockName = dragData.toString()
                val list = blockName.split(".")

                if (list[0] != "move") {
                    val dest = view as LinearLayout
                    build(list[0], dest)
                }
                else {
                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)
                    val dest = view as ViewGroup
                    dest.addView(v)
                }



                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                //println("End")
                //println(event.getLocalState())
                /*
                when (event.result) {
                    true ->
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG)

                    else ->
                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG)
                }.show()

                 */
                true
            }

            else -> {
                println("Noooo(")
                false
            }
        }
    }

    val dragListenerOperator = View.OnDragListener() { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                //println("Up")
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                //println("In")
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                //println("Loc")
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                //println("Exit")
                true
            }

            DragEvent.ACTION_DROP -> {
                //println("Drop")
                val item: ClipData.Item = event.clipData.getItemAt(0)
                val dragData = item.text
                Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()
                val blockName = dragData.toString()
                val list = blockName.split(".")

                val dest = view as LinearLayout


                if (list[0] != "move") {
                    if (list[0] == "oper") {
                        val vCheck = event.localState as View
                        //println(vCheck.transitionName + " " + dest.transitionName)
                        if (vCheck.transitionName == dest.transitionName && dest.transitionName != "All") {
                            if (dest.childCount != 0) {
                                //dest.removeAllViews()
                                val v = dest.getChildAt(0) as View
                                val owner = v.parent as ViewGroup
                                owner.removeView(v)
                                //println("i cant do it")
                            }
                            //printToConsole("all is okay")
                            build(blockName, dest)
                        }
                        else {
                            if (dest.transitionName == "All") {
                                if (dest.childCount != 0) {
                                    //dest.removeAllViews()
                                    val v = dest.getChildAt(0) as View
                                    val owner = v.parent as ViewGroup
                                    owner.removeView(v)
                                    //println("i cant do it")
                                }
                                //printToConsole("all is okay")
                                build(blockName, dest)
                            }
                        }

                    }
                }
                else {
                    if (list[1] == "oper") {
                        val v = event.localState as View
                        if (v.transitionName == dest.transitionName && dest.transitionName != "All") {
                            if(dest.childCount == 0) {
                                val owner = v.parent as ViewGroup
                                owner.removeView(v)
                                dest.addView(v)
                            }
                        }
                        else {
                            if (dest.transitionName == "All") {
                                if(dest.childCount == 0) {
                                    val owner = v.parent as ViewGroup
                                    owner.removeView(v)
                                    dest.addView(v)
                                }
                            }
                        }
                    }
                }
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                //println("End")
                //println(event.getLocalState())
                /*
                when (event.result) {
                    true ->
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG)

                    else ->
                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG)
                }.show()

                 */
                true
            }

            else -> {
                println("Noooo(")
                false
            }
        }
    }

    val dragListenerDelete = View.OnDragListener() { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                //println("Up")
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                //println("In")
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                //println("Loc")
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                //println("Exit")
                true
            }

            DragEvent.ACTION_DROP -> {
                //println("Drop")
                val item: ClipData.Item = event.clipData.getItemAt(0)
                val dragData = item.text
                Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()
                val str = dragData.toString()
                val list = str.split(".")
                //println(list)
                if (list[0] == "move") {
                    val v = event.localState as View
                    if (list[1] == "Init") {
                        //println(v.findViewById<EditText>(R.id.inputName).text)
                        //println(varNames)
                        val varDelete = v.findViewById<EditText>(R.id.inputName).text.toString()
                        //println(varDelete)
                        //varNames.minus(v.findViewById<EditText>(R.id.inputName).text.toString())
                        if (varDelete != "") {
                            varNames.removeAt(varNames.indexOf(varDelete))
                        }
                        //println(varNames)
                    }
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)
                    //addBlockToList(dragData.toString())
                }
                //println(list[2])


                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                //println("End")
                //println(event.getLocalState())
                /*
                when (event.result) {
                    true ->
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG)

                    else ->
                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG)
                }.show()

                 */
                true
            }

            else -> {
                println("Noooo(")
                false
            }
        }
    }


    private fun build(name : String, dest : LinearLayout) {
        var view = layoutInflater.inflate(R.layout.cout_block, null) as View
        val list = name.split(".")
        when (list[0]) {
            "Cout" -> {
                view = layoutInflater.inflate(R.layout.cout_block, null) as View
                val outputOper = view.findViewById<LinearLayout>(R.id.coutBlockOper)
                outputOper.setOnDragListener(dragListenerOperator)
            }
            "Init" -> {
                view = layoutInflater.inflate(R.layout.initialization_block, null) as View
                val editText = view.findViewById<EditText>(R.id.inputName)
                editText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        varNameRightNow = s.toString()
                        //println("now var name is: " + varNameRightNow + ", but i (maybe) will change it")
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    }

                    override fun afterTextChanged(s: Editable?) {
                        if (s.toString() != varNameRightNow) {
                            if (varNames.indexOf(varNameRightNow) != -1) {
                                varNames[varNames.indexOf(varNameRightNow)] = s.toString()
                                //println("i swap one var by another")
                            }
                            else {
                                varNames += s.toString()
                                //println("i add new var")
                            }
                        }
                        //println("text was changed")
                    }
                })

            }
            "If" -> {
                view = layoutInflater.inflate(R.layout.if_block, null) as View
                val bodyIf = view.findViewById<LinearLayout>(R.id.ifBodyInPlt)
                bodyIf.setOnDragListener(dragListenerPlt)
                val bodyElse = view.findViewById<LinearLayout>(R.id.elseBodyInPlt)
                bodyElse.setOnDragListener(dragListenerPlt)
                val condOper = view.findViewById<LinearLayout>(R.id.ifCondOper)
                condOper.setOnDragListener(dragListenerOperator)
            }
            "While" -> {
                view = layoutInflater.inflate(R.layout.while_block, null) as View
                val bodyWhile = view.findViewById<LinearLayout>(R.id.whileBodyInPlt)
                bodyWhile.setOnDragListener(dragListenerPlt)
                val condOper = view.findViewById<LinearLayout>(R.id.whileCondOper)
                condOper.setOnDragListener(dragListenerOperator)
            }
            "Assig" -> {
                view = layoutInflater.inflate(R.layout.assignment_block, null) as View
                val spinner = view.findViewById<Spinner>(R.id.nameSpinner)
                val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, varNames)
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                with(spinner) {
                    adapter = aa
                }
                val assigOper = view.findViewById<LinearLayout>(R.id.assigOper)
                assigOper.setOnDragListener(dragListenerOperator)
            }
            "oper" -> {
                when (list[1]) {
                    "Math" -> {
                        view = layoutInflater.inflate(R.layout.math_oper, null) as View
                        val firstMath = view.findViewById<LinearLayout>(R.id.mathOperFirst)
                        firstMath.setOnDragListener(dragListenerOperator)
                        val secondMath = view.findViewById<LinearLayout>(R.id.mathOperSecond)
                        secondMath.setOnDragListener(dragListenerOperator)
                    }
                    "Ireq" -> {
                        view = layoutInflater.inflate(R.layout.eq_oper, null) as View
                        val firstEq = view.findViewById<LinearLayout>(R.id.eqOperFirst)
                        firstEq.setOnDragListener(dragListenerOperator)
                        val secondEq = view.findViewById<LinearLayout>(R.id.eqOperSecond)
                        secondEq.setOnDragListener(dragListenerOperator)
                    }
                    "Concat" -> {
                        view = layoutInflater.inflate(R.layout.concat_oper, null) as View
                        val firstConcat = view.findViewById<LinearLayout>(R.id.concatOperFirst)
                        firstConcat.setOnDragListener(dragListenerOperator)
                        val secondConcat = view.findViewById<LinearLayout>(R.id.concatOperSecond)
                        secondConcat.setOnDragListener(dragListenerOperator)
                    }
                    "Logic" -> {
                        view = layoutInflater.inflate(R.layout.logic_oper, null) as View
                        val firstLogic = view.findViewById<LinearLayout>(R.id.logicOperFirst)
                        firstLogic.setOnDragListener(dragListenerOperator)
                        val secondLogic = view.findViewById<LinearLayout>(R.id.logicOperSecond)
                        secondLogic.setOnDragListener(dragListenerOperator)
                    }
                    "Minus" -> {
                        view = layoutInflater.inflate(R.layout.minus_oper, null) as View
                        val minusOper = view.findViewById<LinearLayout>(R.id.minusOperFirst)
                        minusOper.setOnDragListener(dragListenerOperator)
                    }
                    "Neg" -> {
                        view = layoutInflater.inflate(R.layout.neg_oper, null) as View
                        val negOper = view.findViewById<LinearLayout>(R.id.negOperFirst)
                        negOper.setOnDragListener(dragListenerOperator)
                    }
                    "strValue" -> {
                        view = layoutInflater.inflate(R.layout.str_value_oper, null) as View
                    }
                    "numValue" -> {
                        view = layoutInflater.inflate(R.layout.num_value_oper, null) as View
                    }
                    "boolValue" -> {
                        view = layoutInflater.inflate(R.layout.bool_value_oper, null) as View
                    }
                    "strVariable" -> {
                        view = layoutInflater.inflate(R.layout.string_variable_oper, null) as View
                        val spinner = view.findViewById<Spinner>(R.id.spinner)
                        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, varNames)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        with(spinner) {
                            adapter = aa
                        }
                    }
                    "numVariable" -> {
                        view = layoutInflater.inflate(R.layout.int_variable_oper, null) as View
                        val spinner = view.findViewById<Spinner>(R.id.spinner)
                        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, varNames)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        with(spinner) {
                            adapter = aa
                        }
                    }
                    "boolVariable" -> {
                        view = layoutInflater.inflate(R.layout.bool_variable_oper, null) as View
                        val spinner = view.findViewById<Spinner>(R.id.spinner)
                        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, varNames)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        with(spinner) {
                            adapter = aa
                        }
                    }
                }
            }
        }
        //val layout = findViewById<LinearLayout>(R.id.linLayoutPlt)
        view.setOnLongClickListener() {
            val checkText = "move." + name
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        dest.addView(view)
    }

}




fun printToConsole (type : Boolean, included: String) {
    if (type) {
        println("Console: " + included)
    }
    else {
        println("Console Error: " + included)
    }
}

data class VariableData (val type: String, val value: String) {}

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

abstract class Block (type : String, typeOfBlock : String, id: Int) : MainOperator (type, typeOfBlock, id) {}

class MathOperator (type : String, typeOfBlock : String, id: Int, val first: Block, val second: Block, val typeOfOper: String) : Block (type, typeOfBlock, id) {
    override fun calculate(): Int {
        var value1 = 0
        var value2 = 0
        var value3 = 0

        when (first.typeOfBlock) {
            "MathOperator" -> {
                value2 = first.calculate()
            }

            "Negativity" -> {
                value2 = first.calculate()
            }

            "NumberValue" -> {
                value2 = first.takeValue().toInt()
            }

            "NumberVariable" -> {
                value2 = first.takeValue().toInt()
            }

            else -> {
                printToConsole(false,"value2 " + "Math operand Error")
            }
        }
        //printToConsole(value2.toString())
        when (second.typeOfBlock) {
            "MathOperator" -> {
                value3 = second.calculate()
            }

            "Negativity" -> {
                value3 = second.calculate()
            }

            "NumberValue" -> {
                value3 = second.takeValue().toInt()
            }

            "NumberVariable" -> {
                value3 = second.takeValue().toInt()
            }

            else -> {
                printToConsole(false, "Math operand Error")
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

            else -> {
                printToConsole(false, "Math move Error")
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

    override fun takeValue(): String {
        val value = calculate().toString()
        return value
    }
}

class Negativity(type : String, typeOfBlock : String, id: Int, val first: Block) : Block (type, typeOfBlock, id){
    override fun calculate(): Int {
        var value = 0
        when (first.typeOfBlock) {
            "MathOperator" -> {
                value = first.calculate()
            }

            "Negativity" -> {
                value = first.calculate()
            }

            "NumberValue" -> {
                value = first.takeValue().toInt()
            }

            "NumberVariable" -> {
                value = first.takeValue().toInt()
            }

            else -> {
                printToConsole(false, "value " + "Math neg operand Error")
            }
        }
        return value * -1
    }
    override fun define(): Boolean {
        val value = calculate().toString()
        when (value) {
            "0" -> return false
            else -> return true
        }
    }
}

class Negation(type : String, typeOfBlock : String, id: Int,  val first: MainOperator) : MainOperator (type, typeOfBlock, id){
    override fun define(): Boolean {
        val value = first.define()
        return !value
    }
}

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
                printToConsole(false, "Logistic move Error")
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

            "Negativity" -> {
                value1 = first.calculate()
            }

            "NumberValue" -> {
                value1 = first.takeValue().toInt()
            }

            "NumberVariable" -> {
                value1 = first.takeValue().toInt()
            }

            else -> {
                printToConsole( false, "logic Math operand Error")
            }
        }
        when (second.typeOfBlock) {
            "MathOperator" -> {
                value2 = second.calculate()
            }

            "Negativity" -> {
                value2 = second.calculate()
            }

            "NumberValue" -> {
                value2 = second.takeValue().toInt()
            }

            "NumberVariable" -> {
                value2 = second.takeValue().toInt()
            }

            else -> {
                printToConsole(false, "logic Math operand Error")
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
                printToConsole(false, "Logistic move Error")
            }
        }
        return value
    }
}

class ToStringOper(type : String, typeOfBlock : String, id: Int, val first: Block) : Block (type, typeOfBlock, id) {
    override fun takeValue(): String {
        var str = ""
        when (first.typeOfBlock) {
            "NumberVariable" -> {
                str = first.takeValue()
            }
            "NumberValue" -> {
                str = first.takeValue()
            }
            "BooleanVariable" -> {
                str = first.takeValue()
            }
            "BooleanValue" -> {
                str = first.takeValue()
            }
            else -> {
                printToConsole(false, "to string operand Error")
            }

        }
        return str
    }
}

class Concat (type : String, typeOfBlock : String, id: Int,  val first: Block, val second: Block) : MainOperator (type, typeOfBlock, id){
    override fun takeValue(): String {
        var str1 = ""
        var str2 = ""
        when (first.typeOfBlock) {
            "Concat" -> {
                str1 = first.takeValue()
            }

            "StringValue" -> {
                str1 = first.takeValue()
            }

            "StringVariable" -> {
                str1 = first.takeValue()
            }
            "toString" -> {
                str1 = first.takeValue()
            }
            else -> {
                printToConsole(false, "value1 " + "Concat operand Error")
            }
        }
        when (second.typeOfBlock) {
            "Concat" -> {
                str2 = second.takeValue()
            }

            "StringValue" -> {
                str2 = second.takeValue()
            }

            "StringVariable" -> {
                str2 = second.takeValue()
            }
            "toString" -> {
                str2 = second.takeValue()
            }
            else -> {
                printToConsole(false, "value2 " + "Concat operand Error")
            }
        }
        return str1 + str2
    }
    override fun define(): Boolean {
        when (takeValue().count()) {
            0 -> return false
            else -> return true
        }
    }
}

abstract class Value (type : String, typeOfBlock : String, id: Int, var value : String) : Block (type, typeOfBlock, id) {}

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

class StringValue (type : String, typeOfBlock : String, id: Int, value : String) : Value (type, typeOfBlock, id, value) {
    override fun define(): Boolean {
        when (value.count()) {
            0 -> return false
            else -> return true
        }
    }
    override fun takeValue(): String {
        return value
    }
}

abstract class Variable (type : String, typeOfBlock : String, id: Int, val name: String) : Block (type, typeOfBlock, id) {}

class BooleanVariable (type : String, typeOfBlock : String, id: Int, name: String) : Variable (type, typeOfBlock, id, name) {
    override fun define(): Boolean {
        return hashMapOfVariableValues[name].toBoolean()
    }
    override fun takeValue(): String {
        return hashMapOfVariableValues[name].toString()
    }
}

class NumberVariable (type : String, typeOfBlock : String, id: Int, name: String) : Variable (type, typeOfBlock, id, name) {
    override fun define(): Boolean {
        when (hashMapOfVariableValues[name]) {
            "0" -> return false
            else -> return true
        }
    }
    override fun takeValue(): String {
        return hashMapOfVariableValues[name].toString()
    }
}

class StringVariable (type : String, typeOfBlock : String, id: Int, name: String) : Variable (type, typeOfBlock, id, name) {
    override fun define(): Boolean {
        when (hashMapOfVariableValues[name].toString().count()) {
            0 -> return false
            else -> return true
        }
    }
    override fun takeValue(): String {
        return hashMapOfVariableValues[name].toString()
    }
}



abstract class FunBlock (val type : String) {
    val bodyOfBlock = Body()
    val secondBodyOfBlock = Body()
    open fun checkCond() {}
    open fun doOutput() {}
    open fun createVariable() {}
    open fun checkTypes() {}
}

class Output (type : String, var messageType : Boolean, var included : MainOperator) : FunBlock(type) {
    override fun doOutput () {
        printToConsole(messageType, included.valueToString())
    }
}

class IfBlock (type : String, var cond : MainOperator, var haveElse : Boolean) : FunBlock(type) {
    override fun checkCond() {
        if (cond.define()) {
            bodyOfBlock.doBody()
        }
        else {
            if (haveElse) {
                secondBodyOfBlock.doBody()
            }
        }
    }

}

class WhileBlock (type : String, var cond : MainOperator) : FunBlock(type) {
    override fun checkCond() {
        while (cond.define()) {
            bodyOfBlock.doBody()
        }
    }
}

class Init (type : String, var name: String, var typeOfVariable: String) : FunBlock(type) {
    override fun createVariable() {
        printToConsole(true, "I create variable with name $name and type $typeOfVariable")
        //println(hashMapOfVariableValues)
        when (typeOfVariable) {
            "Int" -> {
                hashMapOfVariableValues.put(name, "0")
                hashMapOfVariableTypes.put(name, "Int")
            }
            "String" -> {
                hashMapOfVariableValues.put(name,"")
                hashMapOfVariableTypes.put(name, "String")
            }
            "Boolean" -> {
                hashMapOfVariableValues.put(name, "False")
                hashMapOfVariableTypes.put(name, "Boolean")
            }
            else -> {
                printToConsole(false, "Type Error")
            }
        }
        //println(hashMapOfVariableValues)
    }
}

class Assignment (type : String, var name: String, var second: MainOperator) : FunBlock(type) {
    override fun checkTypes() {
        printToConsole(true, "I am going to put ${second.takeValue()} in $name variable")
        if (second.type == hashMapOfVariableTypes[name]) {
            //printToConsole("Types are matched " + second.takeValue())
            hashMapOfVariableValues[name] = second.takeValue()
            //println(hashMapOfVariableValues)
        }
        else {
            printToConsole(false , "Types are not matched")
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
                "initialization" -> i.createVariable()
                "assignment" -> i.checkTypes()
                "while" -> i.checkCond()
            }
        }
    }
}