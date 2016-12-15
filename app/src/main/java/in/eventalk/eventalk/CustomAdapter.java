package in.eventalk.eventalk;


import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends BaseAdapter{
    private List<Bubble> Bubble;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, List<Bubble> post) {
        this.context = context;
        this.Bubble= post;

    }

    @Override
    public int getCount() {
        return Bubble.size();
    }

    @Override
    public Object getItem(int i) {
        return Bubble.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.individual_bubble, viewGroup, false);
        Bubble item = (Bubble) getItem(i);
        TextView user = (TextView) rowView.findViewById(R.id.BlouperName);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView caption = (TextView) rowView.findViewById(R.id.caption);
        TextView body = (TextView) rowView.findViewById(R.id.body);
        user.setText(item.getFirstName()+" "+item.getLastName());
        title.setText(item.getTitle());
        if(Build.VERSION.SDK_INT >= 24){
            body.setText(Html.fromHtml(item.getBody(), Html.FROM_HTML_MODE_LEGACY));
        }else{
            body.setText(Html.fromHtml(item.getBody()));
        }
        caption.setText(item.getCaption());


        return rowView;
    }
}
