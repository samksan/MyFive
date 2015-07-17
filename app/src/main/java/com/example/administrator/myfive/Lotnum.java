package com.example.administrator.myfive;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Administrator on 2015/7/17.
 * 表 Lotnum 操作类
 */
@Table(name = "Lotnums")
public class Lotnum extends Model {
    @Column(name = "qh")
    public String qh;

    @Column(name = "num1")
    public int num1;

    @Column(name = "num2")
    public int num2;

    @Column(name = "num3")
    public int num3;

    @Column(name = "num4")
    public int num4;

    @Column(name = "num5")
    public int num5;

    public Lotnum() {
        super();
    }

    public Lotnum(String qh, int num1, int num2, int num3, int num4, int num5) {
        super();
        this.qh = qh;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
    }
}

