package net.geidea.payment.login


import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.geidea.payment.users.admin.AdminMainActivity
import net.geidea.payment.users.cashier.CashierMainActivity
import net.geidea.payment.users.support.SupportMainActivity
import net.geidea.payment.databinding.ActivityLoginBinding
import net.geidea.payment.users.supervisor.SupervisorMainActivity

class LoginMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Populate user types in Spinner
        setupUserTypeSpinner()

        // Handle login button click
        binding.loginButton.setOnClickListener {
            handleLogin()
        }

        // Handle forgot password click
        binding.forgotPassword.setOnClickListener {
            handleForgotPassword()
        }
    }

    private fun setupUserTypeSpinner() {
        val userTypes = arrayOf("Admin", "Support","Supervisor", "Cashier")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, userTypes)
        binding.userTypeSpinner.adapter = adapter
    }

    private fun handleLogin() {
        val username = binding.usernameInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        val userType = binding.userTypeSpinner.selectedItem.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            return
        }

        // Hard coded login sample
        if (username == "admin" && password == "password" && userType == "Admin") {
            navigateToActivity(AdminMainActivity::class.java)
        } else if (username == "support" && password == "password" && userType == "Support") {
            navigateToActivity(SupportMainActivity::class.java)
        } else if (username == "supervisor" && password == "password" && userType == "Supervisor") {
            navigateToActivity(SupervisorMainActivity::class.java)
        } else if (username == "cashier" && password == "password" && userType == "Cashier") {
            navigateToActivity(CashierMainActivity::class.java)
        } else {
            Toast.makeText(this, "Invalid credentials or user type", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish() // Optional: finish the LoginActivity if you don't want to return to it
    }

    private fun handleForgotPassword() {
        // Handle forgot password action
        Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        // You can navigate to a Forgot Password activity here
    }
}
