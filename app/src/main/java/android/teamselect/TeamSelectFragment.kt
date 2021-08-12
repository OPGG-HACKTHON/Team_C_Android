package android.teamselect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentTeamSelectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamSelectFragment : BaseFragment<FragmentTeamSelectBinding>(R.layout.fragment_team_select) {
    override fun initViews() {
    }
}