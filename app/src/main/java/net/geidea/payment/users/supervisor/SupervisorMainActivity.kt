package net.geidea.payment.users.supervisor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import net.geidea.payment.R
import net.geidea.payment.databinding.ActivityAdminManageSupportBinding
import net.geidea.payment.databinding.ActivitySupervisorMainBinding
import net.geidea.payment.databinding.NavHeaderBinding
import net.geidea.payment.users.admin.AdminManageSupportActivity

class SupervisorMainActivity : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivitySupervisorMainBinding
    private val TAG = "SupervisorManageCashierActivity" // For logging purposes
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle // For managing the drawer layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", TAG)
        // Initialize view binding
        // Inflate the layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_supervisor_main)

        // Setup ActionBarDrawerToggle for navigation drawer
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.supervisorDrawerLayout, R.string.open, R.string.close
        )
        binding.supervisorDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Set up toolbar navigation icon click listener
        // Set up toolbar navigation icon click listener
        binding.toolbar.navIcon?.setOnClickListener {
            binding.supervisorDrawerLayout.openDrawer(GravityCompat.START)
        }

        navItemClickListener()
        setUpCardViewListeners()

        // Update the navigation header
        updateNavHeader("Username: Ahadu Supervisor", "User Role: Supervisor")
    }

    private fun navItemClickListener() {
        // Set up navigation drawer interactions using binding
        binding.supervisorNavigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_manage_cashier -> {
                    // Handle navigation to Manage Cashier
                }
                R.id.nav_home -> {
                    // Handle navigation to Config Terminal
                }
                R.id.nav_terminal_info -> {
                    // Handle navigation to Terminal Info
                }
                R.id.nav_settings -> {
                    // Handle navigation to Settings
                }
                R.id.nav_help -> {
                    // Handle navigation to Summary Report
                }
                R.id.nav_logout -> {
                    // Handle navigation to Reprint
                }
            }
            binding.supervisorDrawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setUpCardViewListeners() {
        // Set up onClickListeners for CardViews using binding
        binding.supervisorManageCashier.setOnClickListener {
            val intent = Intent(this@SupervisorMainActivity, SupervisorManageCashierActivity::class.java)
            startActivity(intent)
        }

        binding.supervisorConfigTerminal.setOnClickListener {
            // Handle Config Terminal click
        }

        binding.supervisorTerminalInfo.setOnClickListener {
            // Handle Terminal Info click
        }

        binding.supervisorSettings.setOnClickListener {
            // Handle Settings click
        }

        binding.supervisorSummaryReport.setOnClickListener {
            // Handle Summary Report click
        }

        binding.supervisorReprint.setOnClickListener {
            // Handle Reprint click
        }
    }

    // Method to update the Navigation Header Texts
    private fun updateNavHeader(username: String, userrole: String) {
        // Get the navigation view binding for the header
        val headerBinding = NavHeaderBinding.bind(binding.supervisorNavigationView.getHeaderView(0))

        // Update the username and userrole
        headerBinding.username.text = username
        headerBinding.userrole.text = userrole
    }
}
