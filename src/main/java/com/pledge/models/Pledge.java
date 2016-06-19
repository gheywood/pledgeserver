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
}
