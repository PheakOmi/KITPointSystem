package com.DaoClasses;
import java.sql.*;  


public class test
{

    public static void main(String args[]) 
    {
        try 
        {
           //step1 Register the driver  
        	Class.forName("org.postgresql.Driver");
        	
            //step2 Get the connection from db 
        	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SBS","postgres", "admin");

            //step3 create the statement object  
            Statement stmt = con.createStatement();
            
            
            
            
            
            
            stmt.executeUpdate("create table bus_master(id serial primary key, plate_number varchar(10),name varchar(50), number_of_seat integer,availability boolean, Created_at timestamp, Updated_at timestamp,enabled boolean)");
            stmt.executeUpdate("create table batch_master(id serial primary key,name varchar(50),date_of_leaving date, date_of_returning date, deadline_booking date, Created_at timestamp, Updated_at timestamp,enabled boolean)");
            stmt.executeUpdate("create table User_info(id serial primary key, Name varchar(100),Email varchar(100), Password varchar(100),Created_at timestamp, Updated_at timestamp, enabled boolean)");
            stmt.executeUpdate("create table location_master(id serial primary key,name varchar(50),Created_at timestamp, Updated_at timestamp,enabled boolean)");
            stmt.executeUpdate("create table pick_up_location(id serial primary key, Name varchar(20),location_id integer,Created_at timestamp, Updated_at timestamp,enabled boolean)");
            stmt.executeUpdate("create table schedule_master(id serial primary key, bus_id integer, driver_id integer,destination_id integer, source_id integer,dept_date date,dept_time time,description varchar (200),number_booking integer, remaining_seat integer,number_customer integer,number_staff integer,number_student integer, Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table booking_master(id serial primary key,user_id integer,destination_id integer, source_id integer, schedule_id integer,booking_date date,dept_date date,dept_time time,number_booking integer, notification varchar(200),qr varchar(200),description varchar(100), Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table driver_bus_assigned(id serial primary key, schedule_id integer,driver_id integer,bus_id integer,dept_date date,dept_time time, Created_at timestamp, Updated_at timestamp)");

            
            
            
            
            System.out.println("Success");
            con.close();

        } 
        catch (Exception e) 
        {
            System.out.println(e.toString());
            
        }

    }
}



//C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib   --location of jdbc jar file 


/*

3 statement objects are available

1- Statement
2- prepared statement 
3- callable statement


prepared statement is much faster than other statements and secure too.

*/
