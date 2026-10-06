package ru.leti.student.data;
public class Models {
 public static class News { public String title,date,text; public News(String t,String d,String x){title=t;date=d;text=x;} }
 public static class Lesson { public String time,name,teacher,room; public Lesson(String t,String n,String te,String r){time=t;name=n;teacher=te;room=r;} }
 public static class Grade { public String subject,grade,date; public Grade(String s,String g,String d){subject=s;grade=g;date=d;} }
}
