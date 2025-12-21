package com.androidtutorials.androidhelloworld

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SampleFragment : Fragment() {

    // Common Tag (same as Activity)
    private val COMMON_TAG = "CommonLifeCycle"

    // Individual Fragment Tag
    private val FRAGMENT_TAG = "FragmentLifeCycle"

    // 1. Called when fragment is attached to Activity
    // Context is available here
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(COMMON_TAG, "Fragment - onAttach")
        Log.d(FRAGMENT_TAG, "onAttach called")
    }

    // 2. Called to do non-UI initialization
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(COMMON_TAG, "Fragment - onCreate")
        Log.d(FRAGMENT_TAG, "onCreate called")
    }

    // 3. Called to create Fragment UI
    // Layout is inflated here
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(COMMON_TAG, "Fragment - onCreateView")
        Log.d(FRAGMENT_TAG, "onCreateView called")
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    // 4. Called after view is created
    // Initialize views & listeners
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(COMMON_TAG, "Fragment - onViewCreated")
        Log.d(FRAGMENT_TAG, "onViewCreated called")
    }

    // 5. Fragment becomes visible
    override fun onStart() {
        super.onStart()
        Log.d(COMMON_TAG, "Fragment - onStart")
        Log.d(FRAGMENT_TAG, "onStart called")
    }

    // 6. Fragment is active and user can interact
    override fun onResume() {
        super.onResume()
        Log.d(COMMON_TAG, "Fragment - onResume")
        Log.d(FRAGMENT_TAG, "onResume called")
    }

    // 7. Fragment partially visible
    // Pause animations, save data
    override fun onPause() {
        super.onPause()
        Log.d(COMMON_TAG, "Fragment - onPause")
        Log.d(FRAGMENT_TAG, "onPause called")
    }

    // 8. Fragment no longer visible
    override fun onStop() {
        super.onStop()
        Log.d(COMMON_TAG, "Fragment - onStop")
        Log.d(FRAGMENT_TAG, "onStop called")
    }

    // 9. Fragment view is destroyed
    // Clear view references to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(COMMON_TAG, "Fragment - onDestroyView")
        Log.d(FRAGMENT_TAG, "onDestroyView called")
    }

    // 10. Fragment is destroyed
    override fun onDestroy() {
        super.onDestroy()
        Log.d(COMMON_TAG, "Fragment - onDestroy")
        Log.d(FRAGMENT_TAG, "onDestroy called")
    }

    // 11. Fragment detached from Activity
    // Context is no longer available
    override fun onDetach() {
        super.onDetach()
        Log.d(COMMON_TAG, "Fragment - onDetach")
        Log.d(FRAGMENT_TAG, "onDetach called")
    }
}
