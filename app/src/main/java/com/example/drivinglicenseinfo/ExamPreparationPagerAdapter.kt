import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.drivinglicenseinfo.QuestionsFragment
import com.example.drivinglicenseinfo.TrafficSignsFragment

class ExamPreparationPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2  // Number of tabs (Questions and Traffic Signs)
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuestionsFragment()
            1 -> TrafficSignsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
