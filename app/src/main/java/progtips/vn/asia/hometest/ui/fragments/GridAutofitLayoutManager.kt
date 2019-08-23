package progtips.vn.asia.hometest.ui.fragments

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.TypedValue
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager


class GridAutofitLayoutManager (context: Context): GridLayoutManager(context, 1) {
    private var mColumnWidth: Int = 0
    private var mColumnWidthChanged = true
    private val DEFAULT_COL_WIDTH = 150f

    init {
        mColumnWidth = /* Set default columnWidth value (150dp here). It is better to move this constant to static constant on top, but we need context to convert it to dp, so can't really do so. */
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_COL_WIDTH, context.resources.displayMetrics).toInt()

        if (mColumnWidth > 0) {
            mColumnWidthChanged = true
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        if (mColumnWidthChanged && mColumnWidth > 0) {
            val totalSpace = when (orientation) {
                LinearLayoutManager.VERTICAL -> width - paddingRight - paddingLeft
                else -> height - paddingTop - paddingBottom
            }
            val spanCount = Math.max(1, totalSpace / mColumnWidth)
            setSpanCount(spanCount)
            mColumnWidthChanged = false
        }
        super.onLayoutChildren(recycler, state)
    }
}