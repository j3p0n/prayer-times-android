/*
 * Copyright (c) 2016 Metin Kale
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.metinkale.prayerapp.names;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.metinkale.prayer.R;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final Item[] mItems;
    private final Context mContext;
    private final String mLongest;

    Adapter(@NonNull Context context, @NonNull Item[] objects, boolean isMulticolumn) {
        mContext = context;
        mItems = objects;
        if (isMulticolumn) {
            String longest = "";
            for (Item i : objects) {
                if (i.desc != null && i.desc.length() > longest.length())
                    longest = i.desc;
            }
            mLongest = longest;
        } else {
            mLongest = null;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.names_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int pos) {
        Item i = mItems[pos];
        if (pos == 0) {
            vh.arabicImg.setVisibility(View.VISIBLE);
            vh.arabicImg.setImageResource(R.drawable.allah);
            vh.name.setVisibility(View.GONE);
            vh.arabic.setVisibility(View.GONE);
        } else {
            vh.arabicImg.setImageDrawable(null);
            vh.arabicImg.setVisibility(View.GONE);
            vh.arabic.setText(i.arabic);
            if (i.name == null) {
                vh.name.setVisibility(View.GONE);
            } else {
                vh.name.setText(i.name);
                vh.name.setVisibility(View.VISIBLE);
            }
            vh.arabic.setVisibility(View.VISIBLE);
        }

        if (i.desc == null) {
            vh.meaning.setVisibility(View.GONE);
            vh.meaningInv.setVisibility(View.GONE);
        } else {
            vh.meaning.setText(i.desc);
            vh.meaningInv.setText((mLongest == null || pos == 0) ? i.desc : mLongest);
        }

    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        else return 1;
    }

    static class Item {
        String arabic;
        String name;
        String desc;

        @NonNull
        @Override
        public String toString() {
            return arabic + " " + name + " " + desc;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView meaningInv;
        private final TextView name;
        private final TextView meaning;
        private final TextView arabic;
        private final ImageView arabicImg;

        ViewHolder(View convertView) {
            super(convertView);
            name = (TextView) convertView.findViewById(R.id.name);
            arabicImg = (ImageView) convertView.findViewById(R.id.arabicImg);
            arabic = (TextView) convertView.findViewById(R.id.arabicTxt);
            meaning = (TextView) convertView.findViewById(R.id.meaning);
            meaningInv = (TextView) convertView.findViewById(R.id.meaningInvisible);
        }
    }
}