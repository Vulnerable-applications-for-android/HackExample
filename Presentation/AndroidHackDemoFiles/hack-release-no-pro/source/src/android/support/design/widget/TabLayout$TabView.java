// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.TintManager;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

// Referenced classes of package android.support.design.widget:
//            TabLayout

class update extends LinearLayout
    implements android.view.stener
{

    private ImageView mCustomIconView;
    private TextView mCustomTextView;
    private View mCustomView;
    private int mDefaultMaxLines;
    private ImageView mIconView;
    private final mCustomIconView mTab;
    private TextView mTextView;
    final TabLayout this$0;

    private float approximateLineWidth(Layout layout, int i, float f)
    {
        return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
    }

    private void updateTextAndIcon(update update1, TextView textview, ImageView imageview)
    {
        android.graphics.drawable.Drawable drawable = update1.con();
        CharSequence charsequence = update1.ext();
        boolean flag;
        if (imageview != null)
        {
            int i;
            boolean flag1;
            if (drawable != null)
            {
                imageview.setImageDrawable(drawable);
                imageview.setVisibility(0);
                setVisibility(0);
            } else
            {
                imageview.setVisibility(8);
                imageview.setImageDrawable(null);
            }
            imageview.setContentDescription(update1.ontentDescription());
        }
        if (!TextUtils.isEmpty(charsequence))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (textview != null)
        {
            if (flag)
            {
                textview.setText(charsequence);
                textview.setContentDescription(update1.ontentDescription());
                textview.setVisibility(0);
                setVisibility(0);
            } else
            {
                textview.setVisibility(8);
                textview.setText(null);
            }
        }
        if (imageview != null)
        {
            textview = (android.view.youtParams)imageview.getLayoutParams();
            flag1 = false;
            i = ((flag1) ? 1 : 0);
            if (flag)
            {
                i = ((flag1) ? 1 : 0);
                if (imageview.getVisibility() == 0)
                {
                    i = TabLayout.access$1500(TabLayout.this, 8);
                }
            }
            if (i != ((android.view.youtParams) (textview)).bottomMargin)
            {
                textview.bottomMargin = i;
                imageview.requestLayout();
            }
        }
        if (!flag && !TextUtils.isEmpty(update1.ontentDescription()))
        {
            setOnLongClickListener(this);
            return;
        } else
        {
            setOnLongClickListener(null);
            setLongClickable(false);
            return;
        }
    }

    public setLongClickable getTab()
    {
        return mTab;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/support/v7/app/ActionBar$Tab.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/support/v7/app/ActionBar$Tab.getName());
    }

    public boolean onLongClick(View view)
    {
        view = new int[2];
        getLocationOnScreen(view);
        Object obj = getContext();
        int i = getWidth();
        int j = getHeight();
        int k = ((Context) (obj)).getResources().getDisplayMetrics().widthPixels;
        obj = Toast.makeText(((Context) (obj)), mTab.ontentDescription(), 0);
        ((Toast) (obj)).setGravity(49, (view[0] + i / 2) - k / 2, j);
        ((Toast) (obj)).show();
        return true;
    }

    public void onMeasure(int i, int j)
    {
        int k = android.view.etSize(i);
        int i1 = android.view.etMode(i);
        int k1 = TabLayout.access$800(TabLayout.this);
        if (k1 > 0 && (i1 == 0 || k > k1))
        {
            i = android.view.akeMeasureSpec(TabLayout.access$900(TabLayout.this), i1);
        }
        super.onMeasure(i, j);
        if (mTextView == null) goto _L2; else goto _L1
_L1:
        float f1;
        int j1;
        getResources();
        f1 = TabLayout.access$1000(TabLayout.this);
        j1 = mDefaultMaxLines;
        if (mIconView == null || mIconView.getVisibility() != 0) goto _L4; else goto _L3
_L3:
        float f;
        int l;
        l = 1;
        f = f1;
_L6:
        f1 = mTextView.getTextSize();
        int l1 = mTextView.getLineCount();
        j1 = TextViewCompat.getMaxLines(mTextView);
        if (f != f1 || j1 >= 0 && l != j1)
        {
label0:
            {
                boolean flag = true;
                j1 = ((flag) ? 1 : 0);
                if (TabLayout.access$1200(TabLayout.this) != 1)
                {
                    break label0;
                }
                j1 = ((flag) ? 1 : 0);
                if (f <= f1)
                {
                    break label0;
                }
                j1 = ((flag) ? 1 : 0);
                if (l1 != 1)
                {
                    break label0;
                }
                Layout layout = mTextView.getLayout();
                if (layout != null)
                {
                    j1 = ((flag) ? 1 : 0);
                    if (approximateLineWidth(layout, 0, f) <= (float)layout.getWidth())
                    {
                        break label0;
                    }
                }
                j1 = 0;
            }
            if (j1 != 0)
            {
                mTextView.setTextSize(0, f);
                mTextView.setMaxLines(l);
                super.onMeasure(i, j);
            }
        }
_L2:
        return;
_L4:
        l = j1;
        f = f1;
        if (mTextView != null)
        {
            l = j1;
            f = f1;
            if (mTextView.getLineCount() > 1)
            {
                f = TabLayout.access$1100(TabLayout.this);
                l = j1;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void setSelected(boolean flag)
    {
        boolean flag1;
        if (isSelected() != flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        super.setSelected(flag);
        if (flag1 && flag)
        {
            sendAccessibilityEvent(4);
            if (mTextView != null)
            {
                mTextView.setSelected(flag);
            }
            if (mIconView != null)
            {
                mIconView.setSelected(flag);
            }
        }
    }

    final void update()
    {
        mIconView miconview = mTab;
        View view = miconview.ustomView();
        if (view != null)
        {
            android.view.ViewParent viewparent = view.getParent();
            if (viewparent != this)
            {
                if (viewparent != null)
                {
                    ((ViewGroup)viewparent).removeView(view);
                }
                addView(view);
            }
            mCustomView = view;
            if (mTextView != null)
            {
                mTextView.setVisibility(8);
            }
            if (mIconView != null)
            {
                mIconView.setVisibility(8);
                mIconView.setImageDrawable(null);
            }
            mCustomTextView = (TextView)view.findViewById(0x1020014);
            if (mCustomTextView != null)
            {
                mDefaultMaxLines = TextViewCompat.getMaxLines(mCustomTextView);
            }
            mCustomIconView = (ImageView)view.findViewById(0x1020006);
        } else
        {
            if (mCustomView != null)
            {
                removeView(mCustomView);
                mCustomView = null;
            }
            mCustomTextView = null;
            mCustomIconView = null;
        }
        if (mCustomView == null)
        {
            if (mIconView == null)
            {
                ImageView imageview = (ImageView)LayoutInflater.from(getContext()).inflate(android.support.design.yout_tab_icon, this, false);
                addView(imageview, 0);
                mIconView = imageview;
            }
            if (mTextView == null)
            {
                TextView textview = (TextView)LayoutInflater.from(getContext()).inflate(android.support.design.yout_tab_text, this, false);
                addView(textview);
                mTextView = textview;
                mDefaultMaxLines = TextViewCompat.getMaxLines(mTextView);
            }
            mTextView.setTextAppearance(getContext(), TabLayout.access$1300(TabLayout.this));
            if (TabLayout.access$1400(TabLayout.this) != null)
            {
                mTextView.setTextColor(TabLayout.access$1400(TabLayout.this));
            }
            updateTextAndIcon(miconview, mTextView, mIconView);
        } else
        if (mCustomTextView != null || mCustomIconView != null)
        {
            updateTextAndIcon(miconview, mCustomTextView, mCustomIconView);
            return;
        }
    }

    public (Context context,  )
    {
        this$0 = TabLayout.this;
        super(context);
        mDefaultMaxLines = 2;
        mTab = ;
        if (TabLayout.access$300(TabLayout.this) != 0)
        {
            setBackgroundDrawable(TintManager.getDrawable(context, TabLayout.access$300(TabLayout.this)));
        }
        ViewCompat.setPaddingRelative(this, TabLayout.access$400(TabLayout.this), TabLayout.access$500(TabLayout.this), TabLayout.access$600(TabLayout.this), TabLayout.access$700(TabLayout.this));
        setGravity(17);
        setOrientation(1);
        update();
    }
}
