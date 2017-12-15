package live.cs443.umb.edu.live_cricket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemAdapterresults extends ArrayAdapter<String>{
    Context context;
    String[] whoone;
    int[] image_one;
    int[] image_two;
    ImageView img1;
    ImageView img2;
    String[] status;
    String[] score1;
    String[] score2;

    TextView Tscore1;
    TextView Tscore2;
    TextView Twohone;
    ImageView win1;
    ImageView win2;

    public ItemAdapterresults(Context paramContext, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    {
        super(paramContext, R.layout.item_result_tabl, paramArrayOfString1);
        this.context = paramContext;
        this.score1 = paramArrayOfString1;
        this.score2 = paramArrayOfString2;
        this.whoone = paramArrayOfString3;
        this.status = paramArrayOfString4;
        this.image_one = paramArrayOfInt1;
        this.image_two = paramArrayOfInt2;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
       //View localView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate( R.layout.item_shdul, paramViewGroup, false);
        LayoutInflater inflater = (LayoutInflater)   getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View localView = inflater.inflate(R.layout.item_result_tabl, paramViewGroup, false);
        this.Twohone = ((TextView)localView.findViewById(R.id.textView1));
        this.Tscore1 = ((TextView)localView.findViewById(R.id.textView2));
        this.Tscore2 = ((TextView)localView.findViewById(R.id.textView3));
        this.win1 = ((ImageView)localView.findViewById(R.id.win1));
        this.win2 = ((ImageView)localView.findViewById(R.id.win2));
        this.img1 = ((ImageView)localView.findViewById(R.id.imageView1));
        this.img2 = ((ImageView)localView.findViewById(R.id.imageView3));
        this.Tscore1.setText(this.score1[paramInt]);
        this.Tscore2.setText(this.score2[paramInt]);
        this.Twohone.setText(this.whoone[paramInt]);

        this.img1.setImageResource(this.image_one[paramInt]);
        this.img2.setImageResource(this.image_two[paramInt]);
        if(this.status[paramInt].trim().toLowerCase().contains("t1"))
        {
            this.win1.setVisibility(View.VISIBLE);
        }
        else
        {
            this.win2.setVisibility(View.VISIBLE);
        }
        return localView;

    }
}
