package com.example.martinapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.martinapi.character.CharacterAdapter
import com.example.martinapi.character.CharactersViewModel
import com.example.martinapi.databinding.FragmentFirstBinding
import com.example.martinapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: CharactersViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //private val peopleAdapter = CharacterAdapter()
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        // adapter, layout
        adapter = CharacterAdapter(CharacterAdapter.OnClickListener { photo ->
            Toast.makeText(activity, "${photo.id}", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putInt("id", photo.id)
//            findNavController().navigate(
//                R.id.action_charactersFragment_to_characterDetailFragment,
//                bundleOf("${photo.id}" to 1)
//            )
            findNavController().navigate(
                R.id.action_charactersFragment_to_characterDetailFragment,
                bundle)

        })

        _binding?.charactersRv?.adapter = adapter
        _binding?.charactersRv?.layoutManager = LinearLayoutManager(activity)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.data?.get(0)?.name.toString(), Toast.LENGTH_SHORT).show()
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)

                    //if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

}