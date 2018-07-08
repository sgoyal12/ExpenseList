package in.codingninjas.envision.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context mContext;
    ArrayList<Item> itemsList;

LayoutInflater inflater;
    // Constructor
    public CustomAdapter(Context context, ArrayList<Item> itemsList){
        super(context,0);
        mContext = context;
        this.itemsList = itemsList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    // This function return the number of different type of views that will be there in the list view.
    @Override
    public int getViewTypeCount(){
        return 2;
    }

    // This function returns the type of item(in our case header or list item) that adapter wants to know in getView function.
    @Override
    public int getItemViewType(int position) {
        return itemsList.get(position).getItemType();
    }

    // This function gives the total count of items that will be there in the list.
    @Override
    public int getCount() {
        return itemsList.size();
    }

    // This function returns the object of the itemList that has to inflated at that position.
    @Override
    public Object getItem(int position) {
        return (Object)itemsList.get(position);
    }


    // This function returns the unique id associated with every inflated layout, since it's is not useful in our case so
    // we return the position, which is also unique for every item.
    @Override
    public long getItemId(int position) {
        return position;
    }




    // This is the function in which we have to inflate the layout as per its TYPE
    // this function gets the type of each item from getItemViewType and on the basis of it we apply if else and inflate the layout.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View output=convertView;
        int type=getItemViewType(position);
        if(output==null)
        {
            if(type==0)
            {
                output= inflater.inflate(R.layout.header_item_layout,parent,false);
                TextView tvtitle;
                tvtitle=output.findViewById(R.id.headerTitleTextView);
                HeaderItemViewHolder headerItemViewHolder=new HeaderItemViewHolder(tvtitle);
                output.setTag(headerItemViewHolder);

            }
            else {
                output=inflater.inflate(R.layout.expense_row_layout,parent,false);
                TextView tvname,tvamount,tvdate,tvtime;
                tvname=output.findViewById(R.id.expenseName);
                tvamount=output.findViewById(R.id.expenseAmount);
                tvdate=output.findViewById(R.id.expenseDate);
                tvtime=output.findViewById(R.id.expenseTime);
                ExpenseViewHolder expenseViewHolder=new ExpenseViewHolder(tvname,tvamount,tvdate,tvtime);
                output.setTag(expenseViewHolder);
            }
        }
        else
        {
            ViewHolder viewHolder=(ViewHolder)output.getTag();
            if(viewHolder instanceof ExpenseViewHolder&&type==0)
            {
                output= inflater.inflate(R.layout.header_item_layout,parent,false);
                TextView tvtitle;
                tvtitle=output.findViewById(R.id.headerTitleTextView);
                HeaderItemViewHolder headerItemViewHolder=new HeaderItemViewHolder(tvtitle);
                output.setTag(headerItemViewHolder);
            }
            else if(viewHolder instanceof HeaderItemViewHolder && type==1)
            {
                output=inflater.inflate(R.layout.expense_row_layout,parent,false);
                TextView tvname,tvamount,tvdate,tvtime;
                tvname=output.findViewById(R.id.expenseName);
                tvamount=output.findViewById(R.id.expenseAmount);
                tvdate=output.findViewById(R.id.expenseDate);
                tvtime=output.findViewById(R.id.expenseTime);
                ExpenseViewHolder expenseViewHolder=new ExpenseViewHolder(tvname,tvamount,tvdate,tvtime);
                output.setTag(expenseViewHolder);
            }
        }
        if(type==0)
        {
            HeaderItemViewHolder headerItemViewHolder= (HeaderItemViewHolder) output.getTag();
            HeaderItem headerItem=(HeaderItem) getItem(position);
            headerItemViewHolder.headerTitleTextView.setText(headerItem.getHeaderTitle());
        }
        else if(type==1)
        {
            ExpenseViewHolder expenseViewHolder=(ExpenseViewHolder) output.getTag();
            Expense expense= (Expense) getItem(position);
            expenseViewHolder.title.setText(expense.getName());
            expenseViewHolder.amount.setText(expense.getAmount()+"");
            expenseViewHolder.date.setText(expense.getDate());
            expenseViewHolder.time.setText(expense.getTime());
        }
        return output;
    }


}
