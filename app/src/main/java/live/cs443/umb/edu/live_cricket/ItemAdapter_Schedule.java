package live.cs443.umb.edu.live_cricket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemAdapter_Schedule extends ArrayAdapter<String>{
    Context context;
    String[] date;
    int[] image_one;
    int[] image_two;
    ImageView img1;
    ImageView img2;
    String[] place;
    String[] team_one;
    String[] team_two;
    String[] time;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;

    public ItemAdapter_Schedule(Context paramContext, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, String[] paramArrayOfString5, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    {
        super(paramContext, R.layout.item_schedule_tab, paramArrayOfString1);
        this.context = paramContext;
        this.team_one = paramArrayOfString1;
        this.team_two = paramArrayOfString2;
        this.date = paramArrayOfString3;
        this.time = paramArrayOfString4;
        this.place = paramArrayOfString5;
        this.image_one = paramArrayOfInt1;
        this.image_two = paramArrayOfInt2;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
       //View localView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate( R.layout.item_shdul, paramViewGroup, false);
        LayoutInflater inflater = (LayoutInflater)   getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View localView = inflater.inflate(R.layout.item_schedule_tab, paramViewGroup, false);

        this.txt1 = ((TextView)localView.findViewById(R.id.textView1));
        this.txt2 = ((TextView)localView.findViewById(R.id.textView2));
        this.txt3 = ((TextView)localView.findViewById(R.id.textView3));
        this.txt4 = ((TextView)localView.findViewById(R.id.textView4));
        this.txt5 = ((TextView)localView.findViewById(R.id.textView5));
        this.img1 = ((ImageView)localView.findViewById(R.id.imageView1));
        this.img2 = ((ImageView)localView.findViewById(R.id.imageView3));
        this.txt1.setText(this.team_one[paramInt]);
        this.txt2.setText(this.team_two[paramInt]);
        this.txt3.setText(this.date[paramInt]);
        this.txt4.setText(this.time[paramInt]);
        this.txt5.setText(this.place[paramInt]);
        this.img1.setImageResource(this.image_one[paramInt]);
        this.img2.setImageResource(this.image_two[paramInt]);
        return localView;


    }
}
