package android.milestone.ui

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath
import kotlin.math.max
import kotlin.math.sqrt

class TopCurvedEdgeTreatment(
    var fabCradleMargin: Float,
    var fabCradleRoundedCornerRadius: Float,
    var cradleVerticalOffset: Float
) : EdgeTreatment() {

    var fabDiameter: Float = 0F
    var horizontalOffset: Float = 0F

    init {
        cradleVerticalOffset = max(0.0f, cradleVerticalOffset)
        this.horizontalOffset = 0.0f
    }

    override fun getEdgePath(length: Float, interpolation: Float, shapePath: ShapePath) {
        if (this.fabDiameter == 0.0f) {
            shapePath.lineTo(length, 0.0f)
            return
        }

        val cradleDiameter = this.fabCradleMargin * 2.0f + this.fabDiameter
        val cradleRadius = cradleDiameter / 2.0f
        val roundedCornerOffset = interpolation * this.fabCradleRoundedCornerRadius
        val middle = length / 2.0f + this.horizontalOffset
        val verticalOffset = interpolation * this.cradleVerticalOffset + (1.0f - interpolation) * cradleRadius
        val verticalOffsetRatio = verticalOffset / cradleRadius

        if (verticalOffsetRatio >= 1.0f) {
            shapePath.lineTo(length, 0.0f)
        } else {
            val distanceBetweenCenters = cradleRadius + roundedCornerOffset
            val distanceBetweenCentersSquared = distanceBetweenCenters * distanceBetweenCenters
            val distanceY = verticalOffset + roundedCornerOffset
            val distanceX = sqrt((distanceBetweenCentersSquared - distanceY * distanceY).toDouble()).toFloat()
            val leftRoundedCornerCircleX = middle - distanceX
            val rightRoundedCornerCircleX = middle + distanceX
            val cornerRadiusArcLength = Math.toDegrees(Math.atan((distanceX / distanceY).toDouble())).toFloat()
            val cutoutArcOffset = 90.0f - cornerRadiusArcLength

            shapePath.lineTo(leftRoundedCornerCircleX - roundedCornerOffset, 0.0f)
            shapePath.addArc(
                leftRoundedCornerCircleX - roundedCornerOffset,
                0.0f,
                leftRoundedCornerCircleX + roundedCornerOffset,
                roundedCornerOffset * 2.0f,
                270.0f,
                cornerRadiusArcLength
            )
            shapePath.addArc(
                middle - cradleRadius,
                -cradleRadius - verticalOffset,
                middle + cradleRadius,
                cradleRadius - verticalOffset,
                180.0f - cutoutArcOffset,
                cutoutArcOffset * 2.0f - 180.0f
            )
            shapePath.addArc(
                rightRoundedCornerCircleX - roundedCornerOffset,
                0.0f,
                rightRoundedCornerCircleX + roundedCornerOffset,
                roundedCornerOffset * 2.0f,
                270.0f - cornerRadiusArcLength,
                cornerRadiusArcLength
            )
            shapePath.lineTo(length, 0.0f)
        }
    }
}