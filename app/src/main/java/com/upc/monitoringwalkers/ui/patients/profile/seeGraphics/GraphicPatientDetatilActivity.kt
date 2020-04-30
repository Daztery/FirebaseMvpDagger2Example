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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_graphics)
        //setSupportActionBar(patient_profile_toolbar)
        presenter.setView(this)
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
        //patient_profile_appBar.visibility=View.VISIBLE

        //val stringDate1="2019-08-07 09:00:00"

        /*val dateformatYyyymmddhhmmss = SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss", Locale.ENGLISH
        )

        val date = dateformatYyyymmddhhmmss.parse(pointEntity.startedAt)

        val calendar = Calendar.getInstance()
        calendar.time=date
        val graphForce = graph_force as GraphView

        /*var arrayDate: ArrayList<Date> = ArrayList()
        arrayDate.add(calendar.time)*/

        //shortToast(this,arrayDate.toString())

        //val series = LineGraphSeries(arrayOf(DataPoint(calendar.time, pointEntity.value.toDouble())))

        val series = LineGraphSeries(arrayOf(
            DataPoint(calendar1.time, 2.0),
            DataPoint(calendar2.time, 5.0),
            DataPoint(calendar3.time, 1.0),
            DataPoint(calendar4.time, 3.0)

        ))

        /*graphForce.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graphForce.gridLabelRenderer.numHorizontalLabels = 8
        graphForce.viewport.setMinY(0.0)
        graphForce.viewport.setMinX(calendar.time.time.toDouble())
        graphForce.viewport.setMaxX(calendar.time.time.toDouble())
        graphForce.viewport.isXAxisBoundsManual = true*/

        graphForce.addSeries(series)*/

        dataMockeadaForce(0)
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
                dataMockeadaForce(position)
                dataMockeadaSpeed(position)
                //shortToast(this@GraphicPatientDetatilActivity,position.toString())
            }
        }

        //
    }

    override fun onFetchGraphicSpeedSuccess(pointEntity: PointEntity) {
        patient_profile_progress.visibility = View.GONE
        patient_profile.visibility = View.VISIBLE
        scroll.visibility=View.VISIBLE
        //patient_profile_appBar.visibility=View.VISIBLE


        dataMockeadaSpeed(0)
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
                dataMockeadaSpeed(position)
            }
        }*/
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


    private fun getDataPoints(pointEntity: PointEntity):ArrayList<DataPoint>{

        val dateformatYyyymmddhhmmss = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH
        )
        val date = dateformatYyyymmddhhmmss.parse(pointEntity.startedAt)

        val calendar = Calendar.getInstance()
        calendar.time=date

        var arrayDataPoint: ArrayList<DataPoint> = ArrayList()

        arrayDataPoint.add(DataPoint(calendar.time, pointEntity.value.toDouble()))

        return arrayDataPoint

    }


    private fun dataMockeadaForce(index:Int) {


        //Last Hour
        val lastHour1 = "2020-04-30 18:10:12"
        val lastHour2 = "2020-04-30 18:15:15"
        val lastHour3 = "2020-04-30 18:18:16"
        val lastHour4 = "2020-04-30 18:35:17"
        val lastHour5 = "2020-04-30 18:45:19"
        val lastHour6 = "2020-04-30 18:59:20"


        //Last Day
        val lastDay1 = "2020-04-30 07:10:12"
        val lastDay2 = "2020-04-30 08:15:15"
        val lastDay3 = "2020-04-30 09:18:16"
        val lastDay4 = "2020-04-30 10:35:17"
        val lastDay5 = "2020-04-30 11:45:19"
        val lastDay6 = "2020-04-30 12:59:20"
        val lastDay7 = "2020-04-30 13:28:17"
        val lastDay8 = "2020-04-30 14:28:19"
        val lastDay9 = "2020-04-30 15:28:20"
        val lastDay10 = "2020-04-30 16:28:17"
        val lastDay11 = "2020-04-30 17:28:19"
        val lastDay12 = "2020-04-30 18:15:20"


        //Last Week
        val lastWeek1 = "2020-04-22 10:10:12"
        val lastWeek2 = "2020-04-23 10:10:12"
        val lastWeek3 = "2020-04-24 10:10:12"
        val lastWeek4 = "2020-04-25 10:10:12"
        val lastWeek5 = "2020-04-26 10:10:12"
        val lastWeek6 = "2020-04-27 10:10:12"
        val lastWeek7 = "2020-04-30 10:10:12"


        /* var arrayDateLastHour= arrayListOf<String>()
        arrayDateLastHour.addAll(listOf(
            "2020-04-30 18:10:12",
            "2020-04-30 18:15:15",
            "2020-04-30 18:18:16",
            "2020-04-30 18:35:17",
            "2020-04-30 18:45:19",
            "2020-04-30 18:59:20"
        ))

        var arrayValueLastHour= arrayListOf<Double>()
        arrayValueLastHour.addAll(listOf(
            4.0,
            6.0,
            6.5,
            7.0,
            7.5,
            8.0
        ))*/

        /* var dataPoints  = arrayOfNulls<DataPoint>(arrayDateLastHour.size)

        for (i in arrayDateLastHour.indices){
            dataPoints[i] = DataPoint(instanceToDate(arrayDateLastHour[i]),arrayValueLastHour[i])
        }

        val series1: LineGraphSeries<DataPoint> = LineGraphSeries()

        for (i in arrayDateLastHour.indices){
            series1.appendData(dataPoints[i],false,dataPoints.size)
        }*/

        val graph = findViewById<GraphView>(R.id.graph_force)

        var series:LineGraphSeries<DataPoint> = LineGraphSeries()

        when (index) {
            0 -> {
                graph.removeAllSeries()
                series = LineGraphSeries(
                    arrayOf(
                        DataPoint(instanceToDate(lastHour1), 4.0),
                        DataPoint(instanceToDate(lastHour2), 4.0),
                        DataPoint(instanceToDate(lastHour3), 6.0),
                        DataPoint(instanceToDate(lastHour4), 6.5),
                        DataPoint(instanceToDate(lastHour5), 7.0),
                        DataPoint(instanceToDate(lastHour6), 7.5)

                    )
                )
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.viewport.isXAxisBoundsManual = true
                graph.viewport.setMinX(instanceToDate(lastHour1).time.toDouble())
                graph.viewport.setMaxX(instanceToDate(lastHour6).time.toDouble())
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Presion"
                graph.addSeries(series)
            }
            1 -> {

                graph.removeAllSeries()
                series = LineGraphSeries(
                    arrayOf(
                        DataPoint(instanceToDate(lastDay1 ), 4.0),
                        DataPoint(instanceToDate(lastDay2 ), 4.0),
                        DataPoint(instanceToDate(lastDay3 ), 6.0),
                        DataPoint(instanceToDate(lastDay4 ), 6.5),
                        DataPoint(instanceToDate(lastDay5 ), 7.0),
                        DataPoint(instanceToDate(lastDay6 ), 4.0),
                        DataPoint(instanceToDate(lastDay7 ), 4.0),
                        DataPoint(instanceToDate(lastDay8 ), 6.0),
                        DataPoint(instanceToDate(lastDay9 ), 6.5),
                        DataPoint(instanceToDate(lastDay10), 7.0),
                        DataPoint(instanceToDate(lastDay11), 4.0),
                        DataPoint(instanceToDate(lastDay12), 4.0)
                    )
                )

                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.viewport.isXAxisBoundsManual = true
                graph.viewport.setMinX(instanceToDate(lastDay1).time.toDouble())
                graph.viewport.setMaxX(instanceToDate(lastDay12).time.toDouble())
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Presion"
                graph.addSeries(series)
            }
            2 -> {

                graph.removeAllSeries()
                series = LineGraphSeries(
                    arrayOf(
                        DataPoint(instanceToDate(lastWeek1 ), 4.0),
                        DataPoint(instanceToDate(lastWeek2 ), 4.0),
                        DataPoint(instanceToDate(lastWeek3 ), 6.0),
                        DataPoint(instanceToDate(lastWeek4 ), 6.5),
                        DataPoint(instanceToDate(lastWeek5 ), 7.0),
                        DataPoint(instanceToDate(lastWeek6 ), 4.0),
                        DataPoint(instanceToDate(lastWeek7 ), 8.0)
                    )
                )

                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                //graph.viewport.isScalable = true
                //graph.viewport.isScrollable = true
                //graph.viewport.setScalableY(true)
                //graph.viewport.setScrollableY(true)
                graph.viewport.isXAxisBoundsManual = true
                graph.viewport.setMinX(instanceToDate(lastWeek1).time.toDouble())
                graph.viewport.setMaxX(instanceToDate(lastWeek7).time.toDouble())
                graph.gridLabelRenderer.numHorizontalLabels=4
                graph.title="Presion"
                graph.addSeries(series)

            }

        }



    }

    private fun instanceToDate(stringDate:String):Date{
        val dateformatYyyymmddhhmmss = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH
        )
        val date = dateformatYyyymmddhhmmss.parse(stringDate)
        val calendar = Calendar.getInstance()
        calendar.time=date

        return calendar.time
    }

    private fun dataMockeadaSpeed(index:Int){
/*
        val stringDate1="2020-04-20 10:28:12"
        val stringDate2="2020-04-20 10:28:15"
        val stringDate3="2020-04-20 10:28:16"
        val stringDate4="2020-04-20 10:28:17"

        val graph = findViewById<GraphView>(R.id.graph_speed)

        val series = LineGraphSeries(
            arrayOf(
                DataPoint(instanceToDate(stringDate1), 3.0),
                DataPoint(instanceToDate(stringDate2), 3.5),
                DataPoint(instanceToDate(stringDate3), 4.5),
                DataPoint(instanceToDate(stringDate4), 6.0)
            )
        )

        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.numHorizontalLabels = 4
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true
        graph.viewport.setScalableY(true)
        graph.viewport.setScrollableY(true)


        graph.title="Velocidad"
        graph.addSeries(series)*/

        //Last Hour
        val lastHour1 = "2020-04-30 18:10:12"
        val lastHour2 = "2020-04-30 18:15:15"
        val lastHour3 = "2020-04-30 18:18:16"
        val lastHour4 = "2020-04-30 18:35:17"
        val lastHour5 = "2020-04-30 18:45:19"
        val lastHour6 = "2020-04-30 18:59:20"


        //Last Day
        val lastDay1 = "2020-04-30 07:10:12"
        val lastDay2 = "2020-04-30 08:15:15"
        val lastDay3 = "2020-04-30 09:18:16"
        val lastDay4 = "2020-04-30 10:35:17"
        val lastDay5 = "2020-04-30 11:45:19"
        val lastDay6 = "2020-04-30 12:59:20"
        val lastDay7 = "2020-04-30 13:28:17"
        val lastDay8 = "2020-04-30 14:28:19"
        val lastDay9 = "2020-04-30 15:28:20"
        val lastDay10 = "2020-04-30 16:28:17"
        val lastDay11 = "2020-04-30 17:28:19"
        val lastDay12 = "2020-04-30 18:15:20"


        //Last Week
        val lastWeek1 = "2020-04-22 10:10:12"
        val lastWeek2 = "2020-04-23 10:10:12"
        val lastWeek3 = "2020-04-24 10:10:12"
        val lastWeek4 = "2020-04-25 10:10:12"
        val lastWeek5 = "2020-04-26 10:10:12"
        val lastWeek6 = "2020-04-27 10:10:12"
        val lastWeek7 = "2020-04-30 10:10:12"

        val graph = findViewById<GraphView>(R.id.graph_speed)

        var series:LineGraphSeries<DataPoint> = LineGraphSeries()

        when (index) {
            0 -> {
                graph.removeAllSeries()
                series = LineGraphSeries(
                    arrayOf(
                        DataPoint(instanceToDate(lastHour1), 4.0),
                        DataPoint(instanceToDate(lastHour2), 4.0),
                        DataPoint(instanceToDate(lastHour3), 6.0),
                        DataPoint(instanceToDate(lastHour4), 6.5),
                        DataPoint(instanceToDate(lastHour5), 7.0),
                        DataPoint(instanceToDate(lastHour6), 7.5)

                    )
                )
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.viewport.isXAxisBoundsManual = true
                graph.viewport.setMinX(instanceToDate(lastHour1).time.toDouble())
                graph.viewport.setMaxX(instanceToDate(lastHour6).time.toDouble())
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Velocidad"
                graph.addSeries(series)
            }
            1 -> {

                graph.removeAllSeries()
                series = LineGraphSeries(
                    arrayOf(
                        DataPoint(instanceToDate(lastDay1 ), 4.0),
                        DataPoint(instanceToDate(lastDay2 ), 4.0),
                        DataPoint(instanceToDate(lastDay3 ), 6.0),
                        DataPoint(instanceToDate(lastDay4 ), 6.5),
                        DataPoint(instanceToDate(lastDay5 ), 7.0),
                        DataPoint(instanceToDate(lastDay6 ), 4.0),
                        DataPoint(instanceToDate(lastDay7 ), 4.0),
                        DataPoint(instanceToDate(lastDay8 ), 6.0),
                        DataPoint(instanceToDate(lastDay9 ), 6.5),
                        DataPoint(instanceToDate(lastDay10), 7.0),
                        DataPoint(instanceToDate(lastDay11), 4.0),
                        DataPoint(instanceToDate(lastDay12), 4.0)
                    )
                )

                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                graph.viewport.isXAxisBoundsManual = true
                graph.viewport.setMinX(instanceToDate(lastDay1).time.toDouble())
                graph.viewport.setMaxX(instanceToDate(lastDay12).time.toDouble())
                graph.gridLabelRenderer.numHorizontalLabels = 4
                graph.title="Velocidad"
                graph.addSeries(series)
            }
            2 -> {

                graph.removeAllSeries()
                series = LineGraphSeries(
                    arrayOf(
                        DataPoint(instanceToDate(lastWeek1 ), 4.0),
                        DataPoint(instanceToDate(lastWeek2 ), 4.0),
                        DataPoint(instanceToDate(lastWeek3 ), 6.0),
                        DataPoint(instanceToDate(lastWeek4 ), 6.5),
                        DataPoint(instanceToDate(lastWeek5 ), 7.0),
                        DataPoint(instanceToDate(lastWeek6 ), 4.0),
                        DataPoint(instanceToDate(lastWeek7 ), 8.0)
                    )
                )

                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
                //graph.viewport.isScalable = true
                //graph.viewport.isScrollable = true
                //graph.viewport.setScalableY(true)
                //graph.viewport.setScrollableY(true)
                graph.viewport.isXAxisBoundsManual = true
                graph.viewport.setMinX(instanceToDate(lastWeek1).time.toDouble())
                graph.viewport.setMaxX(instanceToDate(lastWeek7).time.toDouble())
                graph.gridLabelRenderer.numHorizontalLabels=4
                graph.title="Velocidad"
                graph.addSeries(series)

            }

        }

    }

}
