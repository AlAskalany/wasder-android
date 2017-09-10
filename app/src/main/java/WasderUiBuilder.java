import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.wasder.wasderapp.ui.TabFragment;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class WasderUiBuilder {
	
	static abstract class BuilderBase<T> {
		public abstract T build();
	}
	
	static class TabBuilder extends BuilderBase<TabFragment> {
		
		private ViewPager viewPager;
		private TabLayout tabLayout;
		
		public TabBuilder(ViewPager viewPager, TabLayout tabLayout, ViewPager viewPager1, TabLayout tabLayout1){
			
			this.viewPager = viewPager1;
			this.tabLayout = tabLayout1;
		}
		@Override
		public TabFragment build() {
			
			return null;
		}
	}
}
