package com.example.viewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MonitorScrollView extends ScrollView {
 
  public interface ScrollViewListener {
 
    void onScrollChanged(MonitorScrollView scrollView, int x, int y,
                         int oldx, int oldy);
 
  }
 
  private ScrollViewListener scrollViewListener = null;
 
  public MonitorScrollView(Context context) {
    super(context);
  }
 
  public MonitorScrollView(Context context, AttributeSet attrs,
                           int defStyle) {
    super(context, attrs, defStyle);
  }
 
  public MonitorScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
 
  public void setScrollViewListener(ScrollViewListener scrollViewListener) {
    this.scrollViewListener = scrollViewListener;
  }
 
  @Override
  protected void onScrollChanged(int x, int y, int oldx, int oldy) {
    super.onScrollChanged(x, y, oldx, oldy);
    if (scrollViewListener != null) {
      scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
    }
  }
 
}
