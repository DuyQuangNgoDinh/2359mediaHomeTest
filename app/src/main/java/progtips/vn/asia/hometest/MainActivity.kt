package progtips.vn.asia.hometest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import progtips.vn.asia.hometest.ui.fragments.NowPlayingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateTo(NowPlayingFragment())
    }

    /**
     * Navigate to the given fragment.
     *
     * @param fragment       Fragment to navigate to.
     * @param addToBackstack Whether or not the current fragment should be added to the backstack, default is NOT
     */
    fun navigateTo(fragment: Fragment, addToBackstack: Boolean = false) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackstack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}
