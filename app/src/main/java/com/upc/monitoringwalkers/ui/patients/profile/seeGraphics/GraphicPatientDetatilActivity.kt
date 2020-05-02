package com.upc.monitoringwalkers.ui.patients.profile.seeGraphics

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.longToast
import com.upc.monitoringwalkers.common.shortToast
import com.upc.monitoringwalkers.graphicPatientPresenter
import com.upc.monitoringwalkers.model.*
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.view.GraphicPatientDetatilView
import kotlinx.android.synthetic.main.activity_patient_graphics.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GraphicPatientDetatilActivity : BaseActivity(),
    GraphicPatientDetatilView {

    private val presenter by lazy { graphicPatientPresenter() }
    private lateinit var patientId: String

    var arrayPoints= arrayListOf<PointEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_graphics)
        presenter.setView(view = this)
        patientId = intent.extras!!.getString("patientId").toString()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initUi()
    }

    private fun initUi() {

        presenter.viewReady(patientId)
        var options= arrayOf("Última hora","Último día","Última semana")
        spinner_options.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        presenter.fetchPointsOfForce(patientId)

    }

    override fun onFetchGraphicForceSuccess(pointEntity: PointEntity) {

        patient_profile_progress.visibility = View.GONE
        patient_profile.visibility = View.VISIBLE
        scroll.visibility=View.VISIBLE


        //shortToast(this,pointEntity.toString())


        //shortToast(this,arrayPoints.toString())

        //dataMockeadaForce(0)
        /*spinner_options.onItemSelectedListener=object : AdapterView.OnItemClickListener,
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
                dataMockeadaForce(position)
                dataMockeadaSpeed(position)
            }
        }*/

    }

    override fun onFetchGraphicSpeedSuccess(pointEntity: PointEntity) {
        patient_profile_progress.visibility = View.GONE
        patient_profile.visibility = View.VISIBLE
        scroll.visibility=View.VISIBLE

        getDataPoints(pointEntity)

        //dataMockeadaSpeed(0)

        val series: LineGraphSeries<DataPoint> = LineGraphSeries()

        val graph = findViewById<GraphView>(R.id.graph_speed)

        //var pair = getDataPoints(pointEntity)

       /* var items : ArrayList<PointEntity> = ArrayList()

        items.add(pointEntity)*/

        //longToast(this,items.toString())

        for(i in arrayPoints.indices) {
            series.appendData(
                DataPoint(instanceToDate(arrayPoints[i].startedAt), arrayPoints[i].value.toDouble()),
                true,
                arrayPoints.size
            )
        }

        //longToast(this,items.toString())

        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.numHorizontalLabels = 4
        graph.title="Velocidad"
        graph.addSeries(series)

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

    private fun getDataPoints(pointEntity: PointEntity){// : MutableList<PointEntity>//ArrayList<Pair<String ,Double>>{//Pair<String ,Double>{

        val dateformatYyyymmddhhmmss = SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss", Locale.ENGLISH
        )
        val date = dateformatYyyymmddhhmmss.parse(pointEntity.startedAt)

        val calendar = Calendar.getInstance()
        calendar.time=date


        arrayPoints.add(pointEntity)


        /*

        for(i in arrayDateLastHour.indices){
            series.appendData(DataPoint(instanceToDate(arrayDateLastHour[i]),arrayValueLastHour[i]),true,arrayDateLastHour.size)
        }
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.numHorizontalLabels = 4
        graph.title="Presion"
        graph.addSeries(series)*/
        //longToast(this,pointEntity.toString())
       /*var items = mutableListOf<PointEntity>()
        items.add(pointEntity)

        var pair=Pair(pointEntity.startedAt,pointEntity.value.toDouble())

        var array= arrayListOf<Pair<String ,Double>>()
        array.add(pair)*/


    }

    private fun instanceToDate(stringDate:String):Date{

        val dateformatYyyymmddhhmmss = SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss", Locale.ENGLISH
        )
        val date = dateformatYyyymmddhhmmss.parse(stringDate)
        val calendar = Calendar.getInstance()
        calendar.time=date

        return calendar.time
    }

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

    private fun dataFromFirebaseForce(index:Int){

    }
}
