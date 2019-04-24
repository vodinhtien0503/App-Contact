package com.example.contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>   {
    private final ArrayList <Contact> mContacts;

    public void setOnClickItemListner(OnClickItemListner onClickItemListner) {
        this.onClickItemListner = onClickItemListner;
    }

    private OnClickItemListner onClickItemListner;
    private Context context;
    private LayoutInflater layoutInflater;

    public void setOnClickAdapter(OnClickAdapter onClickAdapter) {
        this.onClickAdapter = onClickAdapter;
    }

    private OnClickAdapter onClickAdapter;

    public ContactAdapter(ArrayList<Contact> mContacts, Context context ) {
        this.mContacts = mContacts;
        this.context=context;
        this.layoutInflater=layoutInflater.from(context);

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.item_activity,viewGroup, false) ;
        return new ContactViewHolder(itemView,onClickItemListner);
    }
    @Override
    public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, int i) {
        Contact contact = mContacts.get(i);
// holder.tvTitle.setText(contact.getmTitle());
        holder.tvFullname.setText(contact.getName());
// holder.tvCompany.setText(contact.getmCompany());
    }
    @Override
    public int getItemCount() {
        return mContacts.size();
    }



    public class ContactViewHolder extends RecyclerView.ViewHolder  {
        // TextView tvTitle;
        TextView tvFullname;
       // OnContactListener onContactListener;

        // TextView tvCompany;
        public ContactViewHolder( View itemView,final OnClickItemListner onClickItemListner) {
            super(itemView);
// tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            //itemView.setClickable(true);
            tvFullname = (TextView) itemView.findViewById(R.id.tv_fullname);
            //this.onContactListener=onContactListener;
// tvCompany = (TextView) itemView.findViewById(R.id.tv_company);
            //itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION) {
                        onClickItemListner.OnClickedItem(position);
                    }
                }
            });
        }

    }
}
