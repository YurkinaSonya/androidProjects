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
        viewBlocks.setOnClickListener(){
            /*
            val myDialogFragment = MyDialogFragment()
            val manager = supportFragmentManager
            //myDialogFragment.show(manager, "wtf")
            val transaction: FragmentTransaction = manager.beginTransaction()
            myDialogFragment.show(transaction, "dialog")
             */
            val params = viewBlocks.layoutParams
            val paramsList = listBlocks.layoutParams
            if(!listIsOpen) {
                params.width = 1000
                paramsList.width = ViewGroup.LayoutParams.WRAP_CONTENT
                println("yep")
            }
            else {
                params.width = 300
                paramsList.width = 0
                println("nope")
            }
            viewBlocks.setLayoutParams(params);
            listBlocks.setLayoutParams(paramsList);
            listIsOpen =!listIsOpen
        }
        var bufIsOpen = false
        val bufBlocks = binding.buf
        val listBuf = binding.listForBuf
        bufBlocks.setOnClickListener(){
            val params = bufBlocks.layoutParams
            val paramsList = listBuf.layoutParams
            if(!bufIsOpen) {
                params.width = 1000
                paramsList.width = ViewGroup.LayoutParams.WRAP_CONTENT
                println("yep")
            }
            else {
                params.width = 300
                paramsList.width = 0
                println("nope")
            }
            bufBlocks.setLayoutParams(params);
            listBuf.setLayoutParams(paramsList);
            bufIsOpen =!bufIsOpen
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
        //val bodyInsidesTs = mutableListOf<FunBlock>()
        //val ifBlockTest = IfBlock("if", true)
        //bodyInsidesTs.add(SpareOutputBlock("output", "first"))
        println("I create main body")
        mainBody.bodyInsides.add(SpareOutputBlock("output", "first"))
        mainBody.bodyInsides.add(SpareOutputBlock("output", "second"))
        mainBody.bodyInsides.add(IfBlock("if", true))
        println("I add first part to main body")
        mainBody.bodyInsides[2].bodyOfBlock.bodyInsides.add(SpareOutputBlock("output", "firstInIf"))
        mainBody.bodyInsides[2].bodyOfBlock.bodyInsides.add(SpareOutputBlock("output", "firstInSecond"))
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

    val dragListener = View.OnDragListener() {view, event ->
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
                when(event.result) {
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
//i write smth


abstract class FunBlock (val type : String) {
    val bodyOfBlock = Body()
    open fun checkCond() {}
    open fun doOutput() {}
}

class SpareOutputBlock (type : String, private val included : String) : FunBlock(type) {
    override fun doOutput () {
        println(included)
    }
}

class IfBlock (type : String, var cond : Boolean) : FunBlock(type) {
    override fun checkCond() {
        if (cond) {
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