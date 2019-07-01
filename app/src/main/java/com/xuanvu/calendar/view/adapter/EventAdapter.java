package com.xuanvu.calendar.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuanvu.calendar.model.Event;
import com.xuanvu.calendar.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CalendarViewHolder> {
    private List<Event> mEvents;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnClickItemListener onClickListener;


    public EventAdapter(Context mContext, int mInflater, List<Event> mEvents, OnClickItemListener onClickListener) {
        this.mEvents = mEvents;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
        this.mInflater = LayoutInflater.from( mContext );
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate( R.layout.item_event, viewGroup, false );
        return new CalendarViewHolder( itemView, onClickListener );

    }


    public interface OnClickItemListener {
        void onItemRecyclerClicked(int postion, int actions);
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int i) {
        holder.tv_title.setText( "Title: "+ mEvents.get( i ).getmTitle() );
        holder.tv_content.setText("Content: "+ mEvents.get( i ).getmContent() );
        holder.tv_time.setText("Time: "+ mEvents.get( i ).getmStartTime()+ " - " + mEvents.get( i ).getmStartDate() + "  -  " + mEvents.get( i ).getmEndTime() + " - "+ mEvents.get( i ).getmEndDate() );
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;
        TextView tv_time;
        OnClickItemListener onClickItemListener;

        public CalendarViewHolder(@NonNull View itemView, final OnClickItemListener onClickCalendarListener) {
            super( itemView );
            tv_title = itemView.findViewById( R.id.tv_title );
            tv_content = itemView.findViewById( R.id.tv_content );
            tv_time = itemView.findViewById( R.id.tv_time );

            this.onClickItemListener = onClickItemListener;

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickCalendarListener.onItemRecyclerClicked( getAdapterPosition(), 1 );
                }
            } );

        }
    }
}