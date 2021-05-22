package com.android.bottomsheetSample

import android.os.Parcelable
import com.android.bottomsheetSample.subStackFragments.SimpleDataClass
import com.google.android.material.bottomsheet.BottomSheetBehavior

object DataManagement {

    var bottomSheetState : Int = BottomSheetBehavior.STATE_COLLAPSED

    var searchInstance = 0
    var favroutiesInstance = 0
    var notificationsInstance = 0
    var profileInstance = 0

    var exploreChapters = 0
    var onSearch = 0

    var mKistState : Parcelable? = null
    var isShowingSearch = false


    fun getDummyData() : ArrayList<SimpleDataClass>{
        val arrayList : ArrayList<SimpleDataClass> = ArrayList()
        for ( x in 1..250 ){
            arrayList.add(
                    SimpleDataClass(
                            title = "Title $x",
                            desp = "$x description"
                    )
            )
        }
        return arrayList
    }

}