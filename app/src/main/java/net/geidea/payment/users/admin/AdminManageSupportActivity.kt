package net.geidea.payment.users.admin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import net.geidea.payment.R
import net.geidea.payment.databinding.ActivityAdminManageSupportBinding

class AdminManageSupportActivity : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityAdminManageSupportBinding
    private val TAG = "AdminManageSupportActivity" // For logging purposes
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle // For managing the drawer layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TAG", TAG)

        // Inflate the layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_manage_support)

        // Setup ActionBarDrawerToggle for navigation drawer
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.adminDrawerLayout, R.string.open, R.string.close
        )
        binding.adminDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Set up toolbar navigation icon click listener
        // Set up toolbar navigation icon click listener
        binding.toolbar.navIcon?.setOnClickListener {
            binding.adminDrawerLayout.openDrawer(GravityCompat.START)
        }

        // Set up navigation drawer
        setupDrawer()

        navItemClickListener()
        setUpCardViewListeners()


    }


    private fun navItemClickListener() {
        binding.adminNavigationView.setNavigationItemSelectedListener { menuItem ->
            binding.adminDrawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_help -> {
                    Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_about -> {
                    Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_share -> {
                    Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_logout -> {
                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                }
                else -> false
            }
            true
        }
    }
    private fun setUpCardViewListeners() {
        // Set onClickListeners for each card
        binding.adminAddSupport.setOnClickListener {
            // Handle the 'Add Support' click event
        }

        binding.adminViewSupport.setOnClickListener {
            // Handle the 'View Support' click event
        }

        binding.adminBlockSupport.setOnClickListener {
            // Handle the 'Block Support' click event
        }

        binding.adminEnableSupport.setOnClickListener {
            // Handle the 'Enable Support' click event
        }

        binding.adminChangeSupportPin.setOnClickListener {
            // Handle the 'Change Support PIN' click event
        }

        binding.deleteSupport.setOnClickListener {
            // Handle the 'Delete Support' click event
        }
    }

    // Function to set up the navigation drawer
    private fun setupDrawer() {
        // Example of how to configure the drawer
        // Use binding.adminDrawerLayout and binding.adminNavigationView
        // Implement your drawer logic here
    }
}
