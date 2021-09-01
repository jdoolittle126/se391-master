package edu.neit.jonathandoolittle.w6_lab_5

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.neit.jonathandoolittle.w6_lab_5.databinding.FragmentSecondBinding
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        // If we have argument (we should), then display them
        arguments?.let {
            val args = SecondFragmentArgs.fromBundle(it)
            binding.textViewDisplayFirstName.text = args.firstName
            binding.textViewDisplayLastName.text = args.lastName
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Sending the first name back to the home screen
        binding.buttonPrevious.setOnClickListener {
            val action = SecondFragmentDirections
                .actionSecondFragmentToFirstFragment(binding.textViewDisplayFirstName.text.toString())
            findNavController().navigate(action)
        }

        // I just found this cool, I was inspired by Canvas's confetti
        // The library can be found here: https://github.com/DanielMartinus/Konfetti
        binding.supriseView.post {
            binding.supriseView.build()
                .addColors(Color.BLUE, Color.RED, Color.CYAN, Color.YELLOW, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square, Shape.Circle)
                .addSizes(Size(12))
                .setPosition(binding.supriseView.width / 2f, binding.supriseView.height / 4f)
                .burst(50)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}