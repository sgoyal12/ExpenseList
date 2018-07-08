package in.codingninjas.envision.expensemanager;

import android.widget.TextView;

public class ExpenseViewHolder implements ViewHolder{

    TextView title;
    TextView amount;
    TextView date;
    TextView time;

    public ExpenseViewHolder(TextView title, TextView amount, TextView date, TextView time) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }
}
