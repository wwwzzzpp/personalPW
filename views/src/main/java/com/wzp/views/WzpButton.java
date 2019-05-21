package com.wzp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WzpButton extends View {

    private Paint paint;

    private int mcColor;
    private String mText = "";

    public WzpButton(Context context) {
        super(context);
    }

    public WzpButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WzpButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.WzpButton);
        mcColor = typedArray.getColor(R.styleable.WzpButton_color, Color.BLUE);
        if (typedArray.getText(R.styleable.WzpButton_textContent)!=null){
            mText = typedArray.getText(R.styleable.WzpButton_textContent).toString();
        }
        typedArray.recycle();
        init();

    }

    public WzpButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(mcColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText,100,100,paint);
    }
}
