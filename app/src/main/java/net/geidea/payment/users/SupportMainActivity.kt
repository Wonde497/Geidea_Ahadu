package net.geidea.payment.users

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.pos.sdk.security.PedKcvInfo
import dagger.hilt.android.AndroidEntryPoint
import net.geidea.payment.R
import net.geidea.payment.databinding.ActivitySupportMainBinding
import net.geidea.payment.help.HelpMainActivity

@AndroidEntryPoint
class SupportMainActivity : AppCompatActivity() {
    private val TAG = "SupportMainActivity" // For logging purposes
    private lateinit var binding: ActivitySupportMainBinding // Binding object for layout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle // For managing the drawer layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", TAG)

        // Inflate the layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_support_main)

        // Setup ActionBarDrawerToggle for navigation drawer
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.supportDrawerLayout, R.string.open, R.string.close
        )
        binding.supportDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Set up toolbar navigation icon click listener
        binding.toolbar.navIcon?.setOnClickListener {
            binding.supportDrawerLayout.openDrawer(GravityCompat.START)
        }

        // Set up ItemClickListener for Navigation View
        navItemClickListener()

        // Set up OnClickListeners for CardViews
        setUpCardViewListeners()
    }

    // Function to handle navigation item clicks
    private fun navItemClickListener() {
        binding.supportNavigationView.setNavigationItemSelectedListener { menuItem ->
            binding.supportDrawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    //val intent = Intent(this, SettingsActivity::class.java) // Update to SettingsActivity
                    //startActivity(intent)
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
        binding.supportManageCashier.setOnClickListener {
            Toast.makeText(this, "Manage Cashier clicked", Toast.LENGTH_SHORT).show()
           // val intent = Intent(this, ManageCashierActivity::class.java)
          //  startActivity(intent)
        }

        binding.supportConfigTerminal.setOnClickListener {
            Toast.makeText(this, "Config Terminal clicked", Toast.LENGTH_SHORT).show()
           // val intent = Intent(this, ConfigTerminalActivity::class.java)
            //startActivity(intent)
        }

        binding.supportTerminalInfo.setOnClickListener {
            Toast.makeText(this, "Terminal Info clicked", Toast.LENGTH_SHORT).show()
           // val intent = Intent(this, TerminalInfoActivity::class.java)
           // startActivity(intent)
        }

        binding.supportSettlement.setOnClickListener {
            Toast.makeText(this, "Settlement clicked", Toast.LENGTH_SHORT).show()
            //val intent = Intent(this, SettlementActivity::class.java)
           // startActivity(intent)
        }

        binding.supportSummaryReport.setOnClickListener {
            Toast.makeText(this, "Summary Report clicked", Toast.LENGTH_SHORT).show()
            //val intent = Intent(this, SummaryReportActivity::class.java)
           // startActivity(intent)
        }

        binding.supportReprint.setOnClickListener {
            Toast.makeText(this, "Reprint clicked", Toast.LENGTH_SHORT).show()
            //val intent = Intent(this, ReprintActivity::class.java)
            //startActivity(intent)
        }

        binding.supportSettings.setOnClickListener {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
            //val intent = Intent(this, SettingsActivity::class.java)
            //startActivity(intent)
        }
    }
}
