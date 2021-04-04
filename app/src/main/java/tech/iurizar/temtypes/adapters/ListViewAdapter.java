package tech.iurizar.temtypes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tech.iurizar.temtypes.R;

public class ListViewAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public ListViewAdapter(@NonNull Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.bottom_sheet_list_view, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.list_item_text_view);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_item_image_view);
        String s = values[position];
        textView.setText(s);
        int iconId = getIcon(s);
        imageView.setImageResource(iconId);

        return rowView;
    }

    private int getIcon(String type) {
        switch(type) {
            case "Neutral":
                return R.drawable.neutral;
            case "Fire":
                return R.drawable.fire;
            case "Water":
                return R.drawable.water;
            case "Nature":
                return R.drawable.nature;
            case "Electric":
                return R.drawable.electric;
            case "Earth":
                return R.drawable.earth;
            case "Mental":
                return R.drawable.mental;
            case "Wind":
                return R.drawable.wind;
            case "Digital":
                return R.drawable.digital;
            case "Melee":
                return R.drawable.melee;
            case "Crystal":
                return R.drawable.crystal;
            case "Toxic":
                return R.drawable.toxic;
        }
        return 0;
    }
}
