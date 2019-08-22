package progtips.vn.asia.hometest.ui.fragments

import android.content.Context
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import androidx.recyclerview.widget.RecyclerView
import android.util.TypedValue
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager


class GridAutofitLayoutManager : GridLayoutManager {
    private var mColumnWidth: Int = 0
    private var mColumnWidthChanged = true

    constructor(context: Context, columnWidth: Int) : super(context, 1) {
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }

    constructor(context: Context, columnWidth: Int, orientation: Int, reverseLayout: Boolean) : super(
        context,
        1,
        orientation,
        reverseLayout
    ) { /* Initially set spanCount to 1, will be changed automatically later. */
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }

    private fun checkedColumnWidth(context: Context, columnWidth: Int): Int {
        var columnWidth = columnWidth
        if (columnWidth <= 0) { /* Set default columnWidth value (48dp here). It is better to move this constant to static constant on top, but we need context to convert it to dp, so can't really do so. */
            columnWidth =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150f, context.resources.displayMetrics).toInt()
        }
        return columnWidth
    }

    fun setColumnWidth(newColumnWidth: Int) {
        if (newColumnWidth > 0 && newColumnWidth != mColumnWidth) {
            mColumnWidth = newColumnWidth
            mColumnWidthChanged = true
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        if (mColumnWidthChanged && mColumnWidth > 0) {
            val totalSpace: Int
            if (orientation == LinearLayoutManager.VERTICAL) {
                totalSpace = width - paddingRight - paddingLeft
            } else {
                totalSpace = height - paddingTop - paddingBottom
            }
            val spanCount = Math.max(1, totalSpace / mColumnWidth)
            setSpanCount(spanCount)
            mColumnWidthChanged = false
        }
        super.onLayoutChildren(recycler, state)
    }
}