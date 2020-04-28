package com.upc.monitoringwalkers.ui.patients.profile.seeGraphics


import android.os.Bundle
import android.view.View
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.upc.monitoringwalkers.R
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
        presenter.fetchPointsOfForce(patientId)


    }

    override fun onFetchGraphicForceSuccess(pointEntity: PointEntity) {

        patient_profile_progress.visibility = View.GONE
        patient_profile.visibility = View.VISIBLE

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

        dataMockeadaForce()

    }

    override fun onFetchGraphicSpeedSuccess(pointEntity: PointEntity) {
        patient_profile_progress.visibility = View.GONE
        patient_profile.visibility = View.VISIBLE
        //patient_profile_appBar.visibility=View.VISIBLE

        dataMockeadaSpeed()
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


    private fun dataMockeadaForce(){
        val stringDate1="2020-04-20 10:28:12"
        val stringDate2="2020-04-20 10:28:15"
        val stringDate3="2020-04-20 10:28:16"
        val stringDate4="2020-04-20 10:28:17"
        val stringDate5="2020-04-20 10:28:19"
        val stringDate6="2020-04-20 10:28:20"

        val dateformatYyyymmddhhmmss = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH
        )
        val date1 = dateformatYyyymmddhhmmss.parse(stringDate1)
        val calendar1 = Calendar.getInstance()
        calendar1.time=date1

        val date2 = dateformatYyyymmddhhmmss.parse(stringDate2)
        val calendar2 = Calendar.getInstance()
        calendar2.time=date2

        val date3 = dateformatYyyymmddhhmmss.parse(stringDate3)
        val calendar3 = Calendar.getInstance()
        calendar3.time=date3

        val date4 = dateformatYyyymmddhhmmss.parse(stringDate4)
        val calendar4 = Calendar.getInstance()
        calendar4.time=date4

        val date5 = dateformatYyyymmddhhmmss.parse(stringDate5)
        val calendar5 = Calendar.getInstance()
        calendar5.time=date5

        val date6 = dateformatYyyymmddhhmmss.parse(stringDate6)
        val calendar6 = Calendar.getInstance()
        calendar6.time=date6

        val graph = findViewById<GraphView>(R.id.graph_force)
        val series = LineGraphSeries(
            arrayOf(
                DataPoint(calendar1.time, 2.0),
                DataPoint(calendar2.time, 5.0),
                DataPoint(calendar3.time, 1.0),
                DataPoint(calendar4.time, 3.0),
                DataPoint(calendar5.time, 5.0),
                DataPoint(calendar6.time, 8.0)
            )
        )
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.numHorizontalLabels = 6
        graph.viewport.setMinY(0.0)
        graph.viewport.setMinX(calendar1.time.time.toDouble())
        graph.viewport.setMaxX(calendar6.time.time.toDouble())
        graph.viewport.isXAxisBoundsManual = true
        graph.addSeries(series)
    }

    private fun dataMockeadaSpeed(){
        val stringDate1="2020-04-20 10:28:12"
        val stringDate2="2020-04-20 10:28:15"
        val stringDate3="2020-04-20 10:28:16"
        val stringDate4="2020-04-20 10:28:17"

        val dateformatYyyymmddhhmmss = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH
        )
        val date1 = dateformatYyyymmddhhmmss.parse(stringDate1)
        val calendar1 = Calendar.getInstance()
        calendar1.time=date1

        val date2 = dateformatYyyymmddhhmmss.parse(stringDate2)
        val calendar2 = Calendar.getInstance()
        calendar2.time=date2

        val date3 = dateformatYyyymmddhhmmss.parse(stringDate3)
        val calendar3 = Calendar.getInstance()
        calendar3.time=date3

        val date4 = dateformatYyyymmddhhmmss.parse(stringDate4)
        val calendar4 = Calendar.getInstance()
        calendar4.time=date4

        val graph = findViewById<GraphView>(R.id.graph_speed)
        val series = LineGraphSeries(
            arrayOf(
                DataPoint(calendar1.time, 10.0),
                DataPoint(calendar2.time, 9.0),
                DataPoint(calendar3.time, 1.0),
                DataPoint(calendar4.time, 2.0)
            )
        )
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.numHorizontalLabels = 4
        graph.viewport.setMinY(0.0)
        graph.viewport.setMinX(calendar1.time.time.toDouble())
        graph.viewport.setMaxX(calendar4.time.time.toDouble())
        graph.viewport.isXAxisBoundsManual = true
        graph.addSeries(series)
    }


}
