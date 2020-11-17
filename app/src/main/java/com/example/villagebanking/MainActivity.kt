package com.example.villagebanking

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.DragStartHelper
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_member_dialog.*
import kotlinx.android.synthetic.main.add_member_dialog.view.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.drawer_toolbar.*
import java.text.FieldPosition
import java.util.zip.Inflater

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var homeFragment: HomeFragment
    lateinit var membersFragment: MembersFragment
    var selectedRole: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Village Banking"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(this,
            drawer_layout,
            toolbar,
            R.string.open,
            R.string.close){

        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigation_view.setNavigationItemSelectedListener(this)


        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> {
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.members -> {
                membersFragment = MembersFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, membersFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val addMemberDialogLayout = LayoutInflater.from(this).inflate(R.layout.add_member_dialog, null)
        when(item.itemId){
            R.id.addMember -> {

                membersFragment = MembersFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, membersFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                val addMemberDialog = AlertDialog.Builder(this).setView(addMemberDialogLayout)
                    .setTitle("Membership Registration")
                val showAddMemberDialog = addMemberDialog.show()

                val role = arrayOf(
                    "SELECT ROLE...",
                    "Chairperson",
                    "Vice Chairperson",
                    "Secretary",
                    "Money Counter 1",
                    "Money Counter 2"
                )
                val arrayAdapter =
                    ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, role)
                addMemberDialogLayout.spPosition.adapter = arrayAdapter

                addMemberDialogLayout.spPosition.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectedRole = role[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            }
        }

    addMemberDialogLayout.btnRegisterMember.setOnClickListener {

        var helper = MyDBHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase

    var cv = ContentValues()
        cv.put("names", addMemberDialogLayout.etFullName.text.toString())
        cv.put("contact_number", addMemberDialogLayout.etContactNo.text.toString())
        cv.put("account_info", addMemberDialogLayout.etAccountInfo.text.toString())
        cv.put("role", selectedRole)
        db.insert("members",null,cv)
    }




        return true
    }





}


