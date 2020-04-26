package com.example.lab2_pizza
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle;
import android.view.View
import android.widget.*
import com.example.lab2_pizza.Fragments.IngredientsFragment
import com.example.lab2_pizza.Fragments.SizeFragment
import com.example.lab2_pizza.Fragments.TotalFragment
import com.example.lab2_pizza.Models.Pizza
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_ingredients.*
import kotlinx.android.synthetic.main.fragment_size.*


class MainActivity : AppCompatActivity(),
                        IngredientsFragment.OnIngredientsFragmentInteractionListener,
                            SizeFragment.OnSizeFragmentInteractionListener,
                                TotalFragment.OnTotalFragmentInteractionListener
{
    lateinit var pizza: Pizza
    lateinit var total: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pizza = Pizza()
        total = findViewById(R.id.total)
            val btnStartDeatilsActivity: Button = findViewById(R.id.okButton)
            btnStartDeatilsActivity.setOnClickListener {
                if(pizza.sizePrice!=0.0 && (pizza.totalPrice-pizza.sizePrice)!= 0.0) {
                    // val intent = Intent(this, DetailsActivity::class.java)
                    // intent.putExtra("pizza", pizza);
                    // startActivity(intent)
                    //Toast.makeText(this, "Total price" + pizza.totalPrice.toString(), Toast.LENGTH_SHORT).show()
                    val fragment = TotalFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,fragment).addToBackStack(null).commit()
                }
                else{
                    Toast.makeText(this, "You must choose size and at least 1 ingredient", Toast.LENGTH_SHORT).show()
                }
            }
    }

        fun sizeClicked(view: View) {
        if(size30.isChecked){
            pizza.sizePrice = 6.0
        }
        if(size40.isChecked){
            pizza.sizePrice = 10.0
        }
        if(size50.isChecked){
            pizza.sizePrice = 15.0
        }
        calcTotal()
        total.text="Total: " + pizza.totalPrice

    }

    fun ingredientsClicked(view: View) {
        when {
            mozzarellaCheck.isChecked -> pizza.mozzarellaCheesePrice = 1.0
            else -> pizza.mozzarellaCheesePrice = 0.0
        }

        when {
            mascarponeCheck.isChecked -> pizza.mascarponeCheesePrice = 1.0
            else -> pizza.mascarponeCheesePrice = 0.0
        }

        when {
            hamCheck.isChecked -> pizza.royalHamPrice = 2.0
            else -> pizza.royalHamPrice = 0.0
        }

        when {
            bavarianCheck.isChecked -> pizza.bavarianSalamiPrice = 1.0
            else -> pizza.bavarianSalamiPrice = 0.0
        }

        when {
            tomatoesCheck.isChecked -> pizza.freshTomatoesPrice = 1.0
            else -> pizza.freshTomatoesPrice = 0.0
        }

        when {
            baconCheck.isChecked -> pizza.baconPrice = 1.0
            else -> pizza.baconPrice = 0.0
        }

        when {
            champCheck.isChecked -> pizza.champignonsPrice = 1.0
            else -> pizza.champignonsPrice = 0.0
        }

        when {
            pepperoniCheck.isChecked -> pizza.pickledPepperoniPrice = 1.0
            else -> pizza.pickledPepperoniPrice = 0.0
        }
        calcTotal()
        total.text="Total: " + pizza.totalPrice
    }

    fun calcTotal(){
        pizza.totalPrice = pizza.sizePrice + pizza.pickledPepperoniPrice +
                pizza.champignonsPrice + pizza.baconPrice + pizza.freshTomatoesPrice +
                pizza.bavarianSalamiPrice + pizza.royalHamPrice + pizza.mascarponeCheesePrice +
                pizza.parmezanCheesePrice + pizza.mozzarellaCheesePrice

    }

    override fun onFragmentInteraction(uri: Uri) {
        (fragment as? IngredientsFragment)?.setOnFragmentInteractionListener(this)
        (fragment as? SizeFragment)?.setOnFragmentInteractionListener(this)
        (fragment as? TotalFragment)?.setOnFragmentInteractionListener(this)
    }

   /*
    fun itemClicked(v: View) { //code to check if this checkbox is checked!
        val checkBox = v as CheckBox
        if (checkBox.isChecked) {
            var name = checkBox.text
            Toast.makeText(this,"Checked" + checkBox.text.toString(),Toast.LENGTH_SHORT).show()
        }
    }
  */

}
