package xyz.lurkyphish2085.quizappdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import xyz.lurkyphish2085.quizappdemo.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private var _navController: NavController? = null

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val navController get() = _navController!!
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        _navController = NavHostFragment.findNavController(this)

        setupButtons()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupButtons() {
        binding.doneBtn.setOnClickListener {
            navController.navigate(R.id.action_resultFragment_to_mainMenuFragment)
        }
    }
}