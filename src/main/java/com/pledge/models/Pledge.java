package com.pledge.models;

import java.util.Date;

/**
 * Created by grantheywood on 18/06/2016.
 */
public class Pledge
{
    public int pledge_id;
    public int user_id;
    public String title;
    public String description;
    public Date start_date;
    public Date end_date;
    public int type_id;
    public int delete_flag;

    @Override
    public String toString() {
        return "Pledge{" +
                "pledge_id=" + pledge_id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", type_id=" + type_id +
                ", delete_flag=" + delete_flag +
                '}';
    }
}
