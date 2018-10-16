package hr.from.bkoruznjak.comet.main.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class CometFragment : Fragment(), ResourceIdProvider, TagProvider {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    override fun getNametag(): String? = this.javaClass.simpleName

    override fun getLayoutResourceId(): Int = throw IllegalStateException("Method not overriden in child")
}
