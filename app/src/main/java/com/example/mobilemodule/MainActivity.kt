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
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilemodule.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


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

val hashMapOfVariableValues: HashMap<String, String> = HashMap<String, String>()
val hashMapOfVariableTypes: HashMap<String, String> = HashMap<String, String>()

var hashMapOfArrayValues: HashMap<String, HashMap<Int, String>> =
    HashMap<String, HashMap<Int, String>>()
var hashMapOfArrayTypes: HashMap<String, String> = HashMap<String, String>()
var hashMapOfArraySize: HashMap<String, Int> = HashMap<String, Int>()

val hashMapForOutputMessages: HashMap<Int, String> = HashMap<Int, String>()
val hashMapForOutputTypes: HashMap<Int, Boolean> = HashMap<Int, Boolean>()

val varNames = arrayListOf<String>("-")
var varNameRightNow = ""
val arrNames = arrayListOf<String>("-")
var arrNameRightNow = ""
var outputInd = 0


class MainActivity : AppCompatActivity() {
    var mainBody = Body()
    var indCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var listIsOpen = false
        val viewBlocks = binding.viewForBlocks
        val listBlocks = binding.listOfBlocks
        val listTitle = binding.listTitle

        viewBlocks.setOnClickListener() {
            val params = viewBlocks.layoutParams
            val paramsList = listBlocks.layoutParams
            val paramsTitle = listTitle.layoutParams

            if (!listIsOpen) {
                paramsList.width = ViewGroup.LayoutParams.WRAP_CONTENT
                params.width = 500
                paramsTitle.width = 0
                println("yep")
            } else {
                params.width = 100
                paramsList.width = 0
                paramsTitle.width = ViewGroup.LayoutParams.MATCH_PARENT
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

        plt.setBackgroundResource(R.drawable.shape_for_body_empty)

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
        val blockInitArray = binding.initArrayBlock
        blockInitArray.setOnLongClickListener() {
            val checkText = "InitArray"
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
        val blockAssigArray = binding.assigArrayBlock
        blockAssigArray.setOnLongClickListener() {
            val checkText = "AssigArray"
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
        val arrayIntOperator = binding.arrayIntOper
        arrayIntOperator.setOnLongClickListener() {
            val checkText = "oper.IndIntArray"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val arrayBoolOperator = binding.arrayBoolOper
        arrayBoolOperator.setOnLongClickListener() {
            val checkText = "oper.IndBoolArray"
            val item = ClipData.Item(checkText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(checkText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            true
        }
        val arrayStrOperator = binding.arrayStringOper
        arrayStrOperator.setOnLongClickListener() {
            val checkText = "oper.IndStrArray"
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
        val toStrOperator = binding.toStr
        toStrOperator.setOnLongClickListener() {
            val checkText = "oper.ToStr"
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
        btn.setOnClickListener() {
            hashMapForOutputMessages.clear()
            hashMapForOutputTypes.clear()
            outputInd = 0
            mainBody = createBlocksInBody(plt)
            mainBody.bodyInsides.add(Input("input", "smth"))
            //println("I start program!!!")
            mainBody.doBody()
            //println("I finish program!!!")
        }

        val btnConsole = findViewById<Button>(R.id.buttonConsole)

        btnConsole.setOnClickListener() {
            val dialog = BottomSheetDialog(this)
            val viewConsole = layoutInflater.inflate(R.layout.sheet_console, null)
            val backButtonInSheet = viewConsole.findViewById<Button>(R.id.buttonBack)
            val llInSheet = viewConsole.findViewById<LinearLayout>(R.id.listForConsole)

            backButtonInSheet.setOnClickListener() {
                dialog.dismiss()
            }

            for (i in 0 until outputInd) {
                val viewMessage = layoutInflater.inflate(R.layout.cout_message, null)
                val messageInd = viewMessage.findViewById<TextView>(R.id.coutMessageInd)
                val message = viewMessage.findViewById<TextView>(R.id.coutMessage)
                messageInd.text = i.toString()
                message.text = hashMapForOutputMessages[i]
                llInSheet.addView(viewMessage)
            }

            //println(llInSheet.childCount)

            dialog.setCancelable(false)

            dialog.setContentView(viewConsole)
            dialog.show()
        }


    }


    private fun createBlocksInBody(llBody: LinearLayout): Body {
        val newBody = Body()
        for (i in 1 until llBody.getChildCount()) {
            //println(llBody.getChildAt(i))
            val block = llBody.getChildAt(i) as RelativeLayout
            val blockName = block.transitionName
            when (blockName) {
                "init" -> {
                    //println("it is init")
                    val ll = block.getChildAt(0) as LinearLayout
                    val spinner = ll.getChildAt(0) as Spinner
                    val nameOfVariable = ll.getChildAt(1) as EditText
                    //val newBlock =  Output("output", true, StringValue("String", "StringValue", 0, "this is init block"))
                    val newBlock = Init(
                        "initialization",
                        nameOfVariable.text.toString(),
                        spinner.selectedItem.toString()
                    )
                    newBody.bodyInsides.add(newBlock)
                }

                "initArray" -> {
                    //println("it is init")
                    val ll = block.getChildAt(0) as LinearLayout
                    val spinner = ll.getChildAt(0) as Spinner
                    val nameOfArray = ll.getChildAt(1) as EditText
                    val llForOper = ll.getChildAt(2) as LinearLayout
                    val sizeOfArray = llForOper.getChildAt(0) as RelativeLayout
                    //val newBlock =  Output("output", true, StringValue("String", "StringValue", 0, "this is init block"))
                    val newBlock = InitArray(
                        "arrayInitialization",
                        nameOfArray.text.toString(),
                        spinner.selectedItem.toString(),
                        createBlock(sizeOfArray)
                    )
                    newBody.bodyInsides.add(newBlock)
                }

                "assig" -> {
                    //println("it is ass")
                    val ll = block.getChildAt(0) as LinearLayout
                    val spinner = ll.getChildAt(0) as Spinner
                    val llForOper = ll.getChildAt(2) as LinearLayout
                    val rlOper = llForOper.getChildAt(0) as RelativeLayout
                    val newBlock = Assignment(
                        "assignment",
                        spinner.selectedItem.toString(),
                        createMainOperator(rlOper)
                    )
                    newBody.bodyInsides.add(newBlock)
                }

                "assigArray" -> {
                    //println("it is ass")
                    val ll = block.getChildAt(0) as LinearLayout
                    val spinner = ll.getChildAt(0) as Spinner
                    val llForIndOper = ll.getChildAt(1) as LinearLayout
                    val rlIndOper = llForIndOper.getChildAt(0) as RelativeLayout
                    val llForOper = ll.getChildAt(3) as LinearLayout
                    val rlOper = llForOper.getChildAt(0) as RelativeLayout
                    //println("     " + ll.getChildAt(2))
                    //val newBlock = Output("output", true, StringValue("String", "StringValue", 0, "this is ass block"))
                    val newBlock = ArrayAssignment(
                        "arrayAssignment",
                        spinner.selectedItem.toString(),
                        createBlock(rlIndOper),
                        createMainOperator(rlOper)
                    )
                    newBody.bodyInsides.add(newBlock)
                }

                "output" -> {
                    //println("it is output")
                    val ll = block.getChildAt(0) as LinearLayout
                    val outputForOper = ll.getChildAt(1) as LinearLayout
                    val outputOper = outputForOper.getChildAt(0) as RelativeLayout
                    val newBlock = Output("output", true, createMainOperator(outputOper))
                    newBody.bodyInsides.add(newBlock)
                }

                "if" -> {
                    val ll = block.getChildAt(0) as LinearLayout
                    val llIf = ll.getChildAt(0) as LinearLayout

                    val llForCond = llIf.getChildAt(1) as LinearLayout
                    val rlCond = llForCond.getChildAt(0) as RelativeLayout

                    val llForBodyIf = ll.getChildAt(1) as LinearLayout
                    val llBodyIf = llForBodyIf.getChildAt(0) as LinearLayout


                    val llForBodyElse = ll.getChildAt(3) as LinearLayout
                    val llBodyElse = llForBodyElse.getChildAt(0) as LinearLayout


                    var hasElse = false
                    if (llBodyElse.getChildCount() != 1) {
                        hasElse = true
                    }

                    val newBlock = IfBlock("if", createMainOperator(rlCond), hasElse)
                    newBlock.bodyOfBlock = createBlocksInBody(llBodyIf)
                    if (hasElse) {
                        newBlock.secondBodyOfBlock = createBlocksInBody(llBodyElse)
                    }

                    //val newBlock = Output("output", true, StringValue("String", "StringValue", 0, "this is if block"))
                    newBody.bodyInsides.add(newBlock)
                }

                "while" -> {
                    val ll = block.getChildAt(0) as LinearLayout

                    val llWhile = ll.getChildAt(0) as LinearLayout
                    val llForCond = llWhile.getChildAt(1) as LinearLayout
                    val rlCond = llForCond.getChildAt(0) as RelativeLayout

                    val llForBodyWhile = ll.getChildAt(1) as LinearLayout
                    val llBodyWhile = llForBodyWhile.getChildAt(0) as LinearLayout

                    val newBlock = WhileBlock("while", createMainOperator(rlCond))
                    newBlock.bodyOfBlock = createBlocksInBody(llBodyWhile)

                    //val newBlock = Output("output", true, StringValue("String", "StringValue", 0, "this is if block"))
                    newBody.bodyInsides.add(newBlock)
                }

                else -> {
                    val newBlock = Output(
                        "output",
                        false,
                        StringValue(
                            "String",
                            "StringValue",
                            0,
                            "Wrong block reading!!! " + blockName
                        )
                    )
                    newBody.bodyInsides.add(newBlock)
                }
            }
        }
        return newBody
    }

    fun createMainOperator(rl: RelativeLayout): MainOperator {
        val ll = rl.getChildAt(0) as LinearLayout
        val blockName = ll.transitionName
        //println (blockName)
        when (blockName) {
            "IntValue" -> {
                return (createBlock(rl))
            }

            "BooleanValue" -> {
                return (createBlock(rl))
            }

            "StringValue" -> {
                return (createBlock(rl))
            }

            "IntVariable" -> {
                return (createBlock(rl))
            }

            "StringVariable" -> {
                return (createBlock(rl))
            }

            "BooleanVariable" -> {
                return (createBlock(rl))
            }

            "IntArray" -> {
                return (createBlock(rl))
            }

            "StringArray" -> {
                return (createBlock(rl))
            }

            "BooleanArray" -> {
                return (createBlock(rl))
            }

            "Math" -> {
                return (createBlock(rl))
            }

            "Minus" -> {
                return (createBlock(rl))
            }

            "ToStr" -> {
                return (createBlock(rl))
            }

            "Concat" -> {
                return (createBlock(rl))
            }

            "Logic" -> {
                val lFirst = ll.getChildAt(0) as LinearLayout
                val logicFirst = lFirst.getChildAt(0) as RelativeLayout
                val spinner = ll.getChildAt(1) as Spinner
                val lSecond = ll.getChildAt(2) as LinearLayout
                val logicSecond = lSecond.getChildAt(0) as RelativeLayout
                //println(logicFirst)
                //println(logicSecond)
                val block = Logic(
                    "Boolean",
                    "Logic",
                    6,
                    createMainOperator(logicFirst),
                    createMainOperator(logicSecond),
                    spinner.selectedItem.toString()
                )
                return block
            }

            "Eq" -> {
                val lFirst = ll.getChildAt(0) as LinearLayout
                val eqFirst = lFirst.getChildAt(0) as RelativeLayout
                val spinner = ll.getChildAt(1) as Spinner
                val lSecond = ll.getChildAt(2) as LinearLayout
                val eqSecond = lSecond.getChildAt(0) as RelativeLayout
                val block = LogicOperator(
                    "Boolean",
                    "LogicOperator",
                    6,
                    createBlock(eqFirst),
                    createBlock(eqSecond),
                    spinner.selectedItem.toString()
                )
                return block
            }

            "Neg" -> {
                val lIn = ll.getChildAt(1) as LinearLayout
                val toNeg = lIn.getChildAt(0) as RelativeLayout
                val block = Negation("Boolean", "Negation", 1, createMainOperator(toNeg))
                return block
            }

            else -> {
                return NumberValue("Int", "NumberValue", 6, "0")
            }
        }

    }

    fun createBlock(rl: RelativeLayout): Block {
        val ll = rl.getChildAt(0) as LinearLayout
        val blockName = ll.transitionName
        //println (blockName)
        when (blockName) {
            "IntValue" -> {
                val valueOfOper = ll.getChildAt(0) as EditText
                val block = NumberValue("Int", "NumberValue", 6, valueOfOper.text.toString())
                return block
            }

            "BooleanValue" -> {
                val spinner = ll.getChildAt(0) as Spinner
                println("!!!!" + spinner.selectedItem.toString())
                val block =
                    BooleanValue("Boolean", "BooleanValue", 6, spinner.selectedItem.toString())
                return block
            }

            "StringValue" -> {
                val valueOfOper = ll.getChildAt(0) as EditText
                val block = StringValue("String", "StringValue", 6, valueOfOper.text.toString())
                return block
            }

            "IntVariable" -> {
                val spinner = ll.getChildAt(1) as Spinner
                val block =
                    NumberVariable("Int", "NumberVariable", 6, spinner.selectedItem.toString())
                return block
            }

            "StringVariable" -> {
                val spinner = ll.getChildAt(1) as Spinner
                val block =
                    StringVariable("String", "StringVariable", 6, spinner.selectedItem.toString())
                return block
            }

            "BooleanVariable" -> {
                val spinner = ll.getChildAt(1) as Spinner
                val block = BooleanVariable(
                    "Boolean",
                    "BooleanVariable",
                    6,
                    spinner.selectedItem.toString()
                )
                return block
            }

            "IntArray" -> {
                val spinner = ll.getChildAt(1) as Spinner
                val lOper = ll.getChildAt(2) as LinearLayout
                val indOper = lOper.getChildAt(0) as RelativeLayout
                val block = NumberArray(
                    "Int",
                    "NumberVariable",
                    6,
                    spinner.selectedItem.toString(),
                    createBlock(indOper)
                )
                return block
            }

            "StringArray" -> {
                return (createBlock(rl))
            }

            "BooleanArray" -> {
                return (createBlock(rl))
            }

            "Math" -> {
                //println("this is math")
                val lFirst = ll.getChildAt(0) as LinearLayout
                val mathFirst = lFirst.getChildAt(0) as RelativeLayout
                val spinner = ll.getChildAt(1) as Spinner
                val lSecond = ll.getChildAt(2) as LinearLayout
                val mathSecond = lSecond.getChildAt(0) as RelativeLayout
                val block = MathOperator(
                    "Int",
                    "MathOperator",
                    6,
                    createBlock(mathFirst),
                    createBlock(mathSecond),
                    spinner.selectedItem.toString()
                )
                return block
            }

            "Minus" -> {
                val lMin = ll.getChildAt(1) as LinearLayout
                val min = lMin.getChildAt(0) as RelativeLayout
                val block = Negativity("Int", "Negativity", 6, createBlock(min))
                return block
            }

            "ToStr" -> {
                val lToStr = ll.getChildAt(1) as LinearLayout
                val toStr = lToStr.getChildAt(0) as RelativeLayout
                val block = ToString("String", "ToString", 6, createBlock(toStr))
                return block
            }

            "Concat" -> {
                val lFirst = ll.getChildAt(0) as LinearLayout
                val concatFirst = lFirst.getChildAt(0) as RelativeLayout
                val lSecond = ll.getChildAt(2) as LinearLayout
                val concatSecond = lSecond.getChildAt(0) as RelativeLayout
                val block = Concat(
                    "String",
                    "Concat",
                    6,
                    createBlock(concatFirst),
                    createBlock(concatSecond)
                )
                return block
            }

            else -> {
                return NumberValue("Int", "NumberValue", 6, "0")
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
                view.setBackgroundResource(R.drawable.shape_for_body_picked)
                //println("Loc")
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                //println("Exit")
                view.setBackgroundResource(R.drawable.shape_for_body_not_picked)
                true
            }

            DragEvent.ACTION_DROP -> {
                //println("Drop")
                val item: ClipData.Item = event.clipData.getItemAt(0)
                val dragData = item.text
                //Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()
                val blockName = dragData.toString()
                val list = blockName.split(".")

                if (list[0] != "move") {
                    if (list.size == 1) {
                        val dest = view as LinearLayout
                        build(list[0], dest)
                    }
                } else {
                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)
                    val dest = view as ViewGroup
                    dest.addView(v)
                }
                view.setBackgroundResource(R.drawable.shape_for_body_not_picked)
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                true
            }

            else -> {
                //println("Noooo(")
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
                //Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()
                val blockName = dragData.toString()
                val list = blockName.split(".")

                if (list[0] != "move") {
                    val dest = view as LinearLayout
                    build(list[0], dest)
                } else {
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
        var checkIsAdded = false
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
                println("Loc")
                view.setBackgroundResource(R.drawable.shape_for_main_operator_picked)
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                val dest = view as LinearLayout
                if (dest.childCount == 0) {
                    dest.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                } else {
                    dest.setBackgroundResource(R.drawable.shape_for_main_operator)
                }
                //println("Exit")
                true
            }

            DragEvent.ACTION_DROP -> {
                //println("Drop")
                val item: ClipData.Item = event.clipData.getItemAt(0)
                val dragData = item.text
                //Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()
                val blockName = dragData.toString()
                val list = blockName.split(".")

                val dest = view as LinearLayout


                if (list[0] != "move") {
                    if (list[0] == "oper") {
                        val vCheck = event.localState as View
                        //println(vCheck.transitionName + " " + dest.transitionName)
                        if (vCheck.transitionName == dest.transitionName && dest.transitionName != "All") {
                            if (dest.childCount != 0) {
                                val v = dest.getChildAt(0) as View
                                val owner = v.parent as ViewGroup
                                owner.removeView(v)
                                //println("i cant do it")
                            }
                            //printToConsole("all is okay")
                            build(blockName, dest)

                            view.setBackgroundResource(R.drawable.shape_for_main_operator)
                        } else {
                            if (dest.transitionName == "All") {
                                if (dest.childCount != 0) {
                                    //dest.removeAllViews()
                                    val v = dest.getChildAt(0) as View
                                    val owner = v.parent as ViewGroup
                                    owner.removeView(v)
                                }
                                build(blockName, dest)
                                view.setBackgroundResource(R.drawable.shape_for_main_operator)
                            }
                        }
                        checkIsAdded = true

                    }
                } else {
                    if (list[1] == "oper") {
                        val v = event.localState as View
                        if (v.transitionName == dest.transitionName && dest.transitionName != "All") {
                            if (dest.childCount == 0) {
                                val owner = v.parent as ViewGroup
                                owner.removeView(v)
                                dest.addView(v)
                                owner.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                                dest.setBackgroundResource(R.drawable.shape_for_main_operator)
                            } else {
                                val owner = v.parent as ViewGroup
                                val destView = dest.getChildAt(0)
                                owner.removeView(v)
                                dest.removeView(destView)
                                owner.addView(destView)
                                dest.addView(v)


                                owner.setBackgroundResource(R.drawable.shape_for_main_operator)
                                dest.setBackgroundResource(R.drawable.shape_for_main_operator)
                            }
                            checkIsAdded = true
                        } else {
                            if (dest.transitionName == "All") {
                                if (dest.childCount == 0) {
                                    val owner = v.parent as ViewGroup
                                    owner.removeView(v)
                                    dest.addView(v)
                                    owner.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                                } else {
                                    val owner = v.parent as ViewGroup
                                    val destView = dest.getChildAt(0)
                                    owner.removeView(v)
                                    dest.removeView(destView)
                                    owner.addView(destView)
                                    dest.addView(v)


                                    owner.setBackgroundResource(R.drawable.shape_for_main_operator)
                                }
                                checkIsAdded = true
                                dest.setBackgroundResource(R.drawable.shape_for_main_operator)
                            }
                        }
                    } else {
                        dest.setBackgroundResource(R.drawable.shape_for_main_operator)
                    }
                }
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                if (!checkIsAdded) {
                    //println("drop loose")
                    val dest = view as LinearLayout
                    if (dest.childCount == 0) {
                        view.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    } else {
                        view.setBackgroundResource(R.drawable.shape_for_main_operator)
                    }
                }
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
                //Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()
                val str = dragData.toString()
                val list = str.split(".")
                //println(list)
                if (list[0] == "move") {
                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    if (list[1] == "Init") {
                        val varDelete = v.findViewById<EditText>(R.id.inputName).text.toString()
                        if (varDelete != "") {
                            varNames.removeAt(varNames.indexOf(varDelete))
                        }
                    } else if (list[1] == "oper") {
                        owner.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }
                    val ownerLL = owner as LinearLayout
                    //println("           " + ownerLL.childCount.toString())
                    if (ownerLL.childCount == 2) {
                        owner.setBackgroundResource(R.drawable.shape_for_body_empty)
                    } else {
                        owner.setBackgroundResource(R.drawable.shape_for_body_not_picked)
                    }
                    owner.removeView(v)
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


    private fun build(name: String, dest: LinearLayout) {
        var view = layoutInflater.inflate(R.layout.cout_block, null) as View
        val list = name.split(".")
        when (list[0]) {
            "Cout" -> {
                view = layoutInflater.inflate(R.layout.cout_block, null) as View
                val outputOper = view.findViewById<LinearLayout>(R.id.coutBlockOper)
                outputOper.setOnDragListener(dragListenerOperator)
                outputOper.setBackgroundResource(R.drawable.shape_for_main_operator_empty)

            }

            "Init" -> {
                view = layoutInflater.inflate(R.layout.initialization_block, null) as View
                val editText = view.findViewById<EditText>(R.id.inputName)
                editText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        varNameRightNow = s.toString()
                        //println("now var name is: " + varNameRightNow + ", but i (maybe) will change it")
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                    }

                    override fun afterTextChanged(s: Editable?) {
                        if (s.toString() != varNameRightNow) {
                            if (varNames.indexOf(varNameRightNow) != -1) {
                                varNames[varNames.indexOf(varNameRightNow)] = s.toString()
                                //println("i swap one var by another")
                            } else {
                                varNames += s.toString()
                                //println("i add new var")
                            }
                        }
                        //println("text was changed")
                    }
                })
            }

            "InitArray" -> {
                view = layoutInflater.inflate(R.layout.initialization_array_block, null) as View
                val editText = view.findViewById<EditText>(R.id.inputName)
                editText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        arrNameRightNow = s.toString()
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                    }

                    override fun afterTextChanged(s: Editable?) {
                        if (s.toString() != arrNameRightNow) {
                            if (arrNames.indexOf(arrNameRightNow) != -1) {
                                arrNames[arrNames.indexOf(arrNameRightNow)] = s.toString()
                                //println("i swap one var by another")
                            } else {
                                arrNames += s.toString()
                            }
                        }
                    }
                })
                val arrayOper = view.findViewById<LinearLayout>(R.id.arrayBlockOper)
                arrayOper.setOnDragListener(dragListenerOperator)
                arrayOper.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
            }

            "If" -> {
                view = layoutInflater.inflate(R.layout.if_block, null) as View
                val bodyIf = view.findViewById<LinearLayout>(R.id.ifBodyInPlt)
                bodyIf.setOnDragListener(dragListenerPlt)
                bodyIf.setBackgroundResource(R.drawable.shape_for_body_empty)
                val bodyElse = view.findViewById<LinearLayout>(R.id.elseBodyInPlt)
                bodyElse.setOnDragListener(dragListenerPlt)
                bodyElse.setBackgroundResource(R.drawable.shape_for_body_empty)
                val condOper = view.findViewById<LinearLayout>(R.id.ifCondOper)
                condOper.setOnDragListener(dragListenerOperator)
            }

            "While" -> {
                view = layoutInflater.inflate(R.layout.while_block, null) as View
                val bodyWhile = view.findViewById<LinearLayout>(R.id.whileBodyInPlt)
                bodyWhile.setOnDragListener(dragListenerPlt)
                bodyWhile.setBackgroundResource(R.drawable.shape_for_body_empty)
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

            "AssigArray" -> {
                view = layoutInflater.inflate(R.layout.assignment_array_block, null) as View
                val spinner = view.findViewById<Spinner>(R.id.nameSpinner)
                val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrNames)
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                with(spinner) {
                    adapter = aa
                }
                val assigIndOper = view.findViewById<LinearLayout>(R.id.assigIndOper)
                assigIndOper.setOnDragListener(dragListenerOperator)
                val assigOper = view.findViewById<LinearLayout>(R.id.assigOper)
                assigOper.setOnDragListener(dragListenerOperator)
            }

            "oper" -> {
                when (list[1]) {
                    "Math" -> {
                        view = layoutInflater.inflate(R.layout.math_oper, null) as View
                        val firstMath = view.findViewById<LinearLayout>(R.id.mathOperFirst)
                        firstMath.setOnDragListener(dragListenerOperator)
                        firstMath.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                        val secondMath = view.findViewById<LinearLayout>(R.id.mathOperSecond)
                        secondMath.setOnDragListener(dragListenerOperator)
                        secondMath.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }

                    "IndIntArray" -> {
                        view = layoutInflater.inflate(R.layout.array_ind_int_oper, null) as View
                        val spinner = view.findViewById<Spinner>(R.id.nameSpinner)
                        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrNames)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        with(spinner) {
                            adapter = aa
                        }
                        val indArr = view.findViewById<LinearLayout>(R.id.arrayInd)
                        indArr.setOnDragListener(dragListenerOperator)
                        indArr.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }

                    "IndStrArray" -> {
                        view = layoutInflater.inflate(R.layout.array_ind_string_oper, null) as View
                        val spinner = view.findViewById<Spinner>(R.id.nameSpinner)
                        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrNames)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        with(spinner) {
                            adapter = aa
                        }
                        val indArr = view.findViewById<LinearLayout>(R.id.arrayInd)
                        indArr.setOnDragListener(dragListenerOperator)
                        indArr.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }

                    "IndBoolArray" -> {
                        view = layoutInflater.inflate(R.layout.array_ind_bool_oper, null) as View
                        val spinner = view.findViewById<Spinner>(R.id.nameSpinner)
                        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrNames)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        with(spinner) {
                            adapter = aa
                        }
                        val indArr = view.findViewById<LinearLayout>(R.id.arrayInd)
                        indArr.setOnDragListener(dragListenerOperator)
                        indArr.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }

                    "Ireq" -> {
                        view = layoutInflater.inflate(R.layout.eq_oper, null) as View
                        val firstEq = view.findViewById<LinearLayout>(R.id.eqOperFirst)
                        firstEq.setOnDragListener(dragListenerOperator)
                        firstEq.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                        val secondEq = view.findViewById<LinearLayout>(R.id.eqOperSecond)
                        secondEq.setOnDragListener(dragListenerOperator)
                        secondEq.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }

                    "Concat" -> {
                        view = layoutInflater.inflate(R.layout.concat_oper, null) as View
                        val firstConcat = view.findViewById<LinearLayout>(R.id.concatOperFirst)
                        firstConcat.setOnDragListener(dragListenerOperator)
                        firstConcat.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                        val secondConcat = view.findViewById<LinearLayout>(R.id.concatOperSecond)
                        secondConcat.setOnDragListener(dragListenerOperator)
                        secondConcat.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }

                    "ToStr" -> {
                        view = layoutInflater.inflate(R.layout.to_str_oper, null) as View
                        val toStrOper = view.findViewById<LinearLayout>(R.id.toStrOper)
                        toStrOper.setOnDragListener(dragListenerOperator)
                        toStrOper.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }

                    "Logic" -> {
                        view = layoutInflater.inflate(R.layout.logic_oper, null) as View
                        val firstLogic = view.findViewById<LinearLayout>(R.id.logicOperFirst)
                        firstLogic.setOnDragListener(dragListenerOperator)
                        firstLogic.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                        val secondLogic = view.findViewById<LinearLayout>(R.id.logicOperSecond)
                        secondLogic.setOnDragListener(dragListenerOperator)
                    }

                    "Minus" -> {
                        view = layoutInflater.inflate(R.layout.minus_oper, null) as View
                        val minusOper = view.findViewById<LinearLayout>(R.id.minusOperFirst)
                        minusOper.setOnDragListener(dragListenerOperator)
                        minusOper.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
                    }

                    "Neg" -> {
                        view = layoutInflater.inflate(R.layout.neg_oper, null) as View
                        val negOper = view.findViewById<LinearLayout>(R.id.negOperFirst)
                        negOper.setOnDragListener(dragListenerOperator)
                        negOper.setBackgroundResource(R.drawable.shape_for_main_operator_empty)
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

    /*
    companion object {
        fun showForInput() : String {
            val builder = AlertDialog.Builder()
            builder.setTitle("Выбор есть всегда").setMessage("Выбери пищу")
            return "this could be in input"

        }
    }

     */


    /*
    fun sentBlockToConsole(messageType: Boolean, message: Message, isEnter : Boolean) {
        val intent = Intent(this, ConsoleActivity::class.java)
        intent.putExtra("data", message)
    }
     */

}


fun printToConsole(type: Boolean, included: String) {
    var message = ""
    if (type) {
        //println("Console: " + included)
        message = included
        //val intent = Intent(MainActivity.this, ConsoleActivity::class.java)

    } else {
        //println("Console Error: " + included)
        message = "Error: " + included
    }
    hashMapForOutputMessages[outputInd] = message
    hashMapForOutputTypes[outputInd] = type
    outputInd++
}


abstract class MainOperator(val type: String, val typeOfBlock: String, val id: Int) {
    open fun define(): Boolean {
        return false
    }

    open fun calculate(): Int {
        return 0
    }

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

    open fun takeValue(): String {
        return ""
    }
}

abstract class Block(type: String, typeOfBlock: String, id: Int) :
    MainOperator(type, typeOfBlock, id) {}

class MathOperator(
    type: String,
    typeOfBlock: String,
    id: Int,
    val first: Block,
    val second: Block,
    val typeOfOper: String
) : Block(type, typeOfBlock, id) {
    override fun calculate(): Int {
        var value1 = 0
        var value2 = 0
        var value3 = 0

        //println("       " + first.typeOfBlock)
        //println("       " + second.typeOfBlock)

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
                printToConsole(false, "value2 " + "Math operand Error")
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

class Negativity(type: String, typeOfBlock: String, id: Int, val first: Block) :
    Block(type, typeOfBlock, id) {
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

class Negation(type: String, typeOfBlock: String, id: Int, val first: MainOperator) :
    MainOperator(type, typeOfBlock, id) {
    override fun define(): Boolean {
        val value = first.define()
        return !value
    }
}

class Logic(
    type: String,
    typeOfBlock: String,
    id: Int,
    val first: MainOperator,
    val second: MainOperator,
    val typeOfLogic: String
) : MainOperator(type, typeOfBlock, id) {
    override fun define(): Boolean {
        var value = false
        val value1 = first.define()
        val value2 = second.define()

        //println("    " + value1.toString())
        //println("    " + value2.toString())

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

    override fun takeValue(): String {
        return define().toString()
    }
}

class LogicOperator(
    type: String,
    typeOfBlock: String,
    id: Int,
    val first: Block,
    val second: Block,
    val typeOfLogic: String
) : MainOperator(type, typeOfBlock, id) {
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
                printToConsole(false, "logic Math operand Error")
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
                if (value1 > value2) {
                    value = true
                }
            }

            "<" -> {
                if (value1 < value2) {
                    value = true
                }
            }

            ">=" -> {
                if (value1 >= value2) {
                    value = true
                }
            }

            "<=" -> {
                if (value1 <= value2) {
                    value = true
                }
            }

            "==" -> {
                if (value1 == value2) {
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

    override fun takeValue(): String {
        return define().toString()
    }
}

class ToString(type: String, typeOfBlock: String, id: Int, val first: Block) :
    Block(type, typeOfBlock, id) {
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

class Concat(type: String, typeOfBlock: String, id: Int, val first: Block, val second: Block) :
    Block(type, typeOfBlock, id) {
    override fun takeValue(): String {
        var str1 = ""
        var str2 = ""

        //println("       " + first.typeOfBlock)
        //println("       " + second.typeOfBlock)
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

            "ToString" -> {
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

            "ToString" -> {
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

abstract class Value(type: String, typeOfBlock: String, id: Int, var value: String) :
    Block(type, typeOfBlock, id) {}

class BooleanValue(type: String, typeOfBlock: String, id: Int, value: String) :
    Value(type, typeOfBlock, id, value) {
    override fun define(): Boolean {
        return value.toBoolean()
    }

    override fun takeValue(): String {
        return value
    }
}

class NumberValue(type: String, typeOfBlock: String, id: Int, value: String) :
    Value(type, typeOfBlock, id, value) {
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

class StringValue(type: String, typeOfBlock: String, id: Int, value: String) :
    Value(type, typeOfBlock, id, value) {
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

abstract class Variable(type: String, typeOfBlock: String, id: Int, val name: String) :
    Block(type, typeOfBlock, id) {}

class BooleanVariable(type: String, typeOfBlock: String, id: Int, name: String) :
    Variable(type, typeOfBlock, id, name) {
    override fun define(): Boolean {
        return hashMapOfVariableValues[name].toBoolean()
    }

    override fun takeValue(): String {
        return hashMapOfVariableValues[name].toString()
    }
}

class NumberVariable(type: String, typeOfBlock: String, id: Int, name: String) :
    Variable(type, typeOfBlock, id, name) {
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

class StringVariable(type: String, typeOfBlock: String, id: Int, name: String) :
    Variable(type, typeOfBlock, id, name) {
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

class NumberArray(type: String, typeOfBlock: String, id: Int, val name: String, val ind: Block) :
    Block(type, typeOfBlock, id) {
    override fun define(): Boolean {
        val a = hashMapOfArrayValues[name]
        val valueForDefine = a?.get(ind.takeValue().toInt()).toString().toInt()
        when (valueForDefine) {
            0 -> return false
            else -> return true
        }
    }

    override fun takeValue(): String {
        val indToTake = ind.takeValue().toInt()
        if (indToTake >= hashMapOfArraySize[name].toString().toInt()) {
            printToConsole(false, "ind out of range")
            return "-1"
        } else {
            val a = hashMapOfArrayValues[name]
            return a?.get(indToTake).toString()
        }
    }
}

class BooleanArray(type: String, typeOfBlock: String, id: Int, val name: String, val ind: Block) :
    Block(type, typeOfBlock, id) {
    override fun define(): Boolean {
        val a = hashMapOfArrayValues[name]
        return a?.get(ind.takeValue().toInt()).toBoolean()
    }

    override fun takeValue(): String {
        val indToTake = ind.takeValue().toInt()
        if (indToTake >= hashMapOfArraySize[name].toString().toInt()) {
            printToConsole(false, "ind out of range")
            return "false"
        } else {
            val a = hashMapOfArrayValues[name]
            return a?.get(indToTake).toString()
        }
    }
}

class StringArray(type: String, typeOfBlock: String, id: Int, val name: String, val ind: Block) :
    Block(type, typeOfBlock, id) {
    override fun define(): Boolean {
        val a = hashMapOfArrayValues[name]
        val valueForDefine = a?.get(ind.takeValue().toInt()).toString()
        when (valueForDefine) {
            "" -> return false
            else -> return true
        }
    }

    override fun takeValue(): String {
        val indToTake = ind.takeValue().toInt()
        if (indToTake >= hashMapOfArraySize[name].toString().toInt()) {
            printToConsole(false, "ind out of range")
            return ""
        } else {
            val a = hashMapOfArrayValues[name]
            return a?.get(indToTake).toString()
        }
    }
}


abstract class FunBlock(val type: String) {
    var bodyOfBlock = Body()
    var secondBodyOfBlock = Body()
    open fun checkCond() {}
    open fun doOutput() {}
    open fun createVariable() {}
    open fun checkTypes() {}
}

class Output(type: String, var messageType: Boolean, var included: MainOperator) : FunBlock(type) {
    override fun doOutput() {
        printToConsole(messageType, included.valueToString())
    }
}

class IfBlock(type: String, var cond: MainOperator, var haveElse: Boolean) : FunBlock(type) {
    override fun checkCond() {
        if (cond.define()) {
            bodyOfBlock.doBody()
        } else {
            if (haveElse) {
                secondBodyOfBlock.doBody()
            }
        }
    }

}

class WhileBlock(type: String, var cond: MainOperator) : FunBlock(type) {
    override fun checkCond() {
        while (cond.define()) {
            bodyOfBlock.doBody()
        }
    }
}

class Init(type: String, var name: String, var typeOfVariable: String) : FunBlock(type) {
    override fun createVariable() {
        //printToConsole(true, "I create variable with name $name and type $typeOfVariable")
        //println(hashMapOfVariableValues)
        when (typeOfVariable) {
            "Int" -> {
                hashMapOfVariableValues.put(name, "0")
                hashMapOfVariableTypes.put(name, "Int")
            }

            "String" -> {
                hashMapOfVariableValues.put(name, "")
                hashMapOfVariableTypes.put(name, "String")
            }

            "Boolean" -> {
                hashMapOfVariableValues.put(name, "false")
                hashMapOfVariableTypes.put(name, "Boolean")
            }

            else -> {
                printToConsole(false, "Type Error")
            }
        }
        //println(hashMapOfVariableValues)
    }
}

class InitArray(type: String, var name: String, var typeOfVariable: String, var sizeBlock: Block) :
    FunBlock(type) {
    override fun createVariable() {
        if (sizeBlock.type == "Int") {
            val size = sizeBlock.takeValue().toInt()
            //printToConsole(true, "I create variable with name $name, type $typeOfVariable and size $size")
            when (typeOfVariable) {
                "Int" -> {
                    val hashMapV: HashMap<Int, String> = HashMap<Int, String>()
                    for (i in size downTo 0 step 1) {
                        hashMapV.put(i, "0")
                    }
                    hashMapOfArrayValues.put(name, hashMapV)
                    hashMapOfArrayTypes.put(name, typeOfVariable)
                    hashMapOfArraySize.put(name, size)
                }

                "String" -> {
                    val hashMapV: HashMap<Int, String> = HashMap<Int, String>()
                    for (i in size downTo 0 step 1) {
                        hashMapV.put(i, "")
                    }
                    hashMapOfArrayValues.put(name, hashMapV)
                    hashMapOfArrayTypes.put(name, typeOfVariable)
                    hashMapOfArraySize.put(name, size)
                }

                "Boolean" -> {
                    val hashMapV: HashMap<Int, String> = HashMap<Int, String>()
                    for (i in size downTo 0 step 1) {
                        hashMapV.put(i, "False")
                    }
                    hashMapOfArrayValues.put(name, hashMapV)
                    hashMapOfArrayTypes.put(name, typeOfVariable)
                    hashMapOfArraySize.put(name, size)
                }

                else -> {
                    printToConsole(false, "Type Of Array Error")
                }
            }
            //printToConsole(false, "i create array $name")
        } else {
            printToConsole(false, "size must to be Int")
        }
        //println(hashMapOfArray)
    }
}

class Assignment(type: String, var name: String, var second: MainOperator) : FunBlock(type) {
    override fun checkTypes() {
        //printToConsole(true, "I am going to put ${second.takeValue()} in $name variable")
        if (second.type == hashMapOfVariableTypes[name]) {
            //printToConsole("Types are matched " + second.takeValue())
            hashMapOfVariableValues[name] = second.takeValue()
            //println(hashMapOfVariableValues)
        } else {
            printToConsole(false, "Types are not matched")
            //println(second.type)
            //println(hashMapOfVariableTypes[name])
        }
    }
}

class Input(type: String, var name: String) : FunBlock(type) {


    override fun checkTypes() {
        //val ans = MainActivity.showForInput()
        //printToConsole(true, ans)
    }
}

class ArrayAssignment(
    type: String,
    var name: String,
    val indBlock: Block,
    var variable: MainOperator
) : FunBlock(type) {
    override fun checkTypes() {
        if (indBlock.type == "Int") {
            val ind = indBlock.takeValue().toInt()
            if (variable.type == hashMapOfArrayTypes[name]) {
                hashMapOfArrayValues[name]?.set(ind, variable.takeValue())
            } else {
                printToConsole(false, "Types are not matched with $name Array")
            }
        } else {
            printToConsole(false, "ind must to be Int")
        }
    }
}

class ArraySwap(
    type: String,
    val name1: String,
    val blockInd1: Block,
    val name2: String,
    val blockInd2: Block
) : FunBlock(type) {
    override fun checkCond() {
        val ind1 = blockInd1.takeValue().toInt()
        val ind2 = blockInd2.takeValue().toInt()
        if (ind1 < hashMapOfArraySize[name1].toString().toInt()) {
            if (ind2 < hashMapOfArraySize[name2].toString().toInt()) {
                if (hashMapOfArrayTypes[name1] == hashMapOfArrayTypes[name2]) {
                    var a = StringArray(
                        hashMapOfArrayTypes[name1].toString(),
                        "Array",
                        0,
                        name1,
                        blockInd1
                    ).takeValue()
                    when (hashMapOfArrayTypes[name1]) {
                        "Int" -> {
                            a = NumberArray(
                                hashMapOfArrayTypes[name1].toString(),
                                "Array",
                                0,
                                name1,
                                blockInd1
                            ).takeValue()
                        }

                        "String" -> {
                            a = StringArray(
                                hashMapOfArrayTypes[name1].toString(),
                                "Array",
                                0,
                                name1,
                                blockInd1
                            ).takeValue()
                        }

                        "Boolean" -> {
                            a = BooleanArray(
                                hashMapOfArrayTypes[name1].toString(),
                                "Array",
                                0,
                                name1,
                                blockInd1
                            ).takeValue()
                        }
                    }
                    var b = StringArray(
                        hashMapOfArrayTypes[name1].toString(),
                        "Array",
                        0,
                        name2,
                        blockInd2
                    ).takeValue()
                    when (hashMapOfArrayTypes[name1]) {
                        "Int" -> {
                            b = NumberArray(
                                hashMapOfArrayTypes[name1].toString(),
                                "Array",
                                0,
                                name2,
                                blockInd2
                            ).takeValue()
                        }

                        "String" -> {
                            b = StringArray(
                                hashMapOfArrayTypes[name1].toString(),
                                "Array",
                                0,
                                name2,
                                blockInd2
                            ).takeValue()
                        }

                        "Boolean" -> {
                            b = BooleanArray(
                                hashMapOfArrayTypes[name1].toString(),
                                "Array",
                                0,
                                name2,
                                blockInd2
                            ).takeValue()
                        }
                    }
                    hashMapOfArrayValues[name1]?.set(ind1, b)
                    hashMapOfArrayValues[name2]?.set(ind2, a)
                } else {
                    printToConsole(false, "$name1 and $name2 Arrays Have Different Types")
                }
            } else {
                printToConsole(false, "Out Of Range In $name2 Array")
            }
        } else {
            printToConsole(false, "Out Of Range In $name1 Array")
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
                "arrayAssignment" -> i.checkTypes()
                "while" -> i.checkCond()
                "input" -> i.checkTypes()
                "swap" -> i.checkCond()
                "arrayInitialization" -> i.createVariable()
            }
        }
    }
}