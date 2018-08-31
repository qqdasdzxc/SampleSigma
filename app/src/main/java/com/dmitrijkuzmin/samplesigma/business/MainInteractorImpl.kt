package com.dmitrijkuzmin.samplesigma.business

import android.os.AsyncTask
import com.dmitrijkuzmin.samplesigma.model.entities.Rss
import com.dmitrijkuzmin.samplesigma.ui.presenter.MainPresenter
import org.simpleframework.xml.core.Persister
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainInteractorImpl(private var presenter: MainPresenter) : MainInteractor {

    private var task: RssTask = RssTask()

    override fun loadData() {
        if (task.status == AsyncTask.Status.PENDING || task.status == AsyncTask.Status.FINISHED) {
            task = RssTask()
            task.execute("https://habrahabr.ru/rss/hubs/all/")
        }
    }

    private fun onLoadingSuccess(result: Rss) {
        presenter.onLoadingSuccess(result)
    }

    private fun onLoadingError(message: String) {
        presenter.onLoadingError(message)
    }

    inner class RssTask : AsyncTask<String, String, String>() {

        private val REQUEST_METHOD = "GET"
        private val REQUEST_FAILED = "REQUEST_FAILED"
        private val READ_TIMEOUT = 10000
        private val CONNECTION_TIMEOUT = 10000

        override fun doInBackground(vararg strings: String): String {
            val urlString = strings[0]
            var result: String

            try {
                val url = URL(urlString)
                val conn = url.openConnection() as HttpURLConnection

                conn.requestMethod = REQUEST_METHOD
                conn.readTimeout = READ_TIMEOUT
                conn.connectTimeout = CONNECTION_TIMEOUT

                if (conn.responseCode == HttpsURLConnection.HTTP_OK) {
                    try {
                        val inputStream = InputStreamReader(conn.inputStream)
                        val reader = BufferedReader(inputStream)
                        val stringBuilder = StringBuilder()

                        reader.forEachLine { stringBuilder.append(it) }

                        reader.close()
                        inputStream.close()

                        result = stringBuilder.toString()
                    } finally {
                        conn.disconnect()
                    }
                } else {
                    result = REQUEST_FAILED
                }
            } catch (e: IOException) {
                result = REQUEST_FAILED
            }

            return result
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            if (s != REQUEST_FAILED) {
                val serializer = Persister()
                try {
                    val response = serializer.read(Rss::class.java, s)
                    onLoadingSuccess(response)
                } catch (e: Exception) {
                    e.printStackTrace()
                    onLoadingError(s)
                }
            } else {
                onLoadingError(s)
            }
        }

    }

}

