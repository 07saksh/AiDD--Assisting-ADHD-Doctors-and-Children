package te.project.aidd;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class Simon_progress extends AppCompatActivity {
    DatabaseHelper db;
    int accuracy[]=new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_progress);

        db=new DatabaseHelper(this);
        SessionManagement ses=new SessionManagement(Simon_progress.this);
        String email=ses.getnaaam();
        accuracy=db.simon_graph(email);
        GraphView accuracygraph = (GraphView) findViewById(R.id.memograph);
        accuracygraph.getGridLabelRenderer().setHorizontalAxisTitle("NO OF GAMES");
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(1,accuracy[0]),
                new DataPoint(2,accuracy[1]),
                new DataPoint(3,accuracy[2]),
                new DataPoint(4,accuracy[3]),
                new DataPoint(5,accuracy[4]),
                new DataPoint(6,accuracy[5]),

        });
        Log.i("yoooo"," "+accuracy[4]);
        accuracygraph.addSeries(series);
        accuracygraph.getGridLabelRenderer().setNumHorizontalLabels(6);
        accuracygraph.getLegendRenderer().setBackgroundColor(Color.LTGRAY);
        accuracygraph.getViewport().setMinY(0);
        accuracygraph.getViewport().setMaxY(100);
        accuracygraph.getViewport().setYAxisBoundsManual(true);
        accuracygraph.setBackgroundColor(Color.WHITE);
        accuracygraph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        accuracygraph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
        accuracygraph.getLegendRenderer().setVisible(true);
        accuracygraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        series.setDataWidth(0.5);
        series.setAnimated(true);
        series.setTitle("MEMORY");
        series.setColor(Color.parseColor("#0288D1"));




    }
}