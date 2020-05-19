package com.upc.monitoringwalkers.model

data class PointEntity(
    var id: String = "",
    //var startedAt: String = "",
    var startedAt: Long=0,
    var value: Int = 0
){
    companion object {
        fun from(): TherapistEntity {
            return TherapistEntity(
            )
        }
    }

   /* fun toBundle(): Bundle {
        val bundle = Bundle()
        with(bundle) {
            putString("id", id)
        }
        return bundle
    }*/

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
fun PointEntity.isValid() = startedAt.toString().isNotBlank()
        && value.toString().isNotBlank()

fun ArrayPoint.isValidArray() = points.size!=0