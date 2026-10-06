package ru.leti.student;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import ru.leti.student.data.*;
import java.util.*;

public class MainActivity extends Activity {
    LinearLayout root, body, nav;
    StudentRepository repo = new StudentRepository();
    int purple=Color.rgb(91,95,239), text=Color.rgb(23,25,35), muted=Color.rgb(119,123,138);

    @Override public void onCreate(Bundle b){ super.onCreate(b); build(); home(); }

    TextView tv(String s,float size,int color){
        TextView v=new TextView(this); v.setText(s); v.setTextSize(size); v.setTextColor(color);
        v.setFontFeatureSettings("kern"); v.setPadding(0,0,0,0); return v;
    }
    GradientDrawable bg(int color,float r){ GradientDrawable g=new GradientDrawable(); g.setColor(color); g.setCornerRadius(r); return g; }

    void build(){
        root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.rgb(247,247,251));
        setContentView(root);

        LinearLayout top=new LinearLayout(this); top.setGravity(Gravity.CENTER_VERTICAL); top.setPadding(22,18,22,12);
        TextView logo=tv("LETI",24,text); logo.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        TextView sub=tv("  STUDENT",12,muted); sub.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        top.addView(logo); top.addView(sub);
        Space sp=new Space(this); top.addView(sp,new LinearLayout.LayoutParams(0,1,1));
        TextView avatar=tv("●",22,purple); top.addView(avatar);
        root.addView(top,new LinearLayout.LayoutParams(-1,62));

        body=new LinearLayout(this); body.setOrientation(LinearLayout.VERTICAL); body.setPadding(18,8,18,8);
        ScrollView scroll=new ScrollView(this); scroll.addView(body); root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));

        nav=new LinearLayout(this); nav.setOrientation(LinearLayout.HORIZONTAL); nav.setGravity(Gravity.CENTER);
        nav.setPadding(8,6,8,8); nav.setBackgroundColor(Color.WHITE);
        root.addView(nav,new LinearLayout.LayoutParams(-1,70));
        addNav("⌂","Главная",()->home()); addNav("▣","Новости",()->news());
        addNav("◷","Расписание",()->schedule()); addNav("★","Оценки",()->grades()); addNav("●","Профиль",()->profile());
    }

    void addNav(String icon,String name,final Runnable r){
        TextView v=tv(icon+"\n"+name,11,muted); v.setGravity(Gravity.CENTER); v.setPadding(3,3,3,3);
        v.setOnClickListener(x->r()); nav.addView(v,new LinearLayout.LayoutParams(0,-1,1));
    }
    TextView heading(String s){
        TextView v=tv(s,27,text); v.setTypeface(Typeface.DEFAULT,Typeface.BOLD); v.setPadding(0,14,0,12); return v;
    }
    TextView small(String s){ TextView v=tv(s,13,muted); v.setPadding(0,3,0,3); return v; }
    LinearLayout card(){
        LinearLayout c=new LinearLayout(this); c.setOrientation(LinearLayout.VERTICAL); c.setPadding(18,16,18,16);
        c.setBackground(bg(Color.WHITE,28)); c.setElevation(2); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(0,7,0,7); c.setLayoutParams(p); return c;
    }
    void clear(){body.removeAllViews();}
    void home(){
        clear(); body.addView(heading("Добро пожаловать"));
        LinearLayout hero=card(); TextView a=tv("Личный кабинет студента",19,text); a.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        hero.addView(a); hero.addView(small("Быстрый доступ к учебной информации"));
        TextView b=tv("Открыть официальный кабинет",15,Color.WHITE); b.setGravity(Gravity.CENTER); b.setPadding(0,14,0,14); b.setBackground(bg(purple,24));
        LinearLayout.LayoutParams bp=new LinearLayout.LayoutParams(-1,52); bp.setMargins(0,14,0,0); hero.addView(b,bp);
        b.setOnClickListener(v->login()); body.addView(hero);
        TextView h=tv("Сегодня",18,text); h.setTypeface(Typeface.DEFAULT,Typeface.BOLD); h.setPadding(0,18,0,6); body.addView(h);
        LinearLayout s=card(); s.addView(small("РАСПИСАНИЕ")); s.addView(tv("Откройте раздел «Расписание»",16,text)); body.addView(s);
    }
    void news(){
        clear(); body.addView(heading("Новости"));
        for(Models.News n:repo.news()){ LinearLayout c=card(); TextView t=tv(n.title,17,text); t.setTypeface(Typeface.DEFAULT,Typeface.BOLD); c.addView(t); c.addView(small(n.date)); c.addView(tv(n.text,14,text)); body.addView(c); }
    }
    void schedule(){
        clear(); body.addView(heading("Расписание"));
        for(Models.Lesson l:repo.schedule()){ LinearLayout c=card(); TextView t=tv(l.time+"   "+l.name,17,text); t.setTypeface(Typeface.DEFAULT,Typeface.BOLD); c.addView(t); c.addView(small(l.teacher+"  •  "+l.room)); body.addView(c); }
    }
    void grades(){
        clear(); body.addView(heading("Успеваемость"));
        for(Models.Grade g:repo.grades()){ LinearLayout c=card(); LinearLayout row=new LinearLayout(this); row.setGravity(Gravity.CENTER_VERTICAL);
            TextView s=tv(g.subject,16,text); s.setTypeface(Typeface.DEFAULT,Typeface.BOLD); row.addView(s,new LinearLayout.LayoutParams(0,50,1));
            TextView gr=tv(g.grade,18,purple); gr.setTypeface(Typeface.DEFAULT,Typeface.BOLD); row.addView(gr); c.addView(row); body.addView(c); }
    }
    void profile(){
        clear(); body.addView(heading("Профиль"));
        LinearLayout c=card(); c.addView(tv("Студент",18,text)); c.addView(small("Данные будут загружены после подключения аккаунта.")); body.addView(c);
        TextView login=tv("Войти в личный кабинет",15,Color.WHITE); login.setGravity(Gravity.CENTER); login.setPadding(0,14,0,14); login.setBackground(bg(purple,24));
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,52); p.setMargins(0,12,0,0); body.addView(login,p); login.setOnClickListener(v->login());
    }
    void login(){
        WebView w=new WebView(this); WebSettings s=w.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true);
        s.setUserAgentString(s.getUserAgentString()+" LETIStudentAndroid/3.0");
        w.setWebViewClient(new WebViewClient(){@Override public boolean shouldOverrideUrlLoading(WebView v,String u){v.loadUrl(u);return true;}});
        w.loadUrl("https://lk.etu.ru/login");
        new AlertDialog.Builder(this).setTitle("Вход в ЛЭТИ").setView(w).setNegativeButton("Закрыть",null).show();
    }
    @Override public void onBackPressed(){super.onBackPressed();}
}
