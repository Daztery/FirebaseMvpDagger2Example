package com.upc.monitoringwalkers.model

import android.os.Bundle

data class PointEntity(
    var id: String = "",
    var startedAt: String = "",
    var value: Int = 0
){
    companion object {
        fun from(bundle: Bundle): TherapistEntity {
            return TherapistEntity(
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        with(bundle) {
            putString("id", id)
        }
        return bundle
    }
}

data class ArrayPoint(
    var points: ArrayList<PointEntity>
)

/*fun PointEntity.mapToPointList(pointEntity: PointEntity) {
    var points : ArrayList<PointEntity> = ArrayList()
    points.add(pointEntity)

}*/
fun ArrayPoint.mapToPointList()=ArrayList<PointEntity>()

fun PointEntity.mapToPoint() = PointEntity(id, startedAt, value)
fun PointEntity.isValid() = startedAt.isNotBlank()
        && value.toString().isNotBlank()

fun ArrayPoint.isValidArray() = points.size!=0