package net.geidea.payment.users.cashier

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import net.geidea.payment.R
import net.geidea.payment.databinding.ActivityCashierMainBinding
import net.geidea.payment.databinding.NavHeaderBinding
import net.geidea.payment.help.HelpMainActivity

@AndroidEntryPoint
class CashierMainActivity : AppCompatActivity() {
    private val TAG = "CashierMainActivity" // For logging purposes
    private lateinit var binding: ActivityCashierMainBinding // Binding object for layout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle // For managing the drawer layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", TAG)

        // Inflate the layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cashier_main)

        // Setup ActionBarDrawerToggle for navigation drawer
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.cashierDrawerLayout, R.string.open, R.string.close
        )
        binding.cashierDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Set up toolbar navigation icon click listener
        binding.toolbar.navIcon?.setOnClickListener {
            binding.cashierDrawerLayout.openDrawer(GravityCompat.START)
        }

        // Set up ItemClickListener for Navigation View
        navItemClickListener()

        // Set up OnClickListeners for CardViews
        setUpCardViewListeners()

        // Update the navigation header
        updateNavHeader("Username: Ahadu Cashier", "User Role: Cashier")
    }

    // Function to handle navigation item clicks
    private fun navItemClickListener() {
        binding.cashierNavigationView.setNavigationItemSelectedListener { menuItem ->
            binding.cashierDrawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    // val intent = Intent(this, SettingsActivity::class.java) // Update to SettingsActivity
                    // startActivity(intent)
                }
                R.id.nav_help -> {
                    Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HelpMainActivity::class.java)
                    startActivity(intent)
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
        binding.cashierKeyDownload.setOnClickListener {
            Toast.makeText(this, "Key Download clicked", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, KeyDownloadActivity::class.java)
            // startActivity(intent)
        }

        binding.cashierTerminalInfo.setOnClickListener {
            Toast.makeText(this, "Terminal Info clicked", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, TerminalInfoActivity::class.java)
            // startActivity(intent)
        }

        binding.cashierSummaryReport.setOnClickListener {
            Toast.makeText(this, "Summary Report clicked", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, SummaryReportActivity::class.java)
            // startActivity(intent)
        }

        binding.cashierReprint.setOnClickListener {
            Toast.makeText(this, "Reprint clicked", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, ReprintActivity::class.java)
            // startActivity(intent)
        }

        binding.cashierSettlement.setOnClickListener {
            Toast.makeText(this, "Settlement clicked", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, SettlementActivity::class.java)
            // startActivity(intent)
        }

        binding.cashierChangePassword.setOnClickListener {
            Toast.makeText(this, "Change Password clicked", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, ChangePasswordActivity::class.java)
            // startActivity(intent)
        }
    }
    // Method to update the Navigation Header Texts
    private fun updateNavHeader(username: String, userrole: String) {
        // Get the navigation view binding for the header
        val headerBinding = NavHeaderBinding.bind(binding.cashierNavigationView.getHeaderView(0))

        // Update the username and userrole
        headerBinding.username.text = username
        headerBinding.userrole.text = userrole
    }
}
