package net.geidea.payment.usersData

data class UserData(
    val id: Int = 0,  // Autoincremented by the database
    val username: String,
    val password: String,
    val userType: String  // Could be string like "admin", "support", or "cashier"
)