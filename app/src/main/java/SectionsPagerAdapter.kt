import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tugaspertemuan9.LoginFragment
import com.example.tugaspertemuan9.RegisterFragment

class SectionsPagerAdapter (activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LoginFragment()
            1 -> RegisterFragment()
            else -> throw  IllegalStateException ("Invalid position")
        }
    }
}