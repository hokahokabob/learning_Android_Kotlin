package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentWordListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordListFragment : Fragment() {
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var letterId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //intentにアクセスするときはactivityを介して行う
        //追記：MainActivity.navControllerを利用するようになったので、intentはうまく取得できなくなった
        //    →onCreateでletterIdをargumentsから引いて設定するようにする
//        val letterId = activity?.intent?.extras?.getString(LETTER).toString()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WordAdapter(letterId, this.requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {//memo: letでラムダの呼び出し。argumentsがnullでない時（どっかから遷移してきた場合）だけ中が走る
            letterId = it.getString(LETTER).toString()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}