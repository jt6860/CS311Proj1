package com.example.cs311_u2_johntorres

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    // Initialize field and button variables.
    private var submitBtn: Button? = null
    private var customerNameField: EditText? = null
    private var customerIDField: EditText? = null
    private var customerAddressField: EditText? = null

    // Validity flag for field validation.
    private var isValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Launch first page layout.
        setContentView(R.layout.activity_main)

        // Pair field variables with layout IDs.
        customerNameField = findViewById(R.id.customerNameInput)
        customerIDField = findViewById(R.id.customerIDInput)
        customerAddressField = findViewById(R.id.customerAddressInput)
        submitBtn = findViewById(R.id.submit)

        // Submit button listener
        submitBtn?.setOnClickListener {

            // Runs validation.
            isValid = validateFields()

            // If valid, advance to next activity.
            if (isValid) {
                val i = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(i)
            }
        }
    }

    // Field Validation
    private fun validateFields(): Boolean {
        // Customer Name
        // Check for entry.
        if (customerNameField!!.length() == 0) {
            customerNameField!!.error = "Customer Name is required"
            return false
        }
        // Checks for number in Customer Name.
        else if(customerNameField!!.text.matches(".*[0-9].*".toRegex())){
            customerNameField!!.error = "Customer Name must not contain number"
            return false
        }

        // Customer ID
        // Set local variables and cast accordingly for int comparison.

        // Check for entry.
        if (customerIDField!!.length() == 0) {
            customerIDField!!.error = "Customer ID is required"
            return false
        }
        val customerIDFieldString = customerIDField?.text.toString()
        val customerIDFieldEntry = customerIDFieldString.toInt()
        // Check ID is between 1 and 999.
        if (customerIDFieldEntry <= 0 || customerIDFieldEntry > 999) {
            customerIDField!!.error = "Customer ID must be between 1 and 999"
            return false
        }

        // Customer Address
        // Check for entry.
        if (customerAddressField!!.length() == 0) {
            customerAddressField!!.error = "Customer Address is required"
            return false
        }

        // If valid, return true.
        return true
    }
}