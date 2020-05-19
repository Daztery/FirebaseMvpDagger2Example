package com.upc.monitoringwalkers.ui.therapists.profile.seePatientGraphics


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.graphicPatientPresenter
import com.upc.monitoringwalkers.model.*
import com.upc.monitoringwalkers.seeGraphicPatientFromTherapistPresenter
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.therapists.profile.seePatientGraphics.view.SeeGraphicPatientView
import kotlinx.android.synthetic.main.activity_patient_graphics.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SeeGraphicPatientActivity : BaseActivity(),
    SeeGraphicPatientView {


    private val presenter by lazy { seeGraphicPatientFromTherapistPresenter() }
    private lateinit var patientId: String

    private var arrayPointsForce= arrayListOf<PointEntity>()
    private var arrayPointsSpeed= arrayListOf<PointEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_graphics)
        presenter.setView(this)
        patientId = intent.extras!!.getString("patientId").toString()
        initUi()
    }

    private fun initUi() {

        presenter.viewReady(patientId)
        val optionsForce= arrayOf("Presión - Toda los datos","Presión - Última hora","Presión - Último día","Presión - Última semana")
        spinner_options.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optionsForce)

        val optionsSpeed= arrayOf("Velocidad - Toda los datos","Velocidad - Última hora","Velocidad - Último día","Velocidad - Última semana")
        spinner_options2.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optionsSpeed)

    }


    override fun onFetchGraphicForceSuccess(pointEntity: PointEntity) {

        patient_profile_progress.visibility = View.GONE
        patient_profile.visibility = View.VISIBLE
        scroll.visibility = View.VISIBLE

        getDataPointsForce(pointEntity)

        val graph = findViewById<GraphView>(R.id.graph_force)
        val series: LineGraphSeries<DataPoint> = LineGraphSeries()

        for(i in arrayPointsForce.indices) {
            series.appendData(
                DataPoint(Date(arrayPointsForce[i].startedAt), arrayPointsForce[i].value.toDouble()),
                true,
                arrayPointsForce.size
            )
        }
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.numHorizontalLabels = 5
        graph.title="Presion"
        graph.addSeries(series)

        spinner_options.onItemSelectedListener=object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                changeGraphicsForce(position,pointEntity,graph,series)


            }
        }


    }

    override fun onFetchGraphicSpeedSuccess(pointEntity: PointEntity) {

        patient_profile_progress.visibility = View.GONE
        patient_profile.visibility = View.VISIBLE
        scroll.visibility=View.VISIBLE

        getDataPointsSpeed(pointEntity)
        val graph = findViewById<GraphView>(R.id.graph_speed)
        val series: LineGraphSeries<DataPoint> = LineGraphSeries()
        for(i in arrayPointsSpeed.indices) {
            series.appendData(
                DataPoint(Date(arrayPointsSpeed[i].startedAt), arrayPointsSpeed[i].value.toDouble()),
                true,
                arrayPointsSpeed.size
            )
        }
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.numHorizontalLabels = 5
        graph.title="Velocidad"
        graph.addSeries(series)

        spinner_options2.onItemSelectedListener=object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                changeGraphicsSpeed(position,pointEntity,graph,series)

            }
        }

    }

    override fun onFetchGraphicForceFail(error: String) {
        patient_profile_progress.visibility = View.GONE
        patient_profile_error.apply {
            visibility = View.VISIBLE
            text = error
        }
    }

    override fun onFetchGraphicSpeedFail(error: String) {
        patient_profile_progress.visibility = View.GONE
        patient_profile_error.apply {
            visibility = View.VISIBLE
            text = error
        }
    }

    private fun getDataPointsForce(pointEntity: PointEntity){

        arrayPointsForce.add(pointEntity)

    }

    private fun getDataPointsSpeed(pointEntity: PointEntity){

        arrayPointsSpeed.add(pointEntity)

    }

    private fun getLastHour(): Date{

        val calendar = Calendar.getInstance()
        /* calendar.set(Calendar.HOUR,5)
         calendar.set(Calendar.MINUTE,0)
         calendar.set(Calendar.SECOND,0)
         calendar.set(Calendar.DAY_OF_MONTH,17)
         calendar.set(Calendar.MONTH,Calendar.MAY)
         calendar.set(Calendar.YEAR,2020)*/

        calendar.add(Calendar.HOUR_OF_DAY,-1)

        return calendar.time

    }

    private fun getLastDay(): Date{

        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_WEEK,-1)

        return calendar.time

    }

    private fun getLastWeek(): Date{

        val calendar = Calendar.getInstance()

        calendar.add(Calendar.WEEK_OF_MONTH,-1)

        return calendar.time

    }

    private fun changeGraphicsForce(index:Int,pointEntity: PointEntity,graph:GraphView,series:LineGraphSeries<DataPoint>){
        when (index){
            0->{
                graph.removeAllSeries()
                getDataPointsForce(pointEntity)
                series.resetData(arrayOf<DataPoint>())
                for(i in arrayPointsForce.indices) {
                    series.appendData(
                        DataPoint(Date(arrayPointsForce[i].startedAt), arrayPointsForce[i].value.toDouble()),
                        true,
                        arrayPointsForce.size
                    )
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 5
                graph.title="Presion"
                graph.addSeries(series)
            }
            1 ->{
                graph.removeAllSeries()
                getDataPointsForce(pointEntity)
                series.resetData(arrayOf<DataPoint>())
                for(i in arrayPointsForce.indices) {
                    if(Date(arrayPointsForce[i].startedAt)>getLastHour()){
                        series.appendData(
                            DataPoint(Date(arrayPointsForce[i].startedAt), arrayPointsForce[i].value.toDouble()),

                            true,
                            arrayPointsForce.size
                        )
                    }
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Presion"
                graph.addSeries(series)
            }
            2 ->{
                graph.removeAllSeries()
                getDataPointsForce(pointEntity)
                series.resetData(arrayOf<DataPoint>())
                for(i in arrayPointsForce.indices) {
                    if(Date(arrayPointsForce[i].startedAt)>getLastDay()){
                        series.appendData(
                            DataPoint(Date(arrayPointsForce[i].startedAt), arrayPointsForce[i].value.toDouble()),

                            true,
                            arrayPointsForce.size
                        )
                    }
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Presion"
                graph.addSeries(series)
            }
            3 ->{
                graph.removeAllSeries()
                getDataPointsForce(pointEntity)
                series.resetData(arrayOf<DataPoint>())
                for(i in arrayPointsForce.indices) {
                    if(Date(arrayPointsForce[i].startedAt)>getLastWeek()){
                        series.appendData(
                            DataPoint(Date(arrayPointsForce[i].startedAt), arrayPointsForce[i].value.toDouble()),

                            true,
                            arrayPointsForce.size
                        )
                    }
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Presion"
                graph.addSeries(series)
            }
        }
    }

    private fun changeGraphicsSpeed(index:Int,pointEntity: PointEntity,graph:GraphView,series:LineGraphSeries<DataPoint>){
        when (index){
            0->{
                graph.removeAllSeries()
                getDataPointsSpeed(pointEntity)
                series.resetData(arrayOf<DataPoint>())
                for(i in arrayPointsSpeed.indices) {
                    series.appendData(
                        DataPoint(Date(arrayPointsSpeed[i].startedAt), arrayPointsSpeed[i].value.toDouble()),
                        true,
                        arrayPointsSpeed.size
                    )
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 5
                graph.title="Velocidad1"
                graph.addSeries(series)
            }
            1 ->{
                graph.removeAllSeries()
                getDataPointsSpeed(pointEntity)
                series.resetData(arrayOf<DataPoint>())
                for(i in arrayPointsSpeed.indices) {
                    if(Date(arrayPointsSpeed[i].startedAt)>getLastHour()){
                        series.appendData(
                            DataPoint(Date(arrayPointsSpeed[i].startedAt), arrayPointsSpeed[i].value.toDouble()),

                            true,
                            arrayPointsSpeed.size
                        )
                    }
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Velocidad"
                graph.addSeries(series)
            }
            2 ->{
                graph.removeAllSeries()
                getDataPointsSpeed(pointEntity)
                series.resetData(arrayOf<DataPoint>())
                for(i in arrayPointsSpeed.indices) {
                    if(Date(arrayPointsSpeed[i].startedAt)>getLastDay()){
                        series.appendData(
                            DataPoint(Date(arrayPointsSpeed[i].startedAt), arrayPointsSpeed[i].value.toDouble()),

                            true,
                            arrayPointsSpeed.size
                        )
                    }
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Velocidad"
                graph.addSeries(series)
            }
            3 ->{
                graph.removeAllSeries()
                getDataPointsSpeed(pointEntity)
                series.resetData(arrayOf<DataPoint>())
                for(i in arrayPointsSpeed.indices) {
                    if(Date(arrayPointsSpeed[i].startedAt)>getLastWeek()){
                        series.appendData(
                            DataPoint(Date(arrayPointsSpeed[i].startedAt), arrayPointsSpeed[i].value.toDouble()),

                            true,
                            arrayPointsSpeed.size
                        )
                    }
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Velocidad"
                graph.addSeries(series)
            }
        }
    }

/*
    private fun dataMockeadaForce(index:Int) {

        var arrayDateLastHour= arrayListOf<String>()
        arrayDateLastHour.addAll(listOf(
            "2020-04-30 18:10:12",
            "2020-04-30 18:15:15",
            "2020-04-30 18:18:16",
            "2020-04-30 18:35:17",
            "2020-04-30 18:45:19",
            "2020-04-30 18:59:20"
        ))

        var arrayDateLastDay= arrayListOf<String>()
        arrayDateLastDay.addAll(listOf(
            "2020-04-30 07:10:12",
            "2020-04-30 08:15:15",
            "2020-04-30 09:18:16",
            "2020-04-30 10:35:17",
            "2020-04-30 11:45:19",
            "2020-04-30 12:59:20",
            "2020-04-30 13:28:17",
            "2020-04-30 14:28:19",
            "2020-04-30 15:28:20",
            "2020-04-30 16:28:17",
            "2020-04-30 17:28:19",
            "2020-04-30 18:15:20"
        ))

        var arrayDateLastWeek= arrayListOf<String>()
        arrayDateLastWeek.addAll(listOf(
            "2020-04-22 10:10:12",
            "2020-04-23 10:10:12",
            "2020-04-24 10:10:12",
            "2020-04-25 10:10:12",
            "2020-04-26 10:10:12",
            "2020-04-27 10:10:12",
            "2020-04-30 10:10:12"
        ))

        var arrayValueLastHour= arrayListOf<Double>()
        arrayValueLastHour.addAll(listOf(
            4.0,
            6.0,
            6.5,
            7.0,
            7.5,
            8.0
        ))

        var arrayValueLastDay= arrayListOf<Double>()
        arrayValueLastDay.addAll(listOf(
            5.0,
            5.0,
            7.0,
            7.5,
            7.0,
            8.0,
            8.0,
            9.0,
            9.5,
            10.0,
            11.0,
            12.0
        ))

        var arrayValueLastWeek= arrayListOf<Double>()
        arrayValueLastWeek.addAll(listOf(
            4.0,
            4.0,
            6.0,
            6.5,
            7.0,
            8.0,
            8.0
        ))

        val series: LineGraphSeries<DataPoint> = LineGraphSeries()

        val graph = findViewById<GraphView>(R.id.graph_force)

        when(index){
            0->{
                graph.removeAllSeries()
                for(i in arrayDateLastHour.indices){
                    series.appendData(DataPoint(instanceToDate(arrayDateLastHour[i]),arrayValueLastHour[i]),true,arrayDateLastHour.size)
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Presion"
                graph.addSeries(series)
            }
            1->{
                graph.removeAllSeries()
                for(i in arrayDateLastDay.indices){
                    series.appendData(DataPoint(instanceToDate(arrayDateLastDay[i]),arrayValueLastDay[i]),true,arrayDateLastDay.size)
                }
                graph.title="Presion"
                graph.addSeries(series)
            }
            2->{
                graph.removeAllSeries()
                for(i in arrayDateLastWeek.indices){
                    series.appendData(DataPoint(instanceToDate(arrayDateLastWeek[i]),arrayValueLastWeek[i]),true,arrayDateLastWeek.size)
                }
                graph.title="Presion"
                graph.addSeries(series)
            }
        }




    }

    private fun dataMockeadaSpeed(index:Int){

        var arrayDateLastHour= arrayListOf<String>()
        arrayDateLastHour.addAll(listOf(
            "2020-04-30 18:10:12",
            "2020-04-30 18:15:15",
            "2020-04-30 18:18:16",
            "2020-04-30 18:35:17",
            "2020-04-30 18:45:19",
            "2020-04-30 18:59:20"
        ))

        var arrayDateLastDay= arrayListOf<String>()
        arrayDateLastDay.addAll(listOf(
            "2020-04-30 07:10:12",
            "2020-04-30 08:15:15",
            "2020-04-30 09:18:16",
            "2020-04-30 10:35:17",
            "2020-04-30 11:45:19",
            "2020-04-30 12:59:20",
            "2020-04-30 13:28:17",
            "2020-04-30 14:28:19",
            "2020-04-30 15:28:20",
            "2020-04-30 16:28:17",
            "2020-04-30 17:28:19",
            "2020-04-30 18:15:20"
        ))

        var arrayDateLastWeek= arrayListOf<String>()
        arrayDateLastWeek.addAll(listOf(
            "2020-04-22 10:10:12",
            "2020-04-23 10:10:12",
            "2020-04-24 10:10:12",
            "2020-04-25 10:10:12",
            "2020-04-26 10:10:12",
            "2020-04-27 10:10:12",
            "2020-04-30 10:10:12"
        ))

        var arrayValueLastHour= arrayListOf<Double>()
        arrayValueLastHour.addAll(listOf(
            4.0,
            5.0,
            6.0,
            6.5,
            7.0,
            7.5
        ))

        var arrayValueLastDay= arrayListOf<Double>()
        arrayValueLastDay.addAll(listOf(
            4.0,
            4.0,
            5.0,
            6.5,
            7.0,
            8.0,
            9.0,
            10.0,
            10.5,
            11.0,
            12.0,
            12.2
        ))

        var arrayValueLastWeek= arrayListOf<Double>()
        arrayValueLastWeek.addAll(listOf(
            4.0,
            5.0,
            6.0,
            6.5,
            7.0,
            6.5,
            8.0
        ))


        val series: LineGraphSeries<DataPoint> = LineGraphSeries()

        val graph = findViewById<GraphView>(R.id.graph_speed)

        when(index){
            0->{
                graph.removeAllSeries()
                for(i in arrayDateLastHour.indices){
                    series.appendData(DataPoint(instanceToDate(arrayDateLastHour[i]),arrayValueLastHour[i]),true,arrayDateLastHour.size)
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Velocidad"
                graph.addSeries(series)
            }
            1->{
                graph.removeAllSeries()
                for(i in arrayDateLastDay.indices){
                    series.appendData(DataPoint(instanceToDate(arrayDateLastDay[i]),arrayValueLastDay[i]),true,arrayDateLastDay.size)
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Velocidad"
                graph.addSeries(series)
            }
            2->{
                graph.removeAllSeries()
                for(i in arrayDateLastWeek.indices){
                    series.appendData(DataPoint(instanceToDate(arrayDateLastWeek[i]),arrayValueLastWeek[i]),true,arrayDateLastWeek.size)
                }
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Velocidad"
                graph.addSeries(series)
            }
        }



    }
*/

}
