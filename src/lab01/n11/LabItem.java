package lab01.n11;

import android.content.Context;
import android.widget.LinearLayout;

abstract public class LabItem {
	protected String topic;
	protected final LinearLayout ll;
	protected Lab01n11Activity main;
	protected Context context;
	
	public LabItem(Lab01n11Activity main) {
		this.main = main;
		ll = main.ll;
		context = main.ll.getContext();
	}
	
	public void exec() {
		
	};
	
}
