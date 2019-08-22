package progtips.vn.asia.hometest.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MovieGridItemDecoration(private val smallPadding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = smallPadding
        outRect.right = smallPadding
        outRect.top = smallPadding
        outRect.bottom = smallPadding
    }
}
