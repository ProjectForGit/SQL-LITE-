package com.example.sqlite

import android.R.id
import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var peopleDB: DatabaseHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        peopleDB = DatabaseHelper(this@MainActivity)

        add.setOnClickListener()
        {
            addUser()
        }

        show.setOnClickListener()
        {
            showUsers()
        }

        update.setOnClickListener()
        {
            Update();
        }
        delete.setOnClickListener()
        {
            Delete();
        }

    }

    private fun addUser() {
        val name: String = edtName.text.toString()
        val email: String = edtEmail.text.toString()

        val insertData: Boolean = peopleDB!!.addData(name, email)

        if (insertData == true) {
            Toast.makeText(this@MainActivity, "Запись добавлена", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@MainActivity, "Что-то пошло не так:(", Toast.LENGTH_LONG).show()
        }
    }

    private fun showUsers() {
        val data: Cursor? = peopleDB!!.showData()

        if (data!!.count == 0) {
            display("Ошибка", "Данные отсутствуют")
            return
        }
        val buffer = StringBuffer()
        while (data.moveToNext()) {
            buffer.append("ID:" + data.getString(0) + "\n")
            buffer.append("Name:" + data.getString(1) + "\n")
            buffer.append("Email:" + data.getString(2) + "\n")

            display("Все пользователи", buffer.toString())
        }
    }

    fun display(title: String?, message: String?) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()

    }

    fun Update() {
//        val name: String = edtName.text.toString()
//        val email: String = edtEmail.text.toString()
//        val contentValues = ContentValues()
//
//        contentValues.put(peopleDB?.Table_Name, name )
//        contentValues.put(peopleDB?.Table_Name, email)
//        val updCount: Int = peopleDB.update(
//            DatabaseHelper.TABLE_CONTACTS,
//            contentValues,
//            D.KEY_ID.toString() + "= ?",
//            arrayOf<String>(id)
//        )
//
//        Log.d("mLog", "updates rows count = $updCount")

    }

    fun Delete()
    {

    }
}