package lab01.n11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
