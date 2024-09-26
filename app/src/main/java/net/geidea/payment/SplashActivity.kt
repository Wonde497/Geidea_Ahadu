package net.geidea.payment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import net.geidea.payment.databinding.ActivitySplashBinding
import net.geidea.payment.kernelconfig.view.KernelConfigActivity
import net.geidea.payment.utils.IS_KERNEL_CONFIG_COMPLETED
import net.geidea.utils.DataStoreUtils
import net.geidea.utils.extension.ioCoroutine


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var binding: ActivitySplashBinding
    private lateinit var dbHandler: DBHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.splashImage.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.splash_animation
            )
        )
        dbHandler=DBHandler(this)
        var isAdminLoggedIn=dbHandler.checkAdmin()
        var isTIDRegistered=dbHandler.checkTID()
        var isMIDRegistered=dbHandler.checkMID()
        var isMerchantNameRegistered=dbHandler.checkMerchantName()
        var isMerchantAddressRegisterd=dbHandler.checkMerchantAddress()
        sharedPreferences=getSharedPreferences("SHARED_DATA", Context.MODE_PRIVATE)
        editor=sharedPreferences.edit()

        ioCoroutine {
            delay(2000)
            val isKernelConfigured = DataStoreUtils.getInstance(this@SplashActivity).getBoolean(
                IS_KERNEL_CONFIG_COMPLETED
            ) ?: false
            if (isKernelConfigured) {
                if(isAdminLoggedIn){
                        if(isTIDRegistered){
                            if(isMIDRegistered){
                                if(isMerchantNameRegistered){
                                    if(isMerchantAddressRegisterd){
                                        if(sharedPreferences.getBoolean("BRANCH MODE",false)==true||sharedPreferences.getBoolean("MERCHANT MODE",false)==true){
                                            if(sharedPreferences.getString("Currency","")=="ETB"||sharedPreferences.getString("Currency","")=="USD"||sharedPreferences.getString("Currency","")=="EURO"){
                                                if(sharedPreferences.getBoolean("4G Data",false)==true||sharedPreferences.getBoolean("WiFi",false)==true){
                                                    val intent = Intent(this@SplashActivity, MainMenuActivity::class.java)
                                                    startActivity(intent)
                                                    finish()
                                                }else{
                                                    val intent = Intent(this@SplashActivity, RegisterCommunicationMode::class.java)
                                                    startActivity(intent)
                                                    finish()
                                                }

                                            }else{
                                                val intent = Intent(this@SplashActivity, RegisterCurrency::class.java)
                                                startActivity(intent)
                                                finish()
                                            }

                                        }else{
                                            val intent = Intent(this@SplashActivity, RegisterTerminalMode::class.java)
                                            startActivity(intent)
                                            finish()
                                        }


                                    }else{
                                        val intent = Intent(this@SplashActivity, RegisterMerchantAddress::class.java)
                                        startActivity(intent)
                                        finish()
                                    }

                                }else{
                                    val intent = Intent(this@SplashActivity, RegisterMerchantName::class.java)
                                    startActivity(intent)
                                    finish()
                                }

                                  }else{
                               val intent = Intent(this@SplashActivity, RegisterMID::class.java)
                               startActivity(intent)
                               finish()
                                    }

                                   }else{
                                       val intent = Intent(this@SplashActivity, RegisterTID::class.java)
                                        startActivity(intent)
                                        finish()
                                           }



                              }else{
                                  val intent = Intent(this@SplashActivity, RegisterAdmin::class.java)
                              startActivity(intent)
                              finish()

                }
            } else {
                val intent=Intent(this,KernelConfigActivity::class.java)
                startActivity(intent)
            }
        }
    }
}