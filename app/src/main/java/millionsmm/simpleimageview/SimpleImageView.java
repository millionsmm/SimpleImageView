package millionsmm.simpleimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wilber
 * on 4/10/2017.
 */

public class SimpleImageView extends View {
    private Paint mPaint;
    private Drawable mDrawable;
    private int mWidth;
    private int mHeight;

    public SimpleImageView(Context context) {
        this(context, null);
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }


    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
                mDrawable = array.getDrawable(R.styleable.SimpleImageView_src);
                measureDrawable();
            } finally {
                if (array != null) {
                    array.recycle();
                    //TypedArray内部并没有提供构造方法，
                    // 用的是维护一个array池形成单例模式，如果不使用这种模式的话会造成oom；
                }
            }

        }
    }

    private void measureDrawable() {
        if (mDrawable == null) {
            throw new RuntimeException("drawable 不能为空");
        }
        mWidth = mDrawable.getIntrinsicWidth();
        mHeight = mDrawable.getIntrinsicHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawable == null) return;
        canvas.drawBitmap(ImageUtils.drawableToBitmap(mDrawable), getLeft(), getTop(), mPaint);
    }
}
