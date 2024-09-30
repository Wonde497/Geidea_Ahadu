package net.geidea.payment.users.supervisor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import net.geidea.payment.R
import net.geidea.payment.databinding.ActivitySupervisorManageCashierBinding

class SupervisorManageCashierActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySupervisorManageCashierBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    companion object {
        private const val TAG = "SupervisorManageCashierActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_supervisor_manage_cashier)

        setupDrawer()
        setupToolbar()
        setupNavigationClickListener()
        setupCardViewListeners()
    }

    private fun setupDrawer() {
        // Initialize the ActionBarDrawerToggle and sync state
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.supervisorDrawerLayout, R.string.open, R.string.close
        )
        binding.supervisorDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun setupToolbar() {
        // Set the toolbar navigation icon listener
        binding.toolbar.navIcon?.setOnClickListener {
            binding.supervisorDrawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun setupNavigationClickListener() {
        binding.supervisorNavigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle item click and close the drawer
            binding.supervisorDrawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.nav_manage_cashier -> navigateToManageCashier()
                R.id.nav_home -> navigateToHome()
                R.id.nav_terminal_info -> navigateToTerminalInfo()
                R.id.nav_settings -> navigateToSettings()
                R.id.nav_help -> navigateToHelp()
                R.id.nav_logout -> handleLogout()
                else -> Log.w(TAG, "Unknown navigation item selected: ${menuItem.itemId}")
            }
            true
        }
    }

    private fun setupCardViewListeners() {
        // Handling click events for the card views
        binding.supervisorAddCashier.setOnClickListener {
            handleAddCashier()
        }
        binding.supervisorViewCashier.setOnClickListener {
            handleViewCashier()
        }
        binding.supervisorBlockCashier.setOnClickListener {
            handleBlockCashier()
        }
        binding.supervisorEnableCashier.setOnClickListener {
            handleEnableCashier()
        }
        binding.supervisorChangeCashierPin.setOnClickListener {
            handleChangeCashierPin()
        }
        binding.deleteCashier.setOnClickListener {
            handleDeleteCashier()
        }
    }

    // Navigation methods
    private fun navigateToManageCashier() {
        Log.d(TAG, "Navigating to Manage Cashier")
        Snackbar.make(binding.root, "Navigating to Manage Cashier", Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToHome() {
        Log.d(TAG, "Navigating to Home")
        Snackbar.make(binding.root, "Navigating to Home", Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToTerminalInfo() {
        Log.d(TAG, "Navigating to Terminal Info")
        Snackbar.make(binding.root, "Navigating to Terminal Info", Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToSettings() {
        Log.d(TAG, "Navigating to Settings")
        Snackbar.make(binding.root, "Navigating to Settings", Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToHelp() {
        Log.d(TAG, "Navigating to Help")
        Snackbar.make(binding.root, "Navigating to Help", Snackbar.LENGTH_SHORT).show()
    }

    private fun handleLogout() {
        Log.d(TAG, "Logging out")
        Snackbar.make(binding.root, "Logging out...", Snackbar.LENGTH_SHORT).show()
        // Add actual logout logic here
    }

    // CardView Actions
    private fun handleAddCashier() {
        Log.d(TAG, "Add Cashier clicked")
        Snackbar.make(binding.root, "Adding new Cashier...", Snackbar.LENGTH_SHORT).show()
        // Add logic for adding cashier
    }

    private fun handleViewCashier() {
        Log.d(TAG, "View Cashier clicked")
        Snackbar.make(binding.root, "Fetching Cashier details...", Snackbar.LENGTH_SHORT).show()
        // Add logic for viewing cashier
    }

    private fun handleBlockCashier() {
        Log.d(TAG, "Block Cashier clicked")
        Snackbar.make(binding.root, "Blocking Cashier...", Snackbar.LENGTH_SHORT).show()
        // Add logic for blocking cashier
    }

    private fun handleEnableCashier() {
        Log.d(TAG, "Enable Cashier clicked")
        Snackbar.make(binding.root, "Enabling Cashier...", Snackbar.LENGTH_SHORT).show()
        // Add logic for enabling cashier
    }

    private fun handleChangeCashierPin() {
        Log.d(TAG, "Change Cashier PIN clicked")
        Snackbar.make(binding.root, "Changing Cashier PIN...", Snackbar.LENGTH_SHORT).show()
        // Add logic for changing cashier PIN
    }

    private fun handleDeleteCashier() {
        Log.d(TAG, "Delete Cashier clicked")
        Snackbar.make(binding.root, "Deleting Cashier...", Snackbar.LENGTH_SHORT).show()
        // Add logic for deleting cashier
    }
}
