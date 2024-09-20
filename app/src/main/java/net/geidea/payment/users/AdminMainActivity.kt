package net.geidea.payment.users



import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.pos.sdk.security.PedKcvInfo
import dagger.hilt.android.AndroidEntryPoint
import net.geidea.payment.MainActivity
import net.geidea.payment.R
import net.geidea.payment.databinding.ActivityAdminMainBinding
import net.geidea.payment.help.HelpMainActivity
import net.geidea.payment.login.LoginMainActivity

@AndroidEntryPoint
class AdminMainActivity : AppCompatActivity() {
    private val TAG = "MainMenuActivity" // For logging purposes
    private lateinit var binding: ActivityAdminMainBinding // Binding object for layout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle // For managing the drawer layout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", TAG)

        // Inflate the layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_main)

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
        // Set up ItemClickListener for Navigation View
        navItemClickListener()
        // Set up OnClickListeners for CardViews
        setUpCardViewListeners()

    }

    // Function to handle navigation item clicks
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
        binding.adminManageTransaction.setOnClickListener {
            Toast.makeText(this, "Manage Transaction clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AdminMainActivity, LoginMainActivity::class.java)
            startActivity(intent)
        }

        binding.adminManageSupport.setOnClickListener {
            Toast.makeText(this, "Manage Support clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AdminMainActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.adminReceiveKey.setOnClickListener {
            Toast.makeText(this, "Receive Key clicked", Toast.LENGTH_SHORT).show()
        }

        binding.adminConfigTerminal.setOnClickListener {
            Toast.makeText(this, "Config Terminal clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AdminMainActivity, HelpMainActivity::class.java)
            startActivity(intent)
        }
    }

}
