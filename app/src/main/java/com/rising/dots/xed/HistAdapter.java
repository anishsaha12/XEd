package com.rising.dots.xed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 31-03-2018.
 */

public class HistAdapter extends ArrayAdapter<trans>{
        Context mCtx1;
        int listLayoutRes1;
        List<trans> transList1;
        public HistAdapter(Context mCtx1, int listLayoutRes1, List<trans> bookList1) {
            super(mCtx1, listLayoutRes1, bookList1);
            this.mCtx1 = mCtx1;
            this.listLayoutRes1 = listLayoutRes1;
            this.transList1= bookList1;
        }
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mCtx1);
            View view = inflater.inflate(listLayoutRes1, null);
            final trans Trans = transList1.get(position);

            TextView textViewAmount = (TextView) view.findViewById(R.id.AmtTV);
            TextView textViewReason = (TextView) view.findViewById(R.id.reasonTV);
            TextView textViewdat = (TextView) view.findViewById(R.id.timeTV);


            textViewAmount.setText(Trans.getAmount());
            textViewReason.setText(Trans.getReason());
            textViewdat.setText(Trans.getDat());

            return view;
        }
    }


